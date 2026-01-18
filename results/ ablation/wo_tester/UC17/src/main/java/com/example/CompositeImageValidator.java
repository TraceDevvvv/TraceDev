package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Combines multiple validators for comprehensive validation.
 * Must complete validation within 2 seconds as per sequence diagram.
 */
public class CompositeImageValidator implements ImageValidator {
    private List<ImageValidator> validators = new ArrayList<>();

    public void addValidator(ImageValidator validator) {
        validators.add(validator);
    }

    @Override
    public ValidationResult validate(ImageData imageData) {
        long startTime = System.currentTimeMillis();
        
        for (ImageValidator validator : validators) {
            ValidationResult result = validator.validate(imageData);
            if (!result.isValid()) {
                return result;
            }
            
            // Check if validation is taking too long (must complete within 2 seconds)
            if (System.currentTimeMillis() - startTime > 2000) {
                return new ValidationResult(false, "Validation timed out after 2 seconds");
            }
        }
        
        return new ValidationResult(true, null);
    }
}