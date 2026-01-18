package com.culturalheritage.application.service;

import java.util.List;

/**
 * Result of request validation.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errorMessages;

    public ValidationResult(boolean isValid, List<String> errorMessages) {
        this.valid = isValid;
        this.errorMessages = errorMessages;
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}