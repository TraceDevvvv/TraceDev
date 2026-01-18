package com.example.model;

import java.util.Date;

/**
 * Represents a Feedback entry with an ID, site ID, comment, rating, and submission date.
 */
public class Feedback {
    private int feedbackId;
    private int siteId;
    private String comment;
    private int rating;
    private Date dateSubmitted;

    // Constructor with parameters, feedbackId may be set later (e.g., by database)
    public Feedback(int siteId, String comment, int rating, Date date) {
        this.siteId = siteId;
        this.comment = comment;
        this.rating = rating;
        this.dateSubmitted = date;
    }

    // Additional constructor with feedbackId
    public Feedback(int feedbackId, int siteId, String comment, int rating, Date date) {
        this.feedbackId = feedbackId;
        this.siteId = siteId;
        this.comment = comment;
        this.rating = rating;
        this.dateSubmitted = date;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public int getSiteId() {
        return siteId;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date date) {
        this.dateSubmitted = date;
    }
}