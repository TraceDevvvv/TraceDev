package com.example.domain;

/**
 * Domain entity representing a Banner.
 */
public class Banner {
    private String bannerId;
    private String restPointId;
    private String imageUrl;
    private int width;
    private int height;

    public Banner(String bannerId, String restPointId, String imageUrl, int width, int height) {
        this.bannerId = bannerId;
        this.restPointId = restPointId;
        this.imageUrl = imageUrl;
        this.width = width;
        this.height = height;
    }

    public String getBannerId() {
        return bannerId;
    }

    public String getRestPointId() {
        return restPointId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void updateImage(String imageUrl, int width, int height) {
        this.imageUrl = imageUrl;
        this.width = width;
        this.height = height;
    }
}