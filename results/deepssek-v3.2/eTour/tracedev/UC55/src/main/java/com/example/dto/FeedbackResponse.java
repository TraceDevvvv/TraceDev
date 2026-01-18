package com.example.dto;

/**
 * Data transfer object for feedback response.
 */
public class FeedbackResponse {
    private boolean success;
    private String message;
    private FeedbackDto existingFeedback;

    public FeedbackResponse(boolean success, String message, FeedbackDto existingFeedback) {
        this.success = success;
        this.message = message;
        this.existingFeedback = existingFeedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public FeedbackDto getExistingFeedback() {
        return existingFeedback;
    }
}