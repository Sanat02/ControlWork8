package kg.attractor.shareHub.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class FileController {
    @GetMapping()
    public String getListOfFiles(Model model) {
        model.addAttribute("hello","hi");
        return "fileList";
    }
}
