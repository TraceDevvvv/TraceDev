package com.example.domain;

/**
 * Domain entity representing a Banner.
 */
public class Banner {
    private final String id;
    private String imageUrl;
    private final String pointOfRestaurantId;
    private boolean isActive;

    public Banner(String id, String imageUrl, String pointOfRestaurantId, boolean isActive) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.pointOfRestaurantId = pointOfRestaurantId;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    /**
     * Updates the image URL of this banner.
     * @param newImageUrl the new image URL.
     */
    public void updateImage(String newImageUrl) {
        this.imageUrl = newImageUrl;
    }
}