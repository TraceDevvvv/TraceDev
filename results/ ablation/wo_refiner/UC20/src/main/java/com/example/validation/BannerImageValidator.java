package com.example.validation;

import com.example.model.Banner;

/**
 * Validates banner images (REQ-009).
 */
public class BannerImageValidator {

    /**
     * Validates the image according to size, dimensions, and format.
     * Sequence diagram steps 23-26.
     */
    public ValidationResult validateImage(Banner banner) {
        // Validate image size (e.g., max 10MB).
        if (!validateImageSize(banner.getFileSize())) {
            return new ValidationResult(false, "VALIDATION_SIZE", "Image size exceeds limit.");
        }

        // Validate image dimensions (e.g., min 100x100, max 2000x2000).
        if (!validateImageDimensions(banner.getWidth(), banner.getHeight())) {
            return new ValidationResult(false, "VALIDATION_DIMENSIONS", "Image dimensions invalid.");
        }

        // Validate image format (e.g., JPEG, PNG).
        if (!validateImageFormat(banner.getImageType())) {
            return new ValidationResult(false, "VALIDATION_FORMAT", "Unsupported image format.");
        }

        return new ValidationResult(true, null, "Image is valid.");
    }

    public boolean validateImageSize(Long fileSize) {
        // Assume max size is 10MB.
        final long MAX_SIZE = 10 * 1024 * 1024;
        return fileSize != null && fileSize > 0 && fileSize <= MAX_SIZE;
    }

    public boolean validateImageDimensions(Integer width, Integer height) {
        if (width == null || height == null) {
            return false;
        }
        final int MIN_DIM = 100;
        final int MAX_DIM = 2000;
        return width >= MIN_DIM && width <= MAX_DIM &&
               height >= MIN_DIM && height <= MAX_DIM;
    }

    public boolean validateImageFormat(String imageType) {
        if (imageType == null) return false;
        String lower = imageType.toLowerCase();
        return lower.equals("jpeg") || lower.equals("jpg") || lower.equals("png");
    }
}