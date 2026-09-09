package com.example.demo.controller.admin;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Category> categories;
        if (keyword != null && !keyword.isEmpty()) {
            categories = categoryService.search(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            categories = categoryService.findAll();
        }
        model.addAttribute("listcate", categories);
        return "admin/category-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category-add";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute Category category,
                         @RequestParam(value = "images1", required = false) MultipartFile file,
                         @RequestParam(value = "images", required = false) String imagesLink) throws IOException {
        String fileName;
        if (file != null && !file.isEmpty()) {
            fileName = FileUploadUtil.saveFile(file, "categories");
        } else if (imagesLink != null && !imagesLink.isEmpty()) {
            fileName = imagesLink;
        } else {
            fileName = "default.png";
        }
        category.setImages(fileName);
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("cate", category);
        return "admin/category-edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Category category,
                         @RequestParam(value = "images1", required = false) MultipartFile file,
                         @RequestParam(value = "images", required = false) String imagesLink) throws IOException {
        Category existing = categoryService.findById(category.getCategoryId());
        if (existing == null) {
            return "redirect:/admin/categories";
        }
        String oldFile = existing.getImages();
        if (file != null && !file.isEmpty()) {
            if (oldFile != null && !oldFile.startsWith("http")) {
                FileUploadUtil.deleteFile(oldFile, "categories");
            }
            String newFile = FileUploadUtil.saveFile(file, "categories");
            category.setImages(newFile);
        } else if (imagesLink != null && !imagesLink.isEmpty()) {
            category.setImages(imagesLink);
        } else {
            category.setImages(oldFile);
        }
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            String images = category.getImages();
            if (images != null && !images.startsWith("http")) {
                FileUploadUtil.deleteFile(images, "categories");
            }
            categoryService.delete(id);
        }
        return "redirect:/admin/categories";
    }
}