package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Result of profile validation containing validation status and error messages.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    // Constructors
    public ValidationResult() {
        this.isValid = true;
        this.errors = new ArrayList<>();
    }

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = (errors != null) ? errors : new ArrayList<>();
    }

    // Getters and setters
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

    /**
     * Add an error message and mark validation as invalid.
     */
    public void addError(String error) {
        if (error != null && !error.trim().isEmpty()) {
            errors.add(error);
            isValid = false;
        }
    }

    /**
     * Check if there are any errors.
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}