package com.example.refreshmentpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator for RefreshmentPointDTO objects.
 * Checks business rules and data integrity for refreshment point data.
 */
public class RefreshmentPointValidator {
    private List<String> errors; // Stores validation error messages

    public RefreshmentPointValidator() {
        this.errors = new ArrayList<>();
    }

    /**
     * Validates the provided RefreshmentPointDTO.
     *
     * @param dto The RefreshmentPointDTO to validate.
     * @return true if the DTO is valid, false otherwise.
     */
    public boolean validate(RefreshmentPointDTO dto) {
        errors.clear(); // Clear previous errors

        if (dto.name == null || dto.name.trim().isEmpty()) {
            errors.add("Name cannot be empty.");
        }
        if (dto.location == null || dto.location.trim().isEmpty()) {
            errors.add("Location cannot be empty.");
        }
        if (dto.type == null || dto.type.trim().isEmpty()) {
            errors.add("Type cannot be empty.");
        }
        // Add more validation rules as needed
        // For example, length constraints, format checks, etc.

        return errors.isEmpty();
    }

    /**
     * Returns a list of error messages from the last validation attempt.
     *
     * @return A list of strings, each representing a validation error.
     */
    public List<String> getErrors() {
        return new ArrayList<>(errors); // Return a copy to prevent external modification
    }
}