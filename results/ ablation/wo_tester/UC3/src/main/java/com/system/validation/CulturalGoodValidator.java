package com.system.validation;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * Validates cultural good form data.
 */
public class CulturalGoodValidator implements IValidator {
    @Override
    public ValidationResult validate(Map<String, Object> formData) {
        List<String> errors = new ArrayList<>();

        // Validate required fields
        if (!formData.containsKey("name") || formData.get("name") == null || ((String) formData.get("name")).trim().isEmpty()) {
            errors.add("Name is required.");
        }
        if (!formData.containsKey("description") || formData.get("description") == null || ((String) formData.get("description")).trim().isEmpty()) {
            errors.add("Description is required.");
        }
        if (!formData.containsKey("category") || formData.get("category") == null || ((String) formData.get("category")).trim().isEmpty()) {
            errors.add("Category is required.");
        }
        if (!formData.containsKey("location") || formData.get("location") == null || ((String) formData.get("location")).trim().isEmpty()) {
            errors.add("Location is required.");
        }

        // Additional business rules could be added here

        boolean isValid = errors.isEmpty();
        return new ValidationResult(isValid, errors);
    }
}