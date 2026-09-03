package com.example.service;

import com.example.entity.Product;
import java.util.List;

public interface IProductDao {
    void insert(Product product);
    void update(Product product);
    void delete(int id) throws Exception;
    Product findById(int id);
    List<Product> findAll();
    List<Product> findTop10();
    List<Product> findAll(int page, int pageSize);
    int count();
    List<Product> searchByName(String keyword);
}