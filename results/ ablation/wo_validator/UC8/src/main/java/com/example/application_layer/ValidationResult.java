package com.example.application_layer;

import java.util.Map;
import java.util.HashMap;

/**
 * Application Layer: Result of validation.
 * Holds errors keyed by field name.
 */
public class ValidationResult {
    private Map<String, String> errors = new HashMap<>();

    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return new HashMap<>(errors);
    }

    public void addError(String field, String error) {
        errors.put(field, error);
    }
}