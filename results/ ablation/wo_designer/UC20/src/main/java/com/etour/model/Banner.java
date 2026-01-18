package com.etour.model;

import java.time.LocalDateTime;

/**
 * Entity representing a Banner associated with a refreshment point.
 */
public class Banner {
    private int id;
    private int refreshmentPointId;
    private String imagePath;
    private LocalDateTime createdAt;
    private String imageFormat;
    private long imageSize;
    private int width;
    private int height;

    public Banner() {}

    public Banner(int id, int refreshmentPointId, String imagePath, LocalDateTime createdAt,
                  String imageFormat, long imageSize, int width, int height) {
        this.id = id;
        this.refreshmentPointId = refreshmentPointId;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
        this.imageFormat = imageFormat;
        this.imageSize = imageSize;
        this.width = width;
        this.height = height;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(int refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
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

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public long getImageSize() {
        return imageSize;
    }

    public void setImageSize(long imageSize) {
        this.imageSize = imageSize;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}