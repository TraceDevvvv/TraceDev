package com.example.domain;

/**
 * Represents the result of a validation operation.
 */
public class ValidationResult {
    private boolean valid;
    private String errorMessage;

    public ValidationResult(boolean isValid, String errorMessage) {
        this.valid = isValid;
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}