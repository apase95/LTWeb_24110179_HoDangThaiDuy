package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.service.MailService;
import com.example.demo.util.OTPUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("alert", "Sai tên đăng nhập hoặc mật khẩu");
        }
        if (logout != null) {
            model.addAttribute("alert", "Bạn đã đăng xuất");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email,
                           @RequestParam String fullname,
                           @RequestParam(required = false) String phone,
                           Model model, HttpSession session) {
        if (userService.findByUsername(username) != null) {
            model.addAttribute("alert", "Tài khoản đã tồn tại!");
            return "register";
        }
        if (userService.findByEmail(email) != null) {
            model.addAttribute("alert", "Email đã được sử dụng!");
            return "register";
        }
        if (phone != null && !phone.isEmpty() && !phone.matches("^[0-9]{10,11}$")) {
            model.addAttribute("alert", "Số điện thoại không hợp lệ");
            return "register";
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFullname(fullname);
        user.setPhone(phone);
        user.setRoleid(3);
        user.setActive(false);
        user.setCreateddate(new Date());
        userService.save(user);

        String otp = OTPUtil.generateOTP();
        Date expiry = new Date(System.currentTimeMillis() + 5 * 60 * 1000);
        userService.saveOTP(username, otp, expiry);

        try {
            mailService.send(email, "Xác thực tài khoản", "Mã xác thực OTP của bạn là: " + otp + "\nHiệu lực trong 5 phút.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("tempUsername", username);
        return "redirect:/verify-otp";
    }

    @GetMapping("/verify-otp")
    public String verifyOtpPage() {
        return "verify-otp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String otp, HttpSession session, Model model) {
        String username = (String) session.getAttribute("tempUsername");
        if (username == null) {
            return "redirect:/register";
        }
        boolean activated = userService.activateUser(username, otp);
        if (activated) {
            session.removeAttribute("tempUsername");
            model.addAttribute("message", "Kích hoạt thành công! Vui lòng đăng nhập.");
            return "login";
        } else {
            model.addAttribute("error", "Mã OTP không đúng hoặc đã hết hạn.");
            return "verify-otp";
        }
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email, Model model, HttpSession session) {
        UserEntity user = userService.findByEmail(email);
        if (user == null) {
            model.addAttribute("error", "Email không tồn tại trong hệ thống");
            return "forgot-password";
        }

        String otp = OTPUtil.generateOTP();
        Date expiry = new Date(System.currentTimeMillis() + 5 * 60 * 1000);
        userService.saveOTP(user.getUsername(), otp, expiry);

        try {
            mailService.send(email, "Đặt lại mật khẩu", "Mã OTP để đặt lại mật khẩu của bạn là: " + otp + "\nHiệu lực trong 5 phút.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Không thể gửi email, vui lòng thử lại sau.");
            return "forgot-password";
        }

        session.setAttribute("resetUsername", user.getUsername());
        model.addAttribute("message", "Mã OTP đã được gửi đến email của bạn.");
        return "reset-password";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage() {
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String otp,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                HttpSession session, Model model) {
        String username = (String) session.getAttribute("resetUsername");
        if (username == null) {
            return "redirect:/forgot-password";
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp");
            return "reset-password";
        }

        boolean isValid = userService.verifyOTP(username, otp);
        if (isValid) {
            boolean updated = userService.updatePassword(username, newPassword);
            if (updated) {
                session.removeAttribute("resetUsername");
                model.addAttribute("message", "Đặt lại mật khẩu thành công!");
                return "login";
            } else {
                model.addAttribute("error", "Có lỗi xảy ra, vui lòng thử lại.");
                return "reset-password";
            }
        } else {
            model.addAttribute("error", "Mã OTP không đúng hoặc đã hết hạn.");
            return "reset-password";
        }
    }
}
