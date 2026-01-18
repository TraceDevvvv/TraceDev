package com.example.domain;

/**
 * Result of the edit teaching operation.
 */
public class EditTeachingResult {
    private final boolean success;
    private final String errorMessage;
    private final Teaching updatedTeaching;

    public EditTeachingResult(boolean success, String errorMessage, Teaching teaching) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.updatedTeaching = teaching;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Teaching getUpdatedTeaching() {
        return updatedTeaching;
    }
}