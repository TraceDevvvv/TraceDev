package com.example.web;

/**
 * Data Transfer Object for Banner.
 */
public class BannerDTO {
    private String id;
    private String pointOfRestId;
    private byte[] imageData;
    private String imageFormat;

    public BannerDTO(String id, String pointOfRestId, byte[] imageData, String imageFormat) {
        this.id = id;
        this.pointOfRestId = pointOfRestId;
        this.imageData = imageData;
        this.imageFormat = imageFormat;
    }

    public String getId() {
        return id;
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