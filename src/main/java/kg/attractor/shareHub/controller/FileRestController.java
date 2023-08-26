package kg.attractor.shareHub.controller;


import kg.attractor.shareHub.dto.FileListDto;
import kg.attractor.shareHub.service.FileListService;
import kg.attractor.shareHub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class FileRestController {

    private final FileListService profileImageService;
    private final UserService userService;
    @PostMapping("/upload")
    public HttpStatus uploadImage(FileListDto profileImageDto) {
        profileImageService.uploadImage(profileImageDto);
        return HttpStatus.OK;

    }

//    @GetMapping
//    public ResponseEntity<?> getImagesByUserId() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return profileImageService.getImageByUsId(userService.mapToUserDto(userService.getUserByEmail(auth.getName()).get()).getId());
//    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get("data", "images", fileName);
        File file = filePath.toFile();

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);

        FileSystemResource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
