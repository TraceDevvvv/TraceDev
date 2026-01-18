package com.example;

/**
 * Domain class representing feedback for a specific location.
 */
public class SiteFeedback {
    private String locationId;
    private String data;

    public SiteFeedback(String locationId, String data) {
        this.locationId = locationId;
        this.data = data;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getData() {
        return data;
    }
}