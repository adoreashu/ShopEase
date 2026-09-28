package com.shopease.backend.models;
import jakarta.persistence.*;
@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double discount;
    private Integer stock;
    private Double rating;
    private String imageUrl;
    @ManyToOne @JoinColumn(name = "category_id")
    private Category category;
    private Boolean available;
    public Product() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; } public void setPrice(Double price) { this.price = price; }
    public Double getDiscount() { return discount; } public void setDiscount(Double discount) { this.discount = discount; }
    public Integer getStock() { return stock; } public void setStock(Integer stock) { this.stock = stock; }
    public Double getRating() { return rating; } public void setRating(Double rating) { this.rating = rating; }
    public String getImageUrl() { return imageUrl; } public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Category getCategory() { return category; } public void setCategory(Category category) { this.category = category; }
    public Boolean getAvailable() { return available; } public void setAvailable(Boolean available) { this.available = available; }
}
