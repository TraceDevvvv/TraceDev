package com.example;

import java.util.List;

/**
 * Result of the edit user operation.
 */
public class EditUserResult {
    private boolean success;
    private String message;
    private List<String> validationErrors;

    private EditUserResult(boolean success, String message, List<String> validationErrors) {
        this.success = success;
        this.message = message;
        this.validationErrors = validationErrors;
    }

    public static EditUserResult success(String message) {
        return new EditUserResult(true, message, java.util.Collections.emptyList());
    }

    public static EditUserResult failure(String message, List<String> errors) {
        return new EditUserResult(false, message, errors);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}