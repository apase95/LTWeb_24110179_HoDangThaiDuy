package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductListController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String list(@RequestParam(value = "page", defaultValue = "1") int page,
                       Model model) {
        int pageSize = 6;
        List<Product> products = productService.findAll(page - 1, pageSize);
        int total = productService.count();
        int totalPages = (int) Math.ceil((double) total / pageSize);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "web/product-list";
    }
}