package com.example.librarycatalog.controllers;

import com.example.librarycatalog.models.UserWithRole;
import com.example.librarycatalog.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class AuthController {
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(Model model){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        UserWithRole user = new UserWithRole();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration/save")
    public String register(@Valid @ModelAttribute("user") UserWithRole user,
                           BindingResult result, Model model) {
        UserWithRole existingEmail = userService.findByEmail(user.getEmail());
        if (existingEmail != null) {
            result.rejectValue("email", "error.email", "There is already a user who uses this email");
        }

        UserWithRole existingUsername = userService.findByUsername(user.getUsername());
        if (existingUsername != null) {
            result.rejectValue("username", "error.username", "There is already a user who uses this username");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "registration";
        }
        userService.saveUser(user);

        return "redirect:/?success";
    }

}

