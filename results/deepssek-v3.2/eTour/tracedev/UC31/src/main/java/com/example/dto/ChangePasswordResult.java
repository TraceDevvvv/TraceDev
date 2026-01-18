package com.example.dto;

/**
 * Result of a change password operation.
 */
public class ChangePasswordResult {
    public boolean success;
    public String message;

    public ChangePasswordResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters and setters
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