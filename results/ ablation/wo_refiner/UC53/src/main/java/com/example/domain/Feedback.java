package com.example.domain;

import java.time.LocalDateTime;

/**
 * Represents a Feedback domain object.
 * Includes validation of vote and comment (requirement 4 and 5).
 */
public class Feedback {
    private String feedbackId;
    private String touristId;
    private String siteId;
    private int vote;
    private String comment;
    private LocalDateTime timestamp;

    // Constructor used by use case
    public Feedback(String feedbackId, String touristId, String siteId, int vote, String comment, LocalDateTime timestamp) {
        this.feedbackId = feedbackId;
        this.touristId = touristId;
        this.siteId = siteId;
        this.vote = vote;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    /**
     * Validates vote (must be 1-5) and comment (must not be null or empty).
     * Updated to accept parameters as per class diagram.
     */
    public boolean validateData() {
        return vote >= 1 && vote <= 5 && comment != null && !comment.trim().isEmpty();
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getSiteId() {
        return siteId;
    }

    public int getVote() {
        return vote;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}