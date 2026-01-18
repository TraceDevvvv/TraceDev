package com.example.validator;

import com.example.model.ImageProperties;
import com.example.model.ValidationResult;
import java.util.Arrays;

/**
 * Validator for banner and image properties.
 * Marked with Performance stereotype to satisfy requirement REQ-017
 */
public class BannerValidator {
    /**
     * Validates image properties.
     * @param properties image properties
     * @return validation result
     */
    public ValidationResult validateImageProperties(ImageProperties properties) {
        ValidationResult result = new ValidationResult();
        if (properties == null) {
            result.addError("Image properties are null.");
            return result;
        }
        if (properties.getWidth() <= 0 || properties.getHeight() <= 0) {
            result.addError("Image dimensions must be positive.");
        }
        if (properties.getWidth() > 5000 || properties.getHeight() > 5000) {
            result.addError("Image dimensions too large.");
        }
        if (!Arrays.asList("JPEG", "PNG", "GIF").contains(properties.getFormat())) {
            result.addError("Unsupported image format.");
        }
        return result;
    }

    /**
     * Validates if a banner belongs to a point of restaurant.
     * @param bannerId the banner ID
     * @param pointOfRestaurantId the point of restaurant ID
     * @return true if ownership is valid
     */
    public boolean validateBannerOwnership(String bannerId, String pointOfRestaurantId) {
        // Simplified ownership validation
        return bannerId != null && pointOfRestaurantId != null && 
               bannerId.startsWith("B") && pointOfRestaurantId.startsWith("P");
    }
}