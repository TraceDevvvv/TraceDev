package com.tourism.domain;

/**
 * Domain entity representing Feedback left by a Tourist for a Site.
 */
public class Feedback {
    private String feedbackId;
    private String touristId;
    private String siteId;
    private String comment;
    private int rating;

    public Feedback() {
    }

    public Feedback(String feedbackId, String touristId, String siteId, String comment, int rating) {
        this.feedbackId = feedbackId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.comment = comment;
        this.rating = rating;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}