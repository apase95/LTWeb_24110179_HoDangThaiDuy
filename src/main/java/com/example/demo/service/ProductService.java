package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> findTop10() {
        return productRepository.findTop10ByOrderByCreatedDateDesc();
    }

    public List<Product> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).getContent();
    }

    public int count() {
        return (int) productRepository.count();
    }

    public List<Product> search(String keyword) {
        return productRepository.findByProductNameContaining(keyword);
    }
}