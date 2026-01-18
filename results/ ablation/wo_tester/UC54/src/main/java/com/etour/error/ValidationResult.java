package com.etour.error;

import java.util.List;

/**
 * Contains the result of a validation, including any errors.
 */
public class ValidationResult {
    private List<ValidationError> errors;

    public ValidationResult(List<ValidationError> errors) {
        this.errors = errors;
    }

    public boolean isValid() {
        return errors == null || errors.isEmpty();
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}