package com.example.application;

/**
 * Result DTO for banner deletion operation.
 */
public class DeleteBannerResult {
    private boolean success;
    private String message;
    private String deletedBannerId;
    
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