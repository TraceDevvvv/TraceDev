package com.example.validation;

import java.util.List;

/**
 * Validator for image data and format.
 */
public class ImageValidator {
    private long maxFileSize;
    private List<String> allowedFormats;

    public ImageValidator(long maxFileSize, List<String> allowedFormats) {
        this.maxFileSize = maxFileSize;
        this.allowedFormats = allowedFormats;
    }

    /**
     * Validates the image data and format.
     */
    public ValidationResult validate(byte[] imageData, String format) {
        if (imageData == null || imageData.length == 0) {
            return new ValidationResult(false, "Image data is empty");
        }
        if (imageData.length > maxFileSize) {
            return new ValidationResult(false, "Image size exceeds limit");
        }
        if (!allowedFormats.contains(format.toLowerCase())) {
            return new ValidationResult(false, "Image format not allowed");
        }
        return new ValidationResult(true, "");
    }
}