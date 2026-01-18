package com.example.validation;

/**
 * Result of validation.
 */
public class ValidationResult {
    private boolean isValid;
    private String errorMessage;

    public ValidationResult(boolean isValid, String errorMessage) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}