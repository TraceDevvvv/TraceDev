package com.example.banner.model;

import java.time.LocalDateTime;

/**
 * Model class representing a Banner entity.
 */
public class Banner {
    private Long id;
    private Long pointOfRestaurantId;
    private String imagePath;
    private LocalDateTime createdAt;

    public Banner() {}

    public Banner(Long id, Long pointOfRestaurantId, String imagePath, LocalDateTime createdAt) {
        this.id = id;
        this.pointOfRestaurantId = pointOfRestaurantId;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }

    public void setPointOfRestaurantId(Long pointOfRestaurantId) {
        this.pointOfRestaurantId = pointOfRestaurantId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}