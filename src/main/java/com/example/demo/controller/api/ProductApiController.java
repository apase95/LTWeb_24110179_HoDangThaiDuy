package com.example.demo.controller.api;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductApiController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<Product> list(@RequestParam(defaultValue = "") String keyword,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        return productService.findPaged(keyword, Math.max(page, 0), size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        Product product = productService.findById(id);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequest request) {
        return save(null, request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody ProductRequest request) {
        if (productService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return save(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (productService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Product> save(Integer id, ProductRequest request) {
        Category category = categoryService.findById(request.categoryId());
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }
        Product product = id == null ? new Product() : productService.findById(id);
        product.setProductName(request.productName());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        product.setImages(request.images());
        product.setStatus(request.status());
        product.setCategory(category);
        if (product.getCreatedDate() == null) {
            product.setCreatedDate(new Date());
        }
        productService.save(product);
        return ResponseEntity.ok(product);
    }

    public record ProductRequest(String productName, String description, Double price, Integer quantity,
                                 String images, Integer status, Integer categoryId) {
    }
}
