package com.example.validator;

import com.example.dto.ValidationResult;
import java.util.List;
import java.util.Arrays;

/**
 * Implementation of image validator.
 */
public class ImageValidatorImpl implements ImageValidator {
    private int maxSizeKB;
    private List<String> allowedFormats;

    public ImageValidatorImpl(int maxSizeKB, List<String> allowedFormats) {
        this.maxSizeKB = maxSizeKB;
        this.allowedFormats = allowedFormats;
    }

    @Override
    public ValidationResult validate(byte[] imageData, String imageFormat, int sizeInKB) {
        ValidationResult result = new ValidationResult();
        
        if (imageData == null || imageData.length == 0) {
            result.addError("Image data is empty");
        }
        
        if (imageFormat == null || imageFormat.isEmpty()) {
            result.addError("Image format is required");
        } else if (!allowedFormats.contains(imageFormat.toUpperCase())) {
            result.addError("Image format not allowed. Allowed: " + allowedFormats);
        }
        
        if (sizeInKB <= 0) {
            result.addError("Image size must be positive");
        } else if (sizeInKB > maxSizeKB) {
            result.addError("Image size exceeds maximum allowed: " + maxSizeKB + "KB");
        }
        
        return result;
    }
}