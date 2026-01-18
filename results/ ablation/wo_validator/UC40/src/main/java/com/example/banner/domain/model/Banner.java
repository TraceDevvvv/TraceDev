package com.example.banner.domain.model;

import java.util.Date;

/**
 * Banner domain entity representing an advertising banner.
 */
public class Banner {
    private String id;
    private String pointOfRestId;
    private String imageUrl;
    private boolean active;
    private Date createdAt;

    /**
     * Constructor for Banner.
     */
    public Banner(String id, String pointOfRestId, String imageUrl, boolean active, Date createdAt) {
        this.id = id;
        this.pointOfRestId = pointOfRestId;
        this.imageUrl = imageUrl;
        this.active = active;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isActive() {
        return active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Marks the banner as deleted (inactive).
     */
    public void delete() {
        this.active = false;
    }
}