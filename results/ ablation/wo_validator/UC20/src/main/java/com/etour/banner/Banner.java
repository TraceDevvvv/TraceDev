package com.etour.banner;

import java.time.LocalDateTime;

/**
 * Banner - represents an advertisement banner with image path and creation date.
 */
public class Banner {
    private String id;
    private String imagePath;
    private LocalDateTime creationDate;
    private String associatedRestPointId;

    public Banner(String id, String imagePath, String associatedRestPointId) {
        this.id = id;
        this.imagePath = imagePath;
        this.associatedRestPointId = associatedRestPointId;
        this.creationDate = LocalDateTime.now();
    }

    // Validates basic image characteristics (e.g., path not empty).
    public boolean validateImageCharacteristics() {
        return imagePath != null && !imagePath.trim().isEmpty();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String path) {
        this.imagePath = path;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getAssociatedRestPointId() {
        return associatedRestPointId;
    }

    public void setAssociatedRestPointId(String associatedRestPointId) {
        this.associatedRestPointId = associatedRestPointId;
    }
}