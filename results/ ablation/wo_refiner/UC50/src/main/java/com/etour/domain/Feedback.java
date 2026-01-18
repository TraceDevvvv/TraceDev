package com.etour.domain;

/**
 * Feedback entity submitted by a tourist for a site.
 */
public class Feedback {
    private String id;
    private String touristId;
    private String siteId;
    private int rating;
    private String comment;

    public Feedback(String id, String touristId, String siteId, int rating, String comment) {
        this.id = id;
        this.touristId = touristId;
        this.siteId = siteId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}