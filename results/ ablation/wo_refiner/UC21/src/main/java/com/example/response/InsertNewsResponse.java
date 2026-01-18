package com.example.response;

/**
 * Response object for the insert news operation.
 */
public class InsertNewsResponse {

    private boolean success;
    private String message;

    public InsertNewsResponse(boolean success, String message) {
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