package com.culturalheritage.application.response;

/**
 * Response object for delete cultural heritage operation.
 */
public class DeleteCulturalHeritageResponse {
    private boolean success;
    private String message;
    private String deletedId;

    public DeleteCulturalHeritageResponse(boolean success, String message, String deletedId) {
        this.success = success;
        this.message = message;
        this.deletedId = deletedId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getDeletedId() {
        return deletedId;
    }
}