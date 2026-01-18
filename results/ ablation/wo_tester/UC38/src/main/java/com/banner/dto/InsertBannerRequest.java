package com.banner.dto;

/**
 * Request DTO for inserting a new banner.
 */
public class InsertBannerRequest {
    private String pointOfRestId;
    private byte[] imageData;
    private String imageFormat;

    public InsertBannerRequest(String pointOfRestId, byte[] imageData, String imageFormat) {
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

    /**
     * Simple validation for required fields.
     */
    public boolean validate() {
        return pointOfRestId != null && !pointOfRestId.isEmpty() &&
               imageData != null && imageData.length > 0 &&
               imageFormat != null && !imageFormat.isEmpty();
    }
}