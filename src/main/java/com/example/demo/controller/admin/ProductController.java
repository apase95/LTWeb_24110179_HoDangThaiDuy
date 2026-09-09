package com.example.demo.controller.admin;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Product> products;
        if (keyword != null && !keyword.isEmpty()) {
            products = productService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            products = productService.findAll();
        }
        model.addAttribute("listproduct", products);
        return "admin/product-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product-add";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute Product product,
                         @RequestParam("categoryId") Integer categoryId,
                         @RequestParam(value = "images", required = false) MultipartFile file) throws IOException {
        Category category = categoryService.findById(categoryId);
        if (category == null) {
            return "redirect:/admin/products";
        }
        product.setCategory(category);
        product.setCreatedDate(new Date());

        if (file != null && !file.isEmpty()) {
            String fileName = FileUploadUtil.saveFile(file, "products");
            product.setImages(fileName);
        } else {
            product.setImages("default.png");
        }

        productService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product-edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Product product,
                         @RequestParam("categoryId") Integer categoryId,
                         @RequestParam(value = "images", required = false) MultipartFile file) throws IOException {
        Product existing = productService.findById(product.getProductId());
        if (existing == null) {
            return "redirect:/admin/products";
        }
        Category category = categoryService.findById(categoryId);
        if (category == null) {
            return "redirect:/admin/products";
        }
        product.setCategory(category);
        if (file != null && !file.isEmpty()) {
            String oldFile = existing.getImages();
            if (oldFile != null && !oldFile.startsWith("http")) {
                FileUploadUtil.deleteFile(oldFile, "products");
            }
            String newFile = FileUploadUtil.saveFile(file, "products");
            product.setImages(newFile);
        } else {
            product.setImages(existing.getImages());
        }
        product.setCreatedDate(existing.getCreatedDate());
        productService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Product product = productService.findById(id);
        if (product != null) {
            String images = product.getImages();
            if (images != null && !images.startsWith("http")) {
                FileUploadUtil.deleteFile(images, "products");
            }
            productService.delete(id);
        }
        return "redirect:/admin/products";
    }
}