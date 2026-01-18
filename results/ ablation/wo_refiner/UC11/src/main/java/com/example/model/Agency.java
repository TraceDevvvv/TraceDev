package com.example.model;

/**
 * Represents an Agency with a designated Point of Rest ID.
 * Added to satisfy requirement REQ-004.
 */
public class Agency {
    private String designatedPointOfRestId;

    public String getDesignatedPointOfRestId() {
        return designatedPointOfRestId;
    }

    public void setDesignatedPointOfRestId(String pointOfRestId) {
        this.designatedPointOfRestId = pointOfRestId;
    }
}