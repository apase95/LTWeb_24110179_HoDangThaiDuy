package com.example.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Category")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryId;

    @Column(name = "CategoryName", columnDefinition = "NVARCHAR(255) NOT NULL")
    private String categoryName;

    @Column(name = "Images", columnDefinition = "NVARCHAR(500)")
    private String images;

    @Column(name = "Status")
    private int status;

    public Category() {}

    public Category(String categoryName, String images, int status) {
        this.categoryName = categoryName;
        this.images = images;
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }
    
    public String getImages() {
        return images;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getStatus() {
        return status;
    } 

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}