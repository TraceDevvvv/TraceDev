package com.example;

/**
 * Represents a Banner associated with a refreshment point.
 */
public class Banner {
    private String id;
    private String imageUrl;
    private String refreshmentPointId;
    private RefreshmentPoint refreshmentPoint; // For bidirectional navigation

    public Banner(String id, String imageUrl, String refreshmentPointId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.refreshmentPointId = refreshmentPointId;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    // Added for bidirectional navigation
    public RefreshmentPoint getRefreshmentPoint() {
        return refreshmentPoint;
    }

    public void setRefreshmentPoint(RefreshmentPoint refreshmentPoint) {
        this.refreshmentPoint = refreshmentPoint;
    }
}