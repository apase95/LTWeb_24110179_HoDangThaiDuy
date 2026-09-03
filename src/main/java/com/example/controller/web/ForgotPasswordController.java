package com.example.controller.web;

import com.example.entity.UserEntity;
import com.example.service.IUserServiceJpa;
import com.example.service.UserServiceJpaImpl;
import com.example.util.EmailUtil;
import com.example.util.OTPUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private IUserServiceJpa userService = new UserServiceJpaImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (email == null || email.trim().isEmpty()) {
            req.setAttribute("error", "Vui lòng nhập email");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
            return;
        }

        UserEntity user = userService.findByEmail(email);
        if (user == null) {
            req.setAttribute("error", "Email không tồn tại trong hệ thống");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
            return;
        }

        String otp = OTPUtil.generateOTP();
        Date expiry = new Date(System.currentTimeMillis() + 5 * 60 * 1000);
        userService.saveOTP(user.getUsername(), otp, expiry);

        try {
            String content = "Mã OTP để đặt lại mật khẩu của bạn là: " + otp + "\nHiệu lực trong 5 phút.";
            EmailUtil.sendEmail(email, "Đặt lại mật khẩu", content);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Không thể gửi email, vui lòng thử lại sau.");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("resetUsername", user.getUsername());

        req.setAttribute("message", "Mã OTP đã được gửi đến email của bạn.");
        req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
    }
}