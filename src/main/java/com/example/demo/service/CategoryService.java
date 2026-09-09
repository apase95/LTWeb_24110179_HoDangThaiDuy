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
    public List<Category> search(String keyword) {
        return categoryRepository.findByCategoryNameContaining(keyword);
    }
    public Page<Category> findAllPaged(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }
}