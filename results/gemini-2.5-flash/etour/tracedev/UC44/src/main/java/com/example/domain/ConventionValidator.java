package com.example.domain;

import com.example.application.ConventionRequestCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for validating Convention-related requests.
 */
public class ConventionValidator {

    /**
     * Validates a ConventionRequestCommand.
     *
     * @param request The command containing the convention details to validate.
     * @return true if the request is valid, false otherwise.
     *         In a real application, this might return a list of validation errors
     *         or throw a specific ValidationException.
     */
    public boolean validate(ConventionRequestCommand request) {
        // Simple validation logic for demonstration purposes.
        // In a real system, this would involve more complex checks.
        if (request == null) {
            System.out.println("Validation Error: Request command is null.");
            return false;
        }
        if (request.getAgreementDetails() == null || request.getAgreementDetails().trim().isEmpty()) {
            System.out.println("Validation Error: Agreement details cannot be null or empty.");
            return false;
        }
        // Additional validation rules could be added here.
        return true;
    }

    /**
     * Helper method to get specific validation errors.
     * This is an assumption based on the sequence diagram showing `validationErrors`.
     *
     * @param request The command to validate.
     * @return A list of error messages. If the list is empty, validation passed.
     */
    public List<String> getValidationErrors(ConventionRequestCommand request) {
        List<String> errors = new ArrayList<>();
        if (request == null) {
            errors.add("Request command is null.");
            return errors;
        }
        if (request.getAgreementDetails() == null || request.getAgreementDetails().trim().isEmpty()) {
            errors.add("Agreement details cannot be null or empty.");
        }
        // Add more validation checks here
        return errors;
    }
}