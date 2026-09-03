package com.example.controller.web;

import com.example.service.IUserServiceJpa;
import com.example.service.UserServiceJpaImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
    private IUserServiceJpa userService = new UserServiceJpaImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("resetUsername") == null) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }
        req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String otp = req.getParameter("otp");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        if (otp == null || newPassword == null || newPassword.isEmpty()) {
            req.setAttribute("error", "Vui lòng nhập đầy đủ thông tin");
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            req.setAttribute("error", "Mật khẩu xác nhận không khớp");
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("resetUsername");
        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }

        boolean isValid = userService.verifyOTP(username, otp);
        if (isValid) {
            boolean updated = userService.updatePassword(username, newPassword);
            if (updated) {
                session.removeAttribute("resetUsername");
                req.setAttribute("message", "Đặt lại mật khẩu thành công!");
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Có lỗi xảy ra, vui lòng thử lại.");
                req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("error", "Mã OTP không đúng hoặc đã hết hạn.");
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
        }
    }
}