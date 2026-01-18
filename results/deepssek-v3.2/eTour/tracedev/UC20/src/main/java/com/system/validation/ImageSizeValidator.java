package com.system.validation;

import com.system.Image;

/**
 * Validates that an image size is within the allowed limit.
 */
public class ImageSizeValidator extends ValidationRule {
    private long maxSize;

    public ImageSizeValidator(long maxSize) {
        this.maxSize = maxSize;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public ValidationResult validate(Object input) {
        if (!(input instanceof Image)) {
            return new ValidationResult(false, "Input must be an Image.");
        }
        Image image = (Image) input;
        boolean valid = image.getSize() <= maxSize;
        String message = valid ? "Size is valid." : "Image size exceeds maximum of " + maxSize + " bytes.";
        return new ValidationResult(valid, message);
    }

    @Override
    public String getErrorMessage() {
        return "Image size validation failed.";
    }
}