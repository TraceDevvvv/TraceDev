package com.example.service;

import com.example.dto.ConventionRequestDTO;
import com.example.dto.ValidationResult;
import java.util.Map;

/**
 * Service class for validating convention request data.
 * Corresponds to the ConventionValidationService class in the UML diagram.
 */
public class ConventionValidationService {

    /**
     * Default constructor.
     */
    public ConventionValidationService() {
    }

    /**
     * Validates the request data.
     * @param requestData The DTO containing request data.
     * @return ValidationResult object indicating success/failure and errors.
     */
    public ValidationResult validateRequestData(ConventionRequestDTO requestData) {
        System.out.println("Validating request data...");
        ValidationResult result = new ValidationResult(true);
        Map<String, Object> data = requestData.getConventionData();

        // Simple validation: check if data is not empty
        if (data == null || data.isEmpty()) {
            result.addError("Convention data cannot be empty.");
            return result;
        }

        // Example validation: check required fields
        if (!data.containsKey("agencyId")) {
            result.addError("Agency ID is required.");
        }
        if (!data.containsKey("conventionType")) {
            result.addError("Convention type is required.");
        }

        // If errors were added, set isValid to false
        if (!result.getErrors().isEmpty()) {
            // Note: The ValidationResult constructor sets isValid; we need to adjust.
            // For simplicity, we assume ValidationResult has a method to recompute validity.
            // Since we don't have that, we will create a new result with false.
            result = new ValidationResult(false);
            result.getErrors().addAll(result.getErrors()); // This is a simplification.
        }

        return result;
    }
}