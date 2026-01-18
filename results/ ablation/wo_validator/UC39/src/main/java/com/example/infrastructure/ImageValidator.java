package com.example.infrastructure;

import com.example.application.results.ValidationResult;

/**
 * Port interface for image validation.
 */
public interface ImageValidator {
    ValidationResult validate(byte[] imageData, String contentType);
}