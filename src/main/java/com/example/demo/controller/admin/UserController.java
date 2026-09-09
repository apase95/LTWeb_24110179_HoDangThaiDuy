package com.example.demo.controller.admin;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<UserEntity> users;
        if (keyword != null && !keyword.isEmpty()) {
            users = userService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            users = userService.findAll();
        }
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        UserEntity user = new UserEntity();
        user.setRoleid(3);
        user.setActive(true);
        model.addAttribute("user", user);
        return "admin/user-add";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute UserEntity user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            return "admin/user-add";
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email đã được sử dụng");
            return "admin/user-add";
        }
        if (user.getRoleid() == null) {
            user.setRoleid(3);
        }
        if (user.getActive() == null) {
            user.setActive(true);
        }
        user.setCreateddate(new Date());
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        UserEntity user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user-edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute UserEntity user) {
        UserEntity existing = userService.findById(user.getId());
        if (existing != null) {
            existing.setFullname(user.getFullname());
            existing.setPhone(user.getPhone());
            existing.setRoleid(user.getRoleid());
            existing.setActive(user.getActive());
            userService.save(existing);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/enable/{id}")
    public String enable(@PathVariable Integer id) {
        userService.enableUser(id, true);
        return "redirect:/admin/users";
    }

    @GetMapping("/disable/{id}")
    public String disable(@PathVariable Integer id) {
        userService.enableUser(id, false);
        return "redirect:/admin/users";
    }
}