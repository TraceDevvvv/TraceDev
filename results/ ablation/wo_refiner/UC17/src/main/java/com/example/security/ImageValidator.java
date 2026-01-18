package com.example.security;

/**
 * Utility class for validating image data.
 * Stereotype <<Security>> as per requirement REQ-016.
 */
public class ImageValidator {
    
    /**
     * Validates the given image data.
     * @param imageData the image data bytes
     * @return true if the image is valid, false otherwise
     */
    public boolean validateImage(byte[] imageData) {
        // For demonstration, we assume a valid image is non‑null and not empty.
        // In a real system, this would check format, size, etc.
        if (imageData == null || imageData.length == 0) {
            return false;
        }
        // Simulate some validation logic
        return imageData.length < 10 * 1024 * 1024; // Reject images larger than 10 MB
    }
}