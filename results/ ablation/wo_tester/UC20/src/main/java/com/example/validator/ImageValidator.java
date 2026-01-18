package com.example.validator;

import com.example.dto.ValidationResult;

/**
 * Interface for image validator.
 */
public interface ImageValidator {
    /**
     * Validates image data.
     */
    ValidationResult validate(byte[] imageData, String imageFormat, int sizeInKB);
}