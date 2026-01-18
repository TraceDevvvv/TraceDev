package com.example.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Value object representing the result of a validation.
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
     * @param error the error message
     */
    public void addError(String error) {
        this.valid = false;
        this.errors.add(error);
    }
}