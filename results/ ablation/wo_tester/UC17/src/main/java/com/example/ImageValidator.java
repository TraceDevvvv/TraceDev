package com.example;

/**
 * Interface for validating image data.
 */
public interface ImageValidator {
    ValidationResult validate(ImageData imageData);
}