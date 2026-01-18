package com.example.domain;

import com.example.application.ImageValidationResult;

/**
 * Domain service for validating image characteristics.
 * Part of the Domain Layer.
 */
public class ImageValidator {

    /**
     * Validates the overall characteristics of an image.
     * This method combines various validation checks.
     * @param imageData The byte array of the image to validate.
     * @return An ImageValidationResult indicating the validation outcome.
     */
    public ImageValidationResult validate(byte[] imageData) {
        System.out.println("IV: Performing overall image validation.");
        if (imageData == null || imageData.length == 0) {
            return ImageValidationResult.INVALID_CHARACTERISTICS;
        }

        // Combine checks for size and format, and potentially other aspects
        boolean sizeOk = validateImageSize(imageData);
        boolean formatOk = validateImageFormat(imageData);
        // Add more complex validation logic here if needed, e.g., content analysis

        if (sizeOk && formatOk /* && other_checks_ok */) {
            return ImageValidationResult.VALID;
        } else {
            return ImageValidationResult.INVALID_CHARACTERISTICS;
        }
    }

    /**
     * Validates the size characteristics of an image.
     * REQ-CD-004: Added validateImageSize method.
     * (Placeholder implementation)
     * @param imageData The byte array of the image.
     * @return True if the image size is valid, false otherwise.
     */
    public boolean validateImageSize(byte[] imageData) { // REQ-CD-004
        System.out.println("IV: Validating image size.");
        // Mock implementation: Assume size is valid if data exists and is within a reasonable range (e.g., 1KB to 5MB)
        if (imageData == null || imageData.length == 0) {
            return false;
        }
        int minSize = 1024; // 1KB
        int maxSize = 5 * 1024 * 1024; // 5MB
        return imageData.length >= minSize && imageData.length <= maxSize;
    }

    /**
     * Validates the format characteristics of an image.
     * REQ-CD-005: Added validateImageFormat method.
     * (Placeholder implementation)
     * @param imageData The byte array of the image.
     * @return True if the image format is valid, false otherwise.
     */
    public boolean validateImageFormat(byte[] imageData) { // REQ-CD-005
        System.out.println("IV: Validating image format.");
        // Mock implementation: Assume format is valid if it somewhat resembles a JPEG or PNG header
        // This is a very simplistic check; real validation would involve image libraries.
        if (imageData == null || imageData.length < 8) {
            return false;
        }
        // Check for JPEG magic numbers: FF D8 FF
        if (imageData[0] == (byte) 0xFF && imageData[1] == (byte) 0xD8 && imageData[2] == (byte) 0xFF) {
            return true;
        }
        // Check for PNG magic numbers: 89 50 4E 47 0D 0A 1A 0A
        if (imageData[0] == (byte) 0x89 && imageData[1] == (byte) 'P' && imageData[2] == (byte) 'N' &&
            imageData[3] == (byte) 'G' && imageData[4] == (byte) 0x0D && imageData[5] == (byte) 0x0A &&
            imageData[6] == (byte) 0x1A && imageData[7] == (byte) 0x0A) {
            return true;
        }
        // Default to false for unknown/unsupported formats
        return false;
    }
}