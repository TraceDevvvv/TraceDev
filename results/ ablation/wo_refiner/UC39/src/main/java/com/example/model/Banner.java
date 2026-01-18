package com.example.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a Banner entity.
 * Added to satisfy requirement REQ-013
 */
public class Banner {
    private String id;
    private String pointOfRestaurantId;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Banner(String id, String pointOfRestaurantId, String imageUrl) {
        this.id = id;
        this.pointOfRestaurantId = pointOfRestaurantId;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Validates the image properties.
     * @return validation result
     */
    public ValidationResult validateImageProperties() {
        // In a real scenario, you might check image properties using an external service.
        ValidationResult result = new ValidationResult();
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            result.addError("Image URL is empty.");
        } else if (!imageUrl.startsWith("http")) {
            result.addError("Image URL must be a valid HTTP/HTTPS link.");
        }
        return result;
    }

    /**
     * Bookmarks the image (saves a copy or reference).
     * Added to satisfy requirement REQ-013
     * @param imageUrl the image URL to bookmark
     */
    public void bookmarkImage(String imageUrl) {
        // In a real system, this might store a copy of the image or a reference in a separate table.
        System.out.println("Bookmarking image: " + imageUrl);
    }
}