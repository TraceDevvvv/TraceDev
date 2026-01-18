package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * ValidationResult class that holds the result of a validation.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    public ValidationResult() {
        this.errors = new ArrayList<>();
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(error);
    }
}