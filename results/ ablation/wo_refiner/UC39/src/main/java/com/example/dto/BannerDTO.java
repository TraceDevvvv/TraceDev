package com.example.dto;

/**
 * Data Transfer Object for Banner.
 */
public class BannerDTO {
    private String id;
    private String pointOfRestaurantId;
    private String imageUrl;
    private String status;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}