package com.example.justice.validation;

import com.example.justice.dto.JusticeUpdateDTO;

/**
 * Validator class for JusticeUpdateDTO.
 * Ensures the integrity and accuracy of updated justification data.
 */
public class JusticeValidator {

    /**
     * Validates the fields of a JusticeUpdateDTO.
     * As per the quality requirement, this ensures data integrity.
     *
     * @param dto The JusticeUpdateDTO to validate.
     * @return true if the DTO is valid, false otherwise.
     */
    public boolean validate(JusticeUpdateDTO dto) {
        System.out.println("JusticeValidator: Validating JusticeUpdateDTO for ID: " + dto.getId());

        // Basic validation:
        // 1. ID must not be null or empty
        if (dto.getId() == null || dto.getId().trim().isEmpty()) {
            System.out.println("Validation failed: Justice ID cannot be null or empty.");
            return false;
        }

        // 2. dateJustification must not be null (as it's the primary field being updated here)
        if (dto.getDateJustification() == null) {
            System.out.println("Validation failed: Date justification cannot be null.");
            return false;
        }

        // Add more complex validation rules here if needed
        // e.g., dateJustification must not be in the future,
        // or must be after an initial date, etc.
        // For this use case, a non-null date is sufficient as a basic example.

        System.out.println("Validation successful for JusticeUpdateDTO.");
        return true;
    }
}