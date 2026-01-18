package com.example;

/**
 * Validates image size constraints.
 */
public class ImageSizeValidator implements ImageValidator {
    private long maxSize;

    public ImageSizeValidator(long maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public ValidationResult validate(ImageData imageData) {
        if (imageData.getSize() > maxSize) {
            return new ValidationResult(false, "Image size exceeds maximum allowed size of " + maxSize + " bytes");
        }
        return new ValidationResult(true, null);
    }
}