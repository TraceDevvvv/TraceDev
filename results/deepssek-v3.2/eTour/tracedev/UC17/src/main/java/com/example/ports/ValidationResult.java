package com.example.ports;

import java.util.List;

/**
 * Result of validation containing status and error messages.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errorMessages;

    public ValidationResult(boolean valid, List<String> errorMessages) {
        this.valid = valid;
        this.errorMessages = errorMessages;
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}