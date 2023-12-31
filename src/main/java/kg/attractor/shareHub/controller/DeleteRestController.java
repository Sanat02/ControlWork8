package kg.attractor.shareHub.controller;

import kg.attractor.shareHub.service.FileListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delete")
@RequiredArgsConstructor
public class DeleteRestController {
    private final FileListService fileListService;

    @DeleteMapping("/{fileId}")
    public void deleteFile(@PathVariable int fileId) {
        fileListService.deleteFile(fileId);
        System.out.println("It works!");
    }

}
