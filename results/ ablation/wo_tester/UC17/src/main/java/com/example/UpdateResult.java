package com.example;

/**
 * Result object returned after banner update operation.
 */
public class UpdateResult {
    private boolean success;
    private String message;
    private Banner updatedBanner;

    public UpdateResult(boolean success, String message, Banner updatedBanner) {
        this.success = success;
        this.message = message;
        this.updatedBanner = updatedBanner;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Banner getUpdatedBanner() {
        return updatedBanner;
    }
}