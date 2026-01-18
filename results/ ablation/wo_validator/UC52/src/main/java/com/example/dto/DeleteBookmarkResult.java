package com.example.dto;

/**
 * Result of delete bookmark operation.
 */
public class DeleteBookmarkResult {
    private boolean success;
    private String message;

    public DeleteBookmarkResult(boolean isSuccess, String message) {
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