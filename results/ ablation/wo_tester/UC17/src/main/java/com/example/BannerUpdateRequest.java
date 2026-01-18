package com.example;

/**
 * Request object for updating a banner's image.
 */
public class BannerUpdateRequest {
    private String bannerId;
    private ImageData imageData;

    public BannerUpdateRequest(String bannerId, ImageData imageData) {
        this.bannerId = bannerId;
        this.imageData = imageData;
    }

    public String getBannerId() {
        return bannerId;
    }

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }
}