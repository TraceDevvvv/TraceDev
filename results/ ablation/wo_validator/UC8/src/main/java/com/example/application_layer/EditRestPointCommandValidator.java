package com.example.application_layer;

import java.util.ArrayList;
import java.util.List;

/**
 * Application Layer: Validator for EditRestPointCommand.
 * Checks required fields and business rules.
 */
public class EditRestPointCommandValidator {
    /**
     * Validates the command and returns a ValidationResult.
     * @param command The command to validate.
     * @return ValidationResult with any errors.
     */
    public ValidationResult validate(EditRestPointCommand command) {
        ValidationResult result = new ValidationResult();
        // Validate required fields.
        List<String> requiredErrors = validateRequiredFields(command);
        for (String error : requiredErrors) {
            result.addError("required", error);
        }
        // Validate business rules.
        List<String> businessErrors = validateBusinessRules(command);
        for (String error : businessErrors) {
            result.addError("business", error);
        }
        return result;
    }

    /**
     * Validates that required fields are present and non‑empty.
     * @param command The command.
     * @return List of error messages.
     */
    public List<String> validateRequiredFields(EditRestPointCommand command) {
        List<String> errors = new ArrayList<>();
        if (command.restPointId == null || command.restPointId.trim().isEmpty()) {
            errors.add("Rest point ID is required.");
        }
        if (command.name == null || command.name.trim().isEmpty()) {
            errors.add("Name is required.");
        }
        if (command.location == null || command.location.trim().isEmpty()) {
            errors.add("Location is required.");
        }
        if (command.capacity <= 0) {
            errors.add("Capacity must be positive.");
        }
        if (command.status == null || command.status.trim().isEmpty()) {
            errors.add("Status is required.");
        }
        return errors;
    }

    /**
     * Validates business rules (e.g., capacity limits, status values).
     * @param command The command.
     * @return List of error messages.
     */
    public List<String> validateBusinessRules(EditRestPointCommand command) {
        List<String> errors = new ArrayList<>();
        // Business rule: capacity cannot exceed 1000.
        if (command.capacity > 1000) {
            errors.add("Capacity cannot exceed 1000.");
        }
        // Business rule: status must be either "active" or "inactive".
        if (!"active".equalsIgnoreCase(command.status) &&
            !"inactive".equalsIgnoreCase(command.status)) {
            errors.add("Status must be 'active' or 'inactive'.");
        }
        return errors;
    }
}