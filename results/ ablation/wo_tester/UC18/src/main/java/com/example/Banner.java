package com.example;

import java.util.Date;

/**
 * Represents a banner that belongs to a refreshment point and is associated with an agency.
 */
public class Banner {
    private String id;
    private String refreshmentPointId;
    private String agencyId;
    private Date createdAt;

    /**
     * Constructor for Banner.
     * @param id The unique identifier of the banner.
     * @param refreshmentPointId The ID of the refreshment point this banner belongs to.
     * @param agencyId The ID of the agency that owns this banner.
     */
    public Banner(String id, String refreshmentPointId, String agencyId) {
        this.id = id;
        this.refreshmentPointId = refreshmentPointId;
        this.agencyId = agencyId;
        this.createdAt = new Date(); // Set creation timestamp to current date and time
    }

    public String getId() {
        return id;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    // Additional methods can be added as needed.
}