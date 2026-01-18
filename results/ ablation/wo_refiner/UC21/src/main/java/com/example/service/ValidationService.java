package com.example.service;

/**
 * Service for validating text fields.
 * Stereotype <<reliable>> - must maintain 99.9% availability and max response time < 2s.
 */
public class ValidationService {

    /**
     * Validates that text is not null and not empty after trim.
     */
    public boolean validateText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    /**
     * Validates that a required field is present.
     */
    public boolean validateRequired(String field) {
        return field != null && !field.trim().isEmpty();
    }

    /**
     * Validates that a field's length is within bounds.
     */
    public boolean validateLength(String field, int min, int max) {
        if (field == null) return false;
        int length = field.length();
        return length >= min && length <= max;
    }
}