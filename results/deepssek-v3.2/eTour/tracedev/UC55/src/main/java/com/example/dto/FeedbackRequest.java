package com.example.dto;

/**
 * Data transfer object for feedback request.
 */
public class FeedbackRequest {
    private String siteId;
    private String userId;
    private String feedbackContent;

    public FeedbackRequest(String siteId, String userId, String feedbackContent) {
        this.siteId = siteId;
        this.userId = userId;
        this.feedbackContent = feedbackContent;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }
}