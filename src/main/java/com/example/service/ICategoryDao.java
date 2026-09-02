package com.example.service;

import com.example.entity.Category;
import java.util.List;

public interface ICategoryDao {
    void insert(Category category);
    void update(Category category);
    void delete(int id) throws Exception;
    Category findById(int id);
    Category findByCategoryName(String name) throws Exception;
    List<Category> findAll();
    List<Category> searchByName(String keyword);
    List<Category> findAll(int page, int pageSize);
    int count();
}