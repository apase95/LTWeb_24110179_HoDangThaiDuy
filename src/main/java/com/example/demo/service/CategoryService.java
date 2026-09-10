package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }
    public void save(Category category) {
        categoryRepository.save(category);
    }
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
    public Page<Category> findPaged(String keyword, int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        if (keyword == null || keyword.isBlank()) {
            return categoryRepository.findAll(request);
        }
        return categoryRepository.findByCategoryNameContainingIgnoreCase(keyword.trim(), request);
    }
}