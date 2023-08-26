package kg.attractor.shareHub.controller;

import kg.attractor.shareHub.dto.ProfileImageDto;
import kg.attractor.shareHub.model.ProfileImage;
import kg.attractor.shareHub.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class FileController {
    private final ProfileImageService profileImageService;
    @GetMapping()
    public String getListOfAllFiles(Model model) {
        List<ProfileImageDto> files=profileImageService.getAllFiles();
        model.addAttribute("files",files);
        model.addAttribute("hello","hi");
        return "fileList";
    }
}