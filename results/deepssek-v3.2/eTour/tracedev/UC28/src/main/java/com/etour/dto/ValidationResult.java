package com.etour.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of a validation.
 * Contains a boolean valid flag and list of error messages.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errors;

    public ValidationResult() {
        this.valid = true;
        this.errors = new ArrayList<>();
    }

    public boolean isValid() {
        return valid && errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.valid = false;
        errors.add(error);
    }
}