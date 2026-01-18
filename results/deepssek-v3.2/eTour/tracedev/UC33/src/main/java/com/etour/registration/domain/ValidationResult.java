package com.etour.registration.domain;

import java.util.List;

/**
 * Result of validation containing success flag and error messages.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errors;

    public ValidationResult(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = errors;
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }
}