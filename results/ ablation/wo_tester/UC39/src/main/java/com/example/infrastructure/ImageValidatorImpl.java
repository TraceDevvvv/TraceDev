package com.example.infrastructure;

/**
 * Implementation of ImageValidator.
 */
public class ImageValidatorImpl implements ImageValidator {
    
    @Override
    public boolean validate(ImageData imageData) {
        // Combines dimension and file size validation
        return validateDimensions(imageData) && validateFileSize(imageData);
    }
    
    @Override
    public boolean validateDimensions(ImageData imageData) {
        // Simplified validation: assume dimensions are acceptable if file exists
        // In real implementation, would check width/height
        return imageData != null && imageData.getFileName() != null;
    }
    
    @Override
    public boolean validateFileSize(ImageData imageData) {
        // Check if file size is within limit (e.g., 5MB)
        long maxSize = 5 * 1024 * 1024; // 5MB in bytes
        return imageData != null && imageData.getFileSize() <= maxSize;
    }
}