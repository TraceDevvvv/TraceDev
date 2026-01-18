package com.example.dto;

import com.example.model.Feedback;

/**
 * Data Transfer Object for Feedback.
 */
public class FeedbackDTO {
    public String id;
    public String siteId;
    public String comment;

    // Default constructor
    public FeedbackDTO() {}

    // Constructor from Feedback entity
    public FeedbackDTO(Feedback feedback) {
        this.id = feedback.getId();
        this.siteId = feedback.getSiteId();
        this.comment = feedback.getComment();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}