package com.etoour.model;

import java.util.Date;

/**
 * Feedback class representing a tourist's feedback on a site.
 */
public class Feedback {
    private String feedbackId;
    private String siteId;
    private String touristId;
    private String comment;
    private int rating;
    private Date createdAt;
    private Date updatedAt;
    private boolean invalid = false; // Track invalid state

    public Feedback(String feedbackId, String siteId, String touristId, String comment, int rating, Date createdAt, Date updatedAt) {
        this.feedbackId = feedbackId;
        this.siteId = siteId;
        this.touristId = touristId;
        this.comment = comment;
        this.rating = rating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Update the comment and set the updated timestamp.
     */
    public void updateComment(String newComment) {
        this.comment = newComment;
        this.updatedAt = new Date(); // Set to current time
    }

    /**
     * Validate the feedback data.
     * For simplicity, checks non‑null comment and rating between 1‑5.
     */
    public boolean validate() {
        boolean valid = comment != null && !comment.trim().isEmpty()
                && rating >= 1 && rating <= 5;
        if (!valid) {
            invalid = true;
        }
        return valid;
    }

    /**
     * Mark this feedback as invalid (per requirement REQ‑004).
     */
    public void markAsInvalid() {
        this.invalid = true;
    }

    public boolean isInvalid() {
        return invalid;
    }
}