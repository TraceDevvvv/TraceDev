package com.example.service;

import com.example.dto.ValidationResult;

/**
 * Validates image data for format, size, and dimensions.
 */
public class ImageValidator {
    // Constants for validation
    private static final int MAX_SIZE_BYTES = 5 * 1024 * 1024; // 5MB
    private static final int MIN_WIDTH = 100;
    private static final int MIN_HEIGHT = 100;
    private static final int MAX_WIDTH = 1920;
    private static final int MAX_HEIGHT = 1080;

    public ValidationResult validate(byte[] imageData) {
        // Check format
        if (!checkFormat(imageData)) {
            return new ValidationResult(false, "Invalid image format", 1001);
        }
        
        // Check size
        if (!checkSize(imageData)) {
            return new ValidationResult(false, "Image size too large", 1002);
        }
        
        // Check dimensions (simplified - would require actual image processing)
        if (!checkDimensions(imageData)) {
            return new ValidationResult(false, "Invalid image dimensions", 1003);
        }
        
        return new ValidationResult(true, "Image validation passed", 0);
    }

    public boolean checkFormat(byte[] imageData) {
        // Simplified format check - in reality would check file signatures
        if (imageData == null || imageData.length < 4) return false;
        
        // Check for common image formats (JPEG, PNG, GIF)
        // JPEG: FF D8 FF
        // PNG: 89 50 4E 47
        // GIF: 47 49 46 38
        return (imageData[0] == (byte)0xFF && imageData[1] == (byte)0xD8) || // JPEG
               (imageData[0] == (byte)0x89 && imageData[1] == 'P') ||       // PNG
               (imageData[0] == 'G' && imageData[1] == 'I');                // GIF
    }

    public boolean checkSize(byte[] imageData) {
        return imageData != null && imageData.length <= MAX_SIZE_BYTES;
    }

    public boolean checkDimensions(byte[] imageData) {
        // Simplified dimension check - in reality would decode the image
        // For demonstration, assume all valid images have reasonable size
        return imageData != null && imageData.length > 1000;
    }
}