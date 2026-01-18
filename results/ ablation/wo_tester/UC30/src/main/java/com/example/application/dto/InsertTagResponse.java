package com.example.application.dto;

/**
 * Data Transfer Object for inserting a tag response.
 */
public class InsertTagResponse {
    private boolean success;
    private String message;

    public InsertTagResponse(boolean success, String message) {
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