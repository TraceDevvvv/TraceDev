package com.system.validation;

import java.util.List;
import java.util.ArrayList;

/**
 * Contains the result of a validation operation.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors != null ? errors : new ArrayList<>();
    }

    public boolean getIsValid() {
        return isValid;
    }

    public List<String> getErrors() {
        return errors;
    }
}