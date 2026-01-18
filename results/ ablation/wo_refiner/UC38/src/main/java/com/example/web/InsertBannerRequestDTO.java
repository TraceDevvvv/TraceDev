package com.example.web;

/**
 * Data Transfer Object for the insertion request.
 */
public class InsertBannerRequestDTO {
    private String pointOfRestId;
    private byte[] imageData;
    private String imageFormat;

    public InsertBannerRequestDTO(String pointOfRestId, byte[] imageData, String imageFormat) {
        this.pointOfRestId = pointOfRestId;
        this.imageData = imageData;
        this.imageFormat = imageFormat;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getImageFormat() {
        return imageFormat;
    }
}