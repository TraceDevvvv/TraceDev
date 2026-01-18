package com.example.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of a validation operation.
 */
public class ValidationResult {
    private boolean valid = true;
    private List<String> errorMessages = new ArrayList<>();

    public boolean isValid() {
        return valid && errorMessages.isEmpty();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void addErrorMessage(String message) {
        valid = false;
        errorMessages.add(message);
    }
}