package com.example.validation;

/**
 * Result of a validation operation.
 */
public class ValidationResult {
    private boolean valid;
    private String errorCode;
    private String errorMessage;

    public ValidationResult(boolean valid, String errorCode, String errorMessage) {
        this.valid = valid;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}