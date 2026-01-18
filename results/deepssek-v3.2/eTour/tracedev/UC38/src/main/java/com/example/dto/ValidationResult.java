package com.example.dto;

/**
 * Data Transfer Object for validation results.
 */
public class ValidationResult {
    private boolean isValid;
    private String errorMessage;
    private int validationCode;

    public ValidationResult(boolean isValid, String errorMessage, int validationCode) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
        this.validationCode = validationCode;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getValidationCode() {
        return validationCode;
    }
}