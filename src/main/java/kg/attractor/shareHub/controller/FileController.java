package kg.attractor.shareHub.controller;

import kg.attractor.shareHub.dto.FileListDto;
import kg.attractor.shareHub.service.FileListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class FileController {
    private final FileListService profileImageService;
    private static final int PAGE_SIZE = 5;

    @GetMapping()
    public String getListOfAllFiles(@RequestParam(name = "page", defaultValue = "0") int page,Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Page<FileListDto> files = profileImageService.getAllFiles(page,PAGE_SIZE);
        model.addAttribute("files", files);
        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", auth.getName());
        }
        return "fileList";
    }
}
