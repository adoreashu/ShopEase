package com.shopease.backend.controllers;
import com.shopease.backend.models.Product;
import com.shopease.backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() { return productRepository.findAll(); }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) { return productRepository.findById(id).orElse(null); }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String q) { return productRepository.findByNameContainingIgnoreCase(q); }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) { return productRepository.findByCategoryName(category); }
}
