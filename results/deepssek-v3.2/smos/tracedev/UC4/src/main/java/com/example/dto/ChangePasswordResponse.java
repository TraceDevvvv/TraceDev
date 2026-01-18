package com.example.dto;

/**
 * Data Transfer Object for password change response.
 */
public class ChangePasswordResponse {
    private boolean success;
    private String message;

    public ChangePasswordResponse(boolean success, String message) {
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