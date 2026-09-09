package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WaitingController {

    @GetMapping("/waiting")
    public String waiting(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("account");
        if (user == null) {
            return "redirect:/login";
        }
        if (user.getRoleid() == 1) {
            return "redirect:/admin/categories";
        } else if (user.getRoleid() == 2) {
            return "redirect:/home";
        } else {
            return "redirect:/profile";
        }
    }
}