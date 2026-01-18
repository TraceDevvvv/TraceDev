package com.example.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for validation result.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    public ValidationResult() {
        this.errors = new ArrayList<>();
        this.isValid = true;
    }

    public boolean isValid() {
        return errors.isEmpty();
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

    public void addError(String error) {
        this.errors.add(error);
    }
}