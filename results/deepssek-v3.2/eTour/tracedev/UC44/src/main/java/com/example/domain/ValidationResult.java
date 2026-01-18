package com.example.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of a validation, including any errors.
 */
public class ValidationResult {
    private boolean isValid = true;
    private List<String> errors = new ArrayList<>();

    public void addError(String error) {
        isValid = false;
        errors.add(error);
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getErrors() {
        return errors;
    }
}