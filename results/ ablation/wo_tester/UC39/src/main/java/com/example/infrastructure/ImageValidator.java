package com.example.infrastructure;

/**
 * Validator for image data.
 */
public interface ImageValidator {
    boolean validate(ImageData imageData);
    boolean validateDimensions(ImageData imageData);   // Added for quality requirement
    boolean validateFileSize(ImageData imageData);     // Added for quality requirement
}