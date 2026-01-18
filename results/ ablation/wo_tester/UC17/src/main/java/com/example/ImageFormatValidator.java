package com.example;

import java.util.List;
import java.util.Arrays;

/**
 * Validates image format constraints.
 */
public class ImageFormatValidator implements ImageValidator {
    private List<String> allowedFormats;

    public ImageFormatValidator(List<String> allowedFormats) {
        this.allowedFormats = allowedFormats;
    }

    @Override
    public ValidationResult validate(ImageData imageData) {
        String mimeType = imageData.getMimeType().toLowerCase();
        for (String format : allowedFormats) {
            if (mimeType.contains(format)) {
                return new ValidationResult(true, null);
            }
        }
        return new ValidationResult(false, "Image format not allowed. Allowed formats: " + allowedFormats);
    }
}