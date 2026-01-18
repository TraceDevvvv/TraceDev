package com.example.validator;

import com.example.model.TeachingDTO;
import com.example.model.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator for TeachingDTO objects.
 * Ensures that the data within a DTO meets business rules before processing.
 */
public class TeachingValidator {

    /**
     * Validates a TeachingDTO.
     * @param teachingDTO The DTO to validate.
     * @return A ValidationResult indicating success or failure with a list of errors.
     */
    public ValidationResult validate(TeachingDTO teachingDTO) {
        List<String> errors = new ArrayList<>();

        if (teachingDTO == null) {
            errors.add("Teaching data cannot be null.");
            return new ValidationResult(false, errors);
        }

        if (teachingDTO.id == null || teachingDTO.id.trim().isEmpty()) {
            errors.add("Teaching ID cannot be empty.");
        }
        if (teachingDTO.name == null || teachingDTO.name.trim().isEmpty()) {
            errors.add("Teaching name cannot be empty.");
        }
        if (teachingDTO.description == null || teachingDTO.description.trim().isEmpty()) {
            errors.add("Teaching description cannot be empty.");
        }

        if (!errors.isEmpty()) {
            return new ValidationResult(false, errors);
        } else {
            return new ValidationResult(true, new ArrayList<>()); // No errors, validation successful
        }
    }
}