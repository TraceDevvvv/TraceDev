package com.example;

/**
 * Validates image data based on size constraints.
 * Returns a ValidationResult indicating whether the image is valid.
 */
public class ImageValidator {
    // Maximum size in MB
    private static final int DEFAULT_MAX_SIZE_MB = 5;

    public ValidationResult validateImage(byte[] imageData, int maxSizeMb) {
        // Assumption: validation checks size and possibly format.
        // For simplicity, we check only size here.
        long maxSizeBytes = maxSizeMb * 1024L * 1024L;
        boolean isValid = imageData != null && imageData.length <= maxSizeBytes;
        String message = isValid ? "Image is valid" : "Image exceeds maximum size of " + maxSizeMb + " MB";
        return new ValidationResult(isValid, message);
    }

    // Overloaded method with default max size
    public ValidationResult validateImage(byte[] imageData) {
        return validateImage(imageData, DEFAULT_MAX_SIZE_MB);
    }
}