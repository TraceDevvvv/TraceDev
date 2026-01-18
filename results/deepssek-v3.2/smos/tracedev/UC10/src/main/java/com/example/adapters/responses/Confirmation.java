package com.example.adapters.responses;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Confirmation response for role updates.
 */
public class Confirmation {
    private boolean success;
    private String message;
    private LocalDateTime timestamp;
    private List<String> validationErrors;

    public Confirmation(boolean success, String message, LocalDateTime timestamp, List<String> validationErrors) {
        this.success = success;
        this.message = message;
        this.timestamp = timestamp;
        this.validationErrors = validationErrors;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}