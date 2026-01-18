package com.newsagency.system.valueobject;

import java.util.List;

/**
 * Value object representing the result of a validation.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    /**
     * Constructor.
     * @param valid true if validation passed
     * @param errors list of error messages (empty if valid)
     */
    public ValidationResult(boolean valid, List<String> errors) {
        this.isValid = valid;
        this.errors = errors;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        this.isValid = valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}