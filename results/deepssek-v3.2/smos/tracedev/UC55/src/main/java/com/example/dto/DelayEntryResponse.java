package com.example.dto;

/**
 * Response object for delay entry processing.
 * Indicates success or failure with a message.
 */
public class DelayEntryResponse {
    private boolean success;
    private String message;

    public DelayEntryResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
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
}