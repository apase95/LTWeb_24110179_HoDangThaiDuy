package com.example.controller.web;

import com.example.entity.Product;
import com.example.service.IProductService;
import com.example.service.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/home", "/"})
public class HomeController extends HttpServlet {
    private IProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> newProducts = productService.findTop10();
        req.setAttribute("newProducts", newProducts);
        req.getRequestDispatcher("/views/index.jsp").forward(req, resp);
    }
}