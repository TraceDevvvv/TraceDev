package com.restaurant.menu.validation;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the result of a validation operation.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errors;

    public ValidationResult(boolean isValid, List<String> errors) {
        this.valid = isValid;
        this.errors = errors != null ? errors : new ArrayList<>();
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }
}