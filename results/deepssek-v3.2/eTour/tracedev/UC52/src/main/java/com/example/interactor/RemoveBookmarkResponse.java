package com.example.interactor;

/**
 * Response object for bookmark removal operation.
 */
public class RemoveBookmarkResponse {
    private boolean success;
    private String message;

    public RemoveBookmarkResponse(boolean success, String message) {
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