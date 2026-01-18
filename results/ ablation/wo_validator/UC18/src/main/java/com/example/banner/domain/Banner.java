package com.example.banner.domain;

import java.util.Date;

/**
 * Domain entity representing a Banner.
 * Contains banner details and activation status.
 */
public class Banner {
    private String id;
    private String refreshmentPointId;
    private Date placementDate;
    private boolean isActive;

    /**
     * Constructor for Banner.
     * @param id unique identifier for the banner
     * @param refreshmentPointId the refreshment point where banner is placed
     */
    public Banner(String id, String refreshmentPointId) {
        this.id = id;
        this.refreshmentPointId = refreshmentPointId;
        this.placementDate = new Date(); // assume placement date is now
        this.isActive = true; // by default banner is active
    }

    public String getId() {
        return id;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Deactivates the banner.
     */
    public void deactivate() {
        this.isActive = false;
    }
}