package kg.attractor.shareHub.controller;


import kg.attractor.shareHub.dto.UserDto;
import kg.attractor.shareHub.service.FileListService;
import kg.attractor.shareHub.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final FileListService fileListService;
    private static final int PAGE_SIZE = 5;

    @GetMapping("/profile")
    public String register(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        userDto.setPages(fileListService.getImageByUserId(userDto.getId(), page, PAGE_SIZE));
        model.addAttribute("account", userDto);
        return "profile";
    }

}
