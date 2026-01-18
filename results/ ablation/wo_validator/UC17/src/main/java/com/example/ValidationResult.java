package com.example;

/**
 * Represents the result of an image validation.
 * Contains a boolean flag for validity and an optional message.
 */
public class ValidationResult {
    private boolean valid;
    private String message;

    public ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}