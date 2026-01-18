package com.touristagency.application;

/**
 * Response model for the use case (application layer).
 */
public class ToggleStatusResponse {
    private final boolean success;
    private final String message;

    public ToggleStatusResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}