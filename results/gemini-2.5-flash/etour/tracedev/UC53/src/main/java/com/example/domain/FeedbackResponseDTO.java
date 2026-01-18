package com.example.domain;

/**
 * Data Transfer Object (DTO) for providing feedback submission results.
 * Used to transfer data from the Application Layer back to the Presentation Layer.
 */
public class FeedbackResponseDTO {
    public boolean success;
    public String message;
    public String feedbackId; // Will be null if submission was not successful or no feedback was created

    // Constructor for success case
    public FeedbackResponseDTO(boolean success, String message, String feedbackId) {
        this.success = success;
        this.message = message;
        this.feedbackId = feedbackId;
    }

    // Constructor for error/general message case without specific feedbackId
    public FeedbackResponseDTO(boolean success, String message) {
        this(success, message, null);
    }

    // Getters for all attributes
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    // Setters (if mutable DTOs are preferred)
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }

    @Override
    public String toString() {
        return "FeedbackResponseDTO{" +
               "success=" + success +
               ", message='" + message + '\'' +
               ", feedbackId='" + feedbackId + '\'' +
               '}';
    }
}