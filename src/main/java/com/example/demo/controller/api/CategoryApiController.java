package com.example.demo.controller.api;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {
    private final CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<Category> list(@RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size) {
        return categoryService.findPaged(keyword, Math.max(page, 0), size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> get(@PathVariable Integer id) {
        Category category = categoryService.findById(id);
        return category == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(category);
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        category.setCategoryId(null);
        categoryService.save(category);
        return category;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category category) {
        if (categoryService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        category.setCategoryId(id);
        categoryService.save(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (categoryService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
