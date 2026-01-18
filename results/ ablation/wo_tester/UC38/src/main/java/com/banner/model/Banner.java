package com.banner.model;

import java.util.Date;
import java.util.UUID;

/**
 * Represents a Banner entity with image data and association to a PointOfRest.
 */
public class Banner {
    private String id;
    private String pointOfRestId;
    private byte[] imageData;
    private String imageFormat;
    private Date createdAt;

    public Banner(String pointOfRestId, byte[] imageData, String imageFormat) {
        this.id = UUID.randomUUID().toString();
        this.pointOfRestId = pointOfRestId;
        this.imageData = imageData;
        this.imageFormat = imageFormat;
        this.createdAt = new Date();
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

    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Validate image characteristics (simplified validation).
     * In real implementation would check actual image metadata.
     */
    public boolean validateImageCharacteristics() {
        return imageData != null && imageData.length > 0 && imageFormat != null && !imageFormat.isEmpty();
    }
}