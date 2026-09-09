package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private Integer categoryId;

    @Column(name = "CategoryName", columnDefinition = "NVARCHAR(255)")
    private String categoryName;

    @Column(name = "Images", columnDefinition = "NVARCHAR(500)")
    private String images;

    @Column(name = "Status")
    private Integer status;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;
}