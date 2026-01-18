package com.example.adapters;

/**
 * Response object returned to the UI.
 */
public class FeedbackResponse {
    private String status;
    private String message;

    public FeedbackResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}