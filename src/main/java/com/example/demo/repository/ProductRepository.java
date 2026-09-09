package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findTop10ByOrderByCreatedDateDesc();
    List<Product> findByProductNameContaining(String keyword);
}