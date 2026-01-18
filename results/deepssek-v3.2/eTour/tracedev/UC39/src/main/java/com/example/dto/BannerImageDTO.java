package com.example.dto;

/**
 * Data Transfer Object for banner image operations.
 */
public class BannerImageDTO {
    private String pointOfRestaurantId;
    private String bannerId;
    private byte[] imageData;
    private String fileName;
    private String contentType;

    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }

    public void setPointOfRestaurantId(String pointOfRestaurantId) {
        this.pointOfRestaurantId = pointOfRestaurantId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}