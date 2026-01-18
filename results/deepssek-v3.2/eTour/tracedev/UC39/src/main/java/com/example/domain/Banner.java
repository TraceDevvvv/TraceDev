package com.example.domain;

/**
 * Core domain entity representing a Banner.
 */
public class Banner {
    private String id;
    private String pointOfRestaurantId;
    private String imageKey;
    private int displayOrder;
    private boolean isActive;

    public Banner() {
    }

    public Banner(String id, String pointOfRestaurantId, String imageKey, int displayOrder, boolean isActive) {
        this.id = id;
        this.pointOfRestaurantId = pointOfRestaurantId;
        this.imageKey = imageKey;
        this.displayOrder = displayOrder;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }

    public void setPointOfRestaurantId(String pointOfRestaurantId) {
        this.pointOfRestaurantId = pointOfRestaurantId;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Updates the image key for this banner.
     * @param newImageKey the new image key from storage
     */
    public void updateImage(String newImageKey) {
        this.imageKey = newImageKey;
    }
}