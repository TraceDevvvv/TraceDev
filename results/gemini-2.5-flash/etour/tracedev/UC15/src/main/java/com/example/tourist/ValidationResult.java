package com.example.tourist;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the result of a validation operation.
 */
public class ValidationResult {
    private boolean isValid;
    private final List<String> errors;

    public ValidationResult() {
        this.isValid = true; // Assume valid until an error is added
        this.errors = new ArrayList<>();
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    /**
     * Adds an error message to the list of errors and sets isValid to false.
     * @param message The error message to add.
     */
    public void addError(String message) {
        this.errors.add(message);
        this.isValid = false; // If there's any error, the result is not valid
    }

    @Override
    public String toString() {
        if (isValid) {
            return "Validation Successful.";
        } else {
            return "Validation Failed with errors: " + String.join(", ", errors);
        }
    }
}