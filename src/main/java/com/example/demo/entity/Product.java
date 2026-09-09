package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Integer productId;

    @Column(name = "ProductName", columnDefinition = "NVARCHAR(255)")
    private String productName;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Double price;
    private Integer quantity;
    @Column(name = "Images", columnDefinition = "NVARCHAR(500)")
    private String images;
    private Integer status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;
}