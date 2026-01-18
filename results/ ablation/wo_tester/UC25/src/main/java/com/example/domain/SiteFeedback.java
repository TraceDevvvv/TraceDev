package com.example.domain;

/**
 * Domain entity representing feedback for a site/location.
 */
public class SiteFeedback {
    private String locationId;
    private int rating;
    private String comment;

    public SiteFeedback(String locationId, int rating, String comment) {
        this.locationId = locationId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getLocationId() {
        return locationId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}