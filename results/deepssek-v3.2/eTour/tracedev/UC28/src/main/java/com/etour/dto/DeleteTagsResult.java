package com.etour.dto;

/**
 * Result object for delete tags operation.
 * Contains success flag, message, and count of deleted tags.
 */
public class DeleteTagsResult {
    private boolean success;
    private String message;
    private int deletedCount;

    public DeleteTagsResult(boolean success, String message, int deletedCount) {
        this.success = success;
        this.message = message;
        this.deletedCount = deletedCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getDeletedCount() {
        return deletedCount;
    }
}