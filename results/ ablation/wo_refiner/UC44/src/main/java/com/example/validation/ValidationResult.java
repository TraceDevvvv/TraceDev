package com.example.validation;

import java.util.List;

/**
 * Result of a validation operation.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errors;

    /**
     * Constructor for validation result.
     */
    public ValidationResult(boolean isValid, List<String> errors) {
        this.valid = isValid;
        this.errors = errors;
    }

    // Getters
    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }
}