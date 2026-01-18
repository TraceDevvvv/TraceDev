package com.example.agency.model;

/**
 * Represents feedback for a specific site/location.
 */
public class SiteFeedback {
    private String id;
    private String locationId;
    private int rating; // 1 to 5
    private String comment;
    private String timestamp;

    public SiteFeedback(String id, String locationId, int rating, String comment, String timestamp) {
        this.id = id;
        this.locationId = locationId;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SiteFeedback{" +
                "id='" + id + '\'' +
                ", locationId='" + locationId + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}