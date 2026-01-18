package com.example.entity;

/**
 * Represents a banner with image data and characteristics.
 */
public class Banner {
    private String bannerId;
    private byte[] imageData;
    private String imageFormat;
    private int sizeInKB;
    private String associatedPointId;

    public Banner(String bannerId, byte[] imageData, String imageFormat, int sizeInKB) {
        this.bannerId = bannerId;
        this.imageData = imageData;
        this.imageFormat = imageFormat;
        this.sizeInKB = sizeInKB;
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

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public int getSizeInKB() {
        return sizeInKB;
    }

    public void setSizeInKB(int sizeInKB) {
        this.sizeInKB = sizeInKB;
    }

    public String getAssociatedPointId() {
        return associatedPointId;
    }

    public void setAssociatedPointId(String associatedPointId) {
        this.associatedPointId = associatedPointId;
    }

    /**
     * Validates image characteristics.
     * In a real implementation, this would check format, size, dimensions, etc.
     */
    public boolean validateImageCharacteristics() {
        // Basic validation
        boolean valid = imageData != null && imageData.length > 0 &&
                       imageFormat != null && !imageFormat.isEmpty() &&
                       sizeInKB > 0;
        System.out.println("Banner validation result: " + valid);
        return valid;
    }
}