package com.example.domain;

import java.util.Date;

/**
 * Banner domain entity representing digital signage for points of rest.
 */
public class Banner {
    private String id;
    private String pointOfRestId;
    private String imageUrl;
    private boolean isActive;
    private Date createdAt;
    private Date deletedAt;

    // Constructor for creating a new Banner
    public Banner(String id, String pointOfRestId, String imageUrl, boolean isActive, Date createdAt) {
        this.id = id;
        this.pointOfRestId = pointOfRestId;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.deletedAt = null;
    }

    // Constructor for loading existing Banner from persistence
    public Banner(String id, String pointOfRestId, String imageUrl, boolean isActive, Date createdAt, Date deletedAt) {
        this.id = id;
        this.pointOfRestId = pointOfRestId;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public void setPointOfRestId(String pointOfRestId) {
        this.pointOfRestId = pointOfRestId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    /**
     * Deactivates the banner (soft delete).
     */
    public void deactivate() {
        this.isActive = false;
        this.deletedAt = new Date(); // current timestamp
    }

    /**
     * Reactivates the banner (rollback in case of ETOUR interruption).
     */
    public void reactivate() {
        this.isActive = true;
        this.deletedAt = null;
    }

    /**
     * Checks if this banner is associated with a given point of rest.
     * @param pointOfRestId the point of rest ID to check
     * @return true if the banner belongs to the given point of rest
     */
    public boolean isAssociatedWith(String pointOfRestId) {
        return this.pointOfRestId.equals(pointOfRestId);
    }
}