package com.example.application;

/**
 * Result object returned by DeleteBannerUseCase.
 */
public class DeleteBannerResult {
    private final boolean success;
    private final String message;
    private final String deletedBannerId;

    public DeleteBannerResult(boolean success, String message, String deletedBannerId) {
        this.success = success;
        this.message = message;
        this.deletedBannerId = deletedBannerId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getDeletedBannerId() {
        return deletedBannerId;
    }
}