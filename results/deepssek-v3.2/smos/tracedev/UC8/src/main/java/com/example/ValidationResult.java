package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of a validation.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errors;

    public ValidationResult() {
        this.valid = true;
        this.errors = new ArrayList<>();
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.valid = false;
        this.errors.add(error);
    }
}