package com.banner.model;

/**
 * Request DTO for adding a banner.
 */
public class AddBannerRequest {
    private String pointOfRestaurantId;
    private ImageFile imageFile; // Assuming already converted from MultipartFile

    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }

    public void setPointOfRestaurantId(String pointOfRestaurantId) {
        this.pointOfRestaurantId = pointOfRestaurantId;
    }

    public ImageFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(ImageFile imageFile) {
        this.imageFile = imageFile;
    }
}