package com.example.dto;

import java.util.List;

/**
 * Represents the result of a validation operation.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    // Constructors
    public ValidationResult() {}

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }

    // Getters and Setters
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}