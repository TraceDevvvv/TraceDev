package com.banner.model;

import java.util.Date;

/**
 * Represents a banner in the system.
 */
public class Banner {
    private String id;
    private String pointOfRestaurantId;
    private String imageUrl;
    private String status;
    private Date createdAt;

    public Banner(String pointOfRestaurantId, String imageUrl) {
        this.id = java.util.UUID.randomUUID().toString();
        this.pointOfRestaurantId = pointOfRestaurantId;
        this.imageUrl = imageUrl;
        this.status = "ACTIVE";
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }

    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean validateImage(ImageFile imageFile) {
        // In a real scenario, validation logic would be delegated to ImageValidatorService
        // This method is kept as per UML but might be unused or simplified.
        // We assume validation passes if imageFile is not null.
        return imageFile != null;
    }
}