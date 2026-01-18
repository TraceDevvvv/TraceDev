package com.example.adapters;

import com.example.domain.ImageMetadata;
import com.example.exceptions.ConnectionException;
import com.example.ports.ImageValidator;
import com.example.ports.ValidationResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ImageValidator.
 * Validates image metadata according to business rules.
 */
public class ImageValidatorImpl implements ImageValidator {
    @Override
    public ValidationResult validate(ImageMetadata imageMetadata) {
        // Simulate potential connection error
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ConnectionException("Connection lost during validation");
        }
        
        List<String> errors = new ArrayList<>();
        
        // Basic validation rules
        if (imageMetadata.getWidth() <= 0) {
            errors.add("Image width must be positive");
        }
        if (imageMetadata.getHeight() <= 0) {
            errors.add("Image height must be positive");
        }
        if (imageMetadata.getSize() > 10 * 1024 * 1024) { // 10MB limit
            errors.add("Image size exceeds 10MB limit");
        }
        if (!isSupportedFormat(imageMetadata.getFormat())) {
            errors.add("Unsupported image format: " + imageMetadata.getFormat());
        }
        
        return new ValidationResult(errors.isEmpty(), errors);
    }
    
    private boolean isSupportedFormat(String format) {
        return format != null && 
               (format.equalsIgnoreCase("jpg") || 
                format.equalsIgnoreCase("jpeg") || 
                format.equalsIgnoreCase("png") ||
                format.equalsIgnoreCase("gif"));
    }
}