package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/detail")
    public String detail(@RequestParam("id") int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "web/product-detail";
    }
}