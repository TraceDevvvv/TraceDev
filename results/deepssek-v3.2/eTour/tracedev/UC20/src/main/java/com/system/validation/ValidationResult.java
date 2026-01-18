package com.system.validation;

/**
 * Represents the result of a validation.
 */
public class ValidationResult {
    private boolean isValid;
    private String errorMessage;

    public ValidationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.errorMessage = message;
    }

    public boolean isValid() { return isValid; }
    public void setValid(boolean valid) { isValid = valid; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}