package com.example.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Result of validation, containing success flag and error messages.
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errorMessages;

    public ValidationResult(boolean isValid) {
        this.valid = isValid;
        this.errorMessages = new ArrayList<>();
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void addErrorMessage(String message) {
        this.errorMessages.add(message);
    }
}