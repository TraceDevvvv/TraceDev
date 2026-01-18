package com.example;

/**
 * Represents a feedback form filled by a tourist.
 */
public class FeedbackForm {
    private String siteId;
    private String touristId;
    private int rating;
    private String comment;

    // Constructor
    public FeedbackForm(String touristId, String siteId, int rating, String comment) {
        this.touristId = touristId;
        this.siteId = siteId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getTouristId() {
        return touristId;
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
}