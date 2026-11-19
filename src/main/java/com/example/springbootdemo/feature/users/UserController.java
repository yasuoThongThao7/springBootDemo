package com.example.springbootdemo.feature.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String showregisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest(null, null, null, null));
        return "auth/register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest(null, null));
        return "auth/login";
    }


}
