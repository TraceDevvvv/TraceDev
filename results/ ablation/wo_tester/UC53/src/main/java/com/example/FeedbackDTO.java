package com.example;

import java.util.Date;

/**
 * Data Transfer Object for feedback, includes timestamp and feedbackId.
 */
public class FeedbackDTO {
    private String feedbackId;
    private String siteId;
    private String touristId;
    private int rating;
    private String comment;
    private Date timestamp;

    public FeedbackDTO(String feedbackId, String touristId, String siteId, int rating, String comment, Date timestamp) {
        this.feedbackId = feedbackId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public String getFeedbackId() {
        return feedbackId;
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

    public String getComment() {
        return comment;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}