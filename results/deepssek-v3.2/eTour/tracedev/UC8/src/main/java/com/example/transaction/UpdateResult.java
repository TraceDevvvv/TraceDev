package com.example.transaction;

/**
 * Result of an update operation.
 */
public class UpdateResult {
    private boolean success;
    private String updatedPointId;
    private String message;

    public UpdateResult(boolean success, String updatedPointId, String message) {
        this.success = success;
        this.updatedPointId = updatedPointId;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getUpdatedPointId() {
        return updatedPointId;
    }

    public String getMessage() {
        return message;
    }
}