package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"", "/", "/home", "/error"})
public class AppController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        
        switch (path) {
            case "/error":
                req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                break;
            case "/home":
                resp.setContentType("text/html; charset=UTF-8");
                resp.getWriter().println("<h1>Đăng nhập thành công! Bạn đang ở trang /home</h1>");
                break;
            default:
                req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
    }
}