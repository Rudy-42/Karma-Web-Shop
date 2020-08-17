package com.codemart.karmawebshop.controller;

import com.codemart.karmawebshop.controller.validator.UserValidator;
import com.codemart.karmawebshop.entity.User;
import com.codemart.karmawebshop.service.ProductService;
import com.codemart.karmawebshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    UserValidator validator;

    @GetMapping("/login")
    public String login(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute(value = "user") User user, Model model) {
        System.out.println(user);
        user.setRole("USER");

        switch (validator.validate(user)) {
            case "ok":
                if (userService.exists(user)) {
                    model.addAttribute("usernameError", true);
                    return "registration";
                }
                userService.addUser(user);
                return "login";
            case "username":
                model.addAttribute("usernameError", true);
                return "registration";
            case "password":
                model.addAttribute("passwordError", true);
                return "registration";
            case "email":
                model.addAttribute("emailError", true);
                return "registration";
            default:
                model.addAttribute("error", true);
                return "registration";
        }
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
}
