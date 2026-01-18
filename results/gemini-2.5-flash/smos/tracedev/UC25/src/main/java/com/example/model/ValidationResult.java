package com.example.model;

import java.util.Collections;
import java.util.List;

/**
 * Represents the result of a validation process, indicating if validation passed
 * and providing a list of error messages if it failed.
 */
public class ValidationResult {
    private final boolean isValid;
    private final List<String> errors;

    /**
     * Constructs a ValidationResult.
     * @param isValid true if validation passed, false otherwise.
     * @param errors a list of error messages. Should be empty if isValid is true.
     */
    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors != null ? Collections.unmodifiableList(errors) : Collections.emptyList();
    }

    /**
     * Checks if there are any errors in the validation result.
     * @return true if there are errors, false otherwise.
     */
    public boolean hasErrors() {
        return !isValid;
    }

    /**
     * Returns the list of error messages.
     * @return an unmodifiable list of error messages.
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Checks if the validation was successful.
     * @return true if valid, false otherwise.
     */
    public boolean isValid() {
        return isValid;
    }
}