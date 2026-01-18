package com.example;

/**
 * Represents the result of a validation operation.
 */
public class ValidationResult {
    private boolean valid;
    private String errorMessage;

    public ValidationResult(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}