package com.example.validation;

import com.example.dto.ModifyRefreshmentPointRequestDto;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for validating refreshment point modification requests.
 */
public class RefreshmentPointValidator {
    
    public ValidationResult validateRequest(ModifyRefreshmentPointRequestDto request) {
        List<String> errors = new ArrayList<>();
        
        if (request.getId() == null || request.getId().trim().isEmpty()) {
            errors.add("ID is required.");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            errors.add("Name is required.");
        }
        if (request.getLocation() == null || request.getLocation().trim().isEmpty()) {
            errors.add("Location is required.");
        }
        // Additional validations could be added for format constraints.
        
        boolean valid = errors.isEmpty();
        return new ValidationResult(valid, errors);
    }
}