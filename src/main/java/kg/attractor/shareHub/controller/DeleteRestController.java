package kg.attractor.shareHub.controller;

import kg.attractor.shareHub.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delete")
@RequiredArgsConstructor
public class DeleteRestController {
    private final ProfileImageService profileImageService;

    @DeleteMapping("/{fileId}")
    public void deleteFile(@PathVariable int fileId){
        profileImageService.deleteFile(fileId);
        System.out.println("It works!");
    }

}
