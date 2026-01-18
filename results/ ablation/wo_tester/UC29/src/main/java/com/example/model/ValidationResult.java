package com.example.model;

/**
 * Represents the result of a validation operation.
 */
public class ValidationResult {
    private boolean isValid;
    private int errorCode;
    private String errorMessage;

    public ValidationResult(boolean isValid, int errorCode, String errorMessage) {
        this.isValid = isValid;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}