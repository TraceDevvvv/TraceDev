package com.example.model;

import java.time.LocalDateTime;

/**
 * Represents a feedback issued by a user for a specific site.
 */
public class Feedback {
    private String feedbackId;
    private String userId;
    private String siteId;
    private String content;
    private LocalDateTime timestamp;

    public Feedback(String feedbackId, String userId, String siteId, String content, LocalDateTime timestamp) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.siteId = siteId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}