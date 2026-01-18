package com.example.value;

/**
 * Value object representing the result of password validation.
 */
public class ValidationResult {
    private boolean valid;
    private String errorMessage;

    // Constructors
    public ValidationResult() {
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

    // Setters (if needed)
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}