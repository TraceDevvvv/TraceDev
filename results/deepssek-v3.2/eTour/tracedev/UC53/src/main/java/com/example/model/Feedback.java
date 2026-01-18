
package com.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Feedback submitted by a tourist for a site.
 */
public class Feedback {
    private String feedbackId;
    private String touristId;
    private String siteId;
    private int vote;
    private String comment;
    private LocalDateTime timestamp;

    /**
     * Constructor as per sequence diagram requirement.
     */
    public Feedback(String touristId, String siteId, int vote, String comment) {
        this.touristId = touristId;
        this.siteId = siteId;
        this.vote = vote;
        this.comment = comment;
        this.timestamp = LocalDateTime.now();
        // feedbackId generated for simplicity
        this.feedbackId = "FB_" + touristId + "_" + siteId + "_" + timestamp.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
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

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Private validation method (as per class diagram).
     * The controller manages validation, but this can be used internally.
     */
    private boolean validate() {
        return vote >= 1 && vote <= 5 && comment != null && !comment.trim().isEmpty();
    }
}
