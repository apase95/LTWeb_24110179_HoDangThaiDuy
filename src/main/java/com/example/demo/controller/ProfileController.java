package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        String username = authentication.getName();
        UserEntity user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "web/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@RequestParam String fullname,
                                @RequestParam(required = false) String phone,
                                @RequestParam(required = false) MultipartFile avatar,
                                Authentication authentication,
                                Model model) throws IOException {
        String username = authentication.getName();
        UserEntity user = userService.findByUsername(username);
        if (user == null) {
            return "redirect:/login";
        }

        if (fullname == null || fullname.trim().isEmpty()) {
            model.addAttribute("message", "Họ tên không được để trống");
            model.addAttribute("user", user);
            return "web/profile";
        }
        if (phone != null && !phone.isEmpty() && !phone.matches("^[0-9]{10,11}$")) {
            model.addAttribute("message", "Số điện thoại không hợp lệ (10-11 số)");
            model.addAttribute("user", user);
            return "web/profile";
        }

        user.setFullname(fullname);
        user.setPhone(phone);

        if (avatar != null && !avatar.isEmpty()) {
            String fileName = FileUploadUtil.saveFile(avatar, "avatars");
            String oldAvatar = user.getAvatar();
            if (oldAvatar != null && !oldAvatar.startsWith("http")) {
                FileUploadUtil.deleteFile(oldAvatar, "avatars");
            }
            user.setAvatar(fileName);
        }

        userService.save(user);
        model.addAttribute("message", "Cập nhật thành công!");
        model.addAttribute("user", user);
        return "web/profile";
    }
}