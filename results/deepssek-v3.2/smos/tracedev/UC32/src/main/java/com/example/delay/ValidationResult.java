package com.example.delay;

import java.util.List;

/**
 * Result of validation.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errors;

    /**
     * Constructor.
     * @param isValid True if validation passed.
     * @param errors List of error messages.
     */
    public ValidationResult(boolean isValid, List<String> errors) {
        this.valid = isValid;
        this.errors = errors;
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
}