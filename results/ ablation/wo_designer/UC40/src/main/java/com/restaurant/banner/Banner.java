package com.restaurant.banner;

import java.time.LocalDateTime;

/**
 * Represents a banner ad in the system.
 */
public class Banner {
    private int id;
    private String title;
    private String imageUrl;
    private LocalDateTime createdAt;
    private int restaurantId;

    public Banner() {}

    public Banner(int id, String title, String imageUrl, LocalDateTime createdAt, int restaurantId) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Banner [id=" + id + ", title=" + title + ", imageUrl=" + imageUrl +
               ", createdAt=" + createdAt + ", restaurantId=" + restaurantId + "]";
    }
}