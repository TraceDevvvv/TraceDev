package com.banner.validation;

/**
 * Validates image format, size, and dimensions.
 */
public class ImageValidator {
    
    public ImageValidator() {}

    /**
     * Check if format is among allowed ones.
     */
    public boolean validateFormat(byte[] imageData, String format) {
        // Simplified validation: allowed formats are JPEG, PNG, GIF
        return format != null && (format.equalsIgnoreCase("JPEG") ||
                                  format.equalsIgnoreCase("PNG") ||
                                  format.equalsIgnoreCase("GIF"));
    }

    /**
     * Check if image size is within reasonable limits (max 5MB).
     */
    public boolean validateSize(byte[] imageData) {
        return imageData != null && imageData.length > 0 && imageData.length <= 5 * 1024 * 1024;
    }

    /**
     * In real implementation would decode image and check dimensions.
     * Here we assume any non‑empty image passes.
     */
    public boolean validateDimensions(byte[] imageData) {
        return imageData != null && imageData.length > 10; // placeholder
    }

    /**
     * Run all validations.
     */
    public boolean validateAll(byte[] imageData, String format) {
        return validateFormat(imageData, format) &&
               validateSize(imageData) &&
               validateDimensions(imageData);
    }
}