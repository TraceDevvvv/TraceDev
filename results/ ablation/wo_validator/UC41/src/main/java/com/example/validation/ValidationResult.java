package com.example.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of a validation.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    // Constructor
    public ValidationResult() {
        this.isValid = true;
        this.errors = new ArrayList<>();
    }

    // Adds an error and sets valid to false
    public void addError(String error) {
        this.errors.add(error);
        this.isValid = false;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getErrors() {
        return errors;
    }
}