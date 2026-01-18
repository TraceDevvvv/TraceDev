package com.example.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Result of a validation operation.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errors;

    public ValidationResult() {
        this.valid = true;
        this.errors = new ArrayList<>();
    }

    public ValidationResult(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = errors != null ? errors : new ArrayList<>();
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    /**
     * Adds an error message and marks validation as invalid.
     */
    public void addError(String error) {
        this.errors.add(error);
        this.valid = false;
    }
}