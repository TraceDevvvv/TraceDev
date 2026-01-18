package com.example.dto;

/**
 * DTO for insert banner request.
 */
public class InsertBannerRequestDTO {
    private String refreshmentPointId;
    private byte[] imageData;
    private String imageFormat;
    private int sizeInKB;

    public InsertBannerRequestDTO(String refreshmentPointId, byte[] imageData, String imageFormat, int sizeInKB) {
        this.refreshmentPointId = refreshmentPointId;
        this.imageData = imageData;
        this.imageFormat = imageFormat;
        this.sizeInKB = sizeInKB;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
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
}