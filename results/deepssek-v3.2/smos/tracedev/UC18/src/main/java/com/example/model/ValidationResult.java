package com.example.model;

/**
 * Represents the result of address validation.
 */
public class ValidationResult {
    private boolean valid;
    private String errorMessage;

    // Constructors
    public ValidationResult(boolean valid) {
        this.valid = valid;
    }

    public ValidationResult(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    // Getters
    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}