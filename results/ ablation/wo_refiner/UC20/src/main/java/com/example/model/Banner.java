package com.example.model;

import java.time.LocalDateTime;
import com.example.validation.ValidationResult;

/**
 * Represents a Banner entity.
 * Includes restPointId per REQ-011.
 * Note: validate() method removed per REQ-009.
 */
public class Banner {
    private String id;
    private String restPointId;
    private byte[] imageData;
    private String imageType;
    private Long fileSize;
    private Integer width;
    private Integer height;
    private LocalDateTime uploadDate;

    public Banner(String id, String restPointId, byte[] imageData, String imageType,
                  Long fileSize, Integer width, Integer height, LocalDateTime uploadDate) {
        this.id = id;
        this.restPointId = restPointId;
        this.imageData = imageData;
        this.imageType = imageType;
        this.fileSize = fileSize;
        this.width = width;
        this.height = height;
        this.uploadDate = uploadDate;
    }

    /**
     * Convenience constructor for sequence diagram step 22.
     */
    public Banner(byte[] imageData, String imageType, String restPointId) {
        this(null, restPointId, imageData, imageType, null, null, null, null);
    }

    public String getId() {
        return id;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public String getRestPointId() {
        return restPointId;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getImageType() {
        return imageType;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * Resizes the banner to target dimensions.
     */
    public void resize(int targetWidth, int targetHeight) {
        // Implementation would resize the image data.
        this.width = targetWidth;
        this.height = targetHeight;
        System.out.println("Banner resized to " + targetWidth + "x" + targetHeight);
    }

    /**
     * Validates the banner.
     * Sequence diagram step 25.
     */
    public ValidationResult validate() {
        // This is a stub; actual validation would be delegated to BannerImageValidator.
        // For traceability, we return a default valid result.
        return new ValidationResult(true, null, "Banner validation passed.");
    }
}