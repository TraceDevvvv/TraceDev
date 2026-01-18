package com.example.usecase;

import java.util.List;

/**
 * Represents the result of a validation operation.
 * Maps to the ValidationResult class in the UML diagram.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public List<String> getErrors() {
        return errors;
    }
}