package com.example.application.results;

/**
 * Result of the update banner image use case.
 */
public class UpdateBannerImageResult {
    private final boolean success;
    private final String message;

    public UpdateBannerImageResult(boolean success, String message) {
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