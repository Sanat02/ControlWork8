package kg.attractor.shareHub.controller;


import kg.attractor.shareHub.dto.ProfileImageDto;
import kg.attractor.shareHub.service.ProfileImageService;
import kg.attractor.shareHub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/images")
@Controller
public class SetImageController {
    private final ProfileImageService profileImageService;
    private final UserService userService;

    @GetMapping()
    public String setImage(Model model) {
        return "setFile";
    }

    @PostMapping("/set")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addImage(
            @RequestParam(name = "files") MultipartFile image,
            @RequestParam(name = "privacy") String status

            ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ProfileImageDto profileImageDto = ProfileImageDto.builder()
                .file(image)
                .userId(userService.mapToUserDto(userService.getUserByEmail(auth.getName()).get()).getId())
                .status(status)
                .build();
        profileImageService.uploadImage(profileImageDto);
        return "redirect:/profile";
    }

}
