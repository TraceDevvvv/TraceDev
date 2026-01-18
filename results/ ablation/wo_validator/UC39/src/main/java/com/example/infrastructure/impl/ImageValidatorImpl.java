package com.example.infrastructure.impl;

import com.example.infrastructure.ImageValidator;
import com.example.application.results.ValidationResult;

/**
 * Adapter implementing image validation.
 * For simplicity, it validates based on content type and non‑empty data.
 */
public class ImageValidatorImpl implements ImageValidator {
    @Override
    public ValidationResult validate(byte[] imageData, String contentType) {
        if (imageData == null || imageData.length == 0) {
            return new ValidationResult(false, "Image data is empty.");
        }
        if (contentType == null || !(contentType.startsWith("image/"))) {
            return new ValidationResult(false, "Invalid content type. Must be an image.");
        }
        // Additional checks (e.g., file size, dimensions) could be added here.
        return new ValidationResult(true, null);
    }
}