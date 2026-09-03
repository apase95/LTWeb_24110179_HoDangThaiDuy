package com.example.controller.web;

import java.io.IOException;
import com.example.service.UserServiceJpaImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/verify-otp")
public class VerifyOtpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String otp = req.getParameter("otp");
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("tempUsername");
        if (username == null) {
            resp.sendRedirect(req.getContextPath() + "/register");
            return;
        }

        UserServiceJpaImpl userService = new UserServiceJpaImpl();
        boolean activated = userService.activateUser(username, otp);
        if (activated) {
            session.removeAttribute("tempUsername");
            req.setAttribute("message", "Kích hoạt thành công! Vui lòng đăng nhập.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Mã OTP không đúng hoặc đã hết hạn.");
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
        }
    }
}
