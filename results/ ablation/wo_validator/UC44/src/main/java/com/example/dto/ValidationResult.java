package com.example.dto;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the result of a validation operation.
 * Corresponds to the ValidationResult class in the UML diagram.
 */
public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    /**
     * Constructor.
     * @param isValid Initial validation status.
     */
    public ValidationResult(boolean isValid) {
        this.isValid = isValid;
        this.errors = new ArrayList<>();
    }

    /**
     * Gets the validation status.
     * @return true if valid, false otherwise.
     */
    public boolean getIsValid() {
        return isValid;
    }

    /**
     * Gets the list of validation errors.
     * @return List of error messages.
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Adds an error message and sets isValid to false.
     * @param error The error message.
     */
    public void addError(String error) {
        this.errors.add(error);
        this.isValid = false;
    }
}