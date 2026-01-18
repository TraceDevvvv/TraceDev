package com.example.dto;

/**
 * Response object for delete bookmark operation.
 */
public class DeleteBookmarkResponse {
    private boolean success;
    private String message;

    public DeleteBookmarkResponse(boolean isSuccess, String message) {
        this.success = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}