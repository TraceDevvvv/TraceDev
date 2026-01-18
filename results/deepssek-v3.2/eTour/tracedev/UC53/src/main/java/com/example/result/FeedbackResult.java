package com.example.result;

/**
 * Result of a feedback submission.
 */
public class FeedbackResult {
    private boolean success;
    private String message;
    private String feedbackId;

    public FeedbackResult() {
    }

    public FeedbackResult(boolean success, String message, String feedbackId) {
        this.success = success;
        this.message = message;
        this.feedbackId = feedbackId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }
}