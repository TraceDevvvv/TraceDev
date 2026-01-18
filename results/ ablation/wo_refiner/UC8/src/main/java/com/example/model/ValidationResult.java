package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of a validation.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    public ValidationResult() {
        this.isValid = true;
        this.errors = new ArrayList<>();
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
        this.isValid = false;
    }
}