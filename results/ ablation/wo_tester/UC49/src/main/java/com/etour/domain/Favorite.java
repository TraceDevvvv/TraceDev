package com.etour.domain;

import java.util.Date;

/**
 * Domain entity representing a favorite tourist site saved by a tourist.
 * Implements the domain model from the class diagram.
 */
public class Favorite {
    private String id;
    private String touristId;
    private String siteId;
    private String siteName;
    private Date addedDate;

    // Constructor
    public Favorite(String id, String touristId, String siteId, String siteName, Date addedDate) {
        this.id = id;
        this.touristId = touristId;
        this.siteId = siteId;
        this.siteName = siteName;
        this.addedDate = addedDate;
    }

    // Getters (as per class diagram)
    public String getId() {
        return id;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public Date getAddedDate() {
        return addedDate;
    }
}