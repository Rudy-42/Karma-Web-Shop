package com.codemart.karmawebshop.controller;

import com.codemart.karmawebshop.entity.User;
import com.codemart.karmawebshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    UserService userService;


    @RequestMapping("/admin/view")
    public String admin(Model model) {

        List<User> users = userService.getAll();
        model.addAttribute("users", users);

        return "admin";
    }

    @RequestMapping("/admin/delete/{id}")
    public String adminDelete(@PathVariable long id){

        userService.delete(id);

        return "redirect:/admin/view";
    }

}
