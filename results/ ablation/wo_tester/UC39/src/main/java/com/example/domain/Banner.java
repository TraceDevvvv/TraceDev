package com.example.domain;

/**
 * Domain entity representing a Banner.
 * Contains business logic for banner operations.
 */
public class Banner {
    private String bannerId;
    private String pointOfRestaurantId;
    private String imageUrl;
    
    public Banner(String bannerId, String pointOfRestaurantId, String imageUrl) {
        this.bannerId = bannerId;
        this.pointOfRestaurantId = pointOfRestaurantId;
        this.imageUrl = imageUrl;
    }
    
    public String getBannerId() {
        return bannerId;
    }
    
    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    /**
     * Validates if the current image URL is valid.
     * For simplicity, checks if imageUrl is not null and not empty.
     * @return true if image is valid
     */
    public boolean isValidImage() {
        return imageUrl != null && !imageUrl.trim().isEmpty();
    }
    
    /**
     * Changes the banner image URL.
     * @param newImageUrl the new image URL
     */
    public void changeImage(String newImageUrl) {
        this.imageUrl = newImageUrl;
        // Internal state update as per sequence diagram
    }
    
    /**
     * Bookmarks the current image.
     * As per Flow of Events 12.
     */
    public void bookmarkImage() {
        // Implementation would bookmark the image for future reference
        // For now, we just log or perform internal bookmarking logic
        System.out.println("Image bookmarked for banner: " + bannerId);
    }
}