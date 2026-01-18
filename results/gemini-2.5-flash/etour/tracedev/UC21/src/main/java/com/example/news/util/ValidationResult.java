package com.example.news.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to hold validation results, indicating success/failure
 * and any associated error messages.
 */
public class ValidationResult {
    public boolean isValid;
    public List<String> errors;

    public ValidationResult() {
        this.isValid = true;
        this.errors = new ArrayList<>();
    }

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors != null ? new ArrayList<>(errors) : new ArrayList<>();
    }

    /**
     * Adds an error message to the list of errors and sets isValid to false.
     *
     * @param error The error message to add.
     */
    public void addError(String error) {
        if (error != null && !error.trim().isEmpty()) {
            this.errors.add(error);
            this.isValid = false; // If there's an error, the result is no longer valid
        }
    }

    // Getters and Setters
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<String> getErrors() {
        return new ArrayList<>(errors); // Return a copy to prevent external modification
    }

    public void setErrors(List<String> errors) {
        this.errors = errors != null ? new ArrayList<>(errors) : new ArrayList<>();
        // Update isValid based on whether errors exist
        this.isValid = this.errors.isEmpty();
    }

    /**
     * Checks if there are any errors.
     * @return true if there are errors, false otherwise.
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}