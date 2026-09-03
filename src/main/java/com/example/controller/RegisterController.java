package com.example.controller;

import com.example.entity.UserEntity;
import com.example.service.UserService;
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

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        UserService service = new UserService();
        String alertMsg = "";

        if (service.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, res);
            return;
        }

        boolean isSuccess = service.register(username, password, email, fullname, phone);
        if (isSuccess) {
            UserServiceJpaImpl jpaService = new UserServiceJpaImpl();
            UserEntity user = jpaService.findByUsername(username);
            if (user != null) {
                String otp = OTPUtil.generateOTP();
                Date expiry = new Date(System.currentTimeMillis() + 5 * 60 * 1000); // 5 phút
                jpaService.saveOTP(username, otp, expiry);

                try {
                    String content = "Mã xác thực OTP của bạn là: " + otp + "\nHiệu lực trong 5 phút.";
                    EmailUtil.sendEmail(email, "Xác thực tài khoản", content);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                HttpSession session = req.getSession();
                session.setAttribute("tempUsername", username);
                res.sendRedirect(req.getContextPath() + "/verify-otp");
            } else {
                res.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            alertMsg = "System error!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, res);
        }
    }
}