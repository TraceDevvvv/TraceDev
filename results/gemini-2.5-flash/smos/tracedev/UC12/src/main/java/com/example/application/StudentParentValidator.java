package com.example.application;

import com.example.exception.ValidationException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Service responsible for validating Parent and Student IDs.
 */
public class StudentParentValidator {

    // Simple regex for ID format: alphanumeric, 1 to 10 characters.
    private static final Pattern ID_PATTERN = Pattern.compile("^[a-zA-Z0-9]{1,10}$");

    /**
     * Validates a single parent ID.
     * Sequence Diagram: m17 - Service to Validator: validateParentId(parentId)
     * Sequence Diagram: m43 - Service to Validator: validateParentId(parentId) (part of validation calls within manageStudentAssociations)
     * @param id The parent ID to validate.
     * @throws ValidationException if the ID is null, empty, or does not match the expected format.
     */
    public void validateParentId(String id) throws ValidationException {
        System.out.println("[Validator] Validating parent ID: " + id);
        if (id == null || id.trim().isEmpty()) {
            throw new ValidationException("Parent ID cannot be null or empty.");
        }
        if (!ID_PATTERN.matcher(id).matches()) {
            throw new ValidationException("Invalid Parent ID format: " + id);
        }
        // In a real system, you might check if the parent ID actually exists in a Parent repository.
        System.out.println("[Validator] Parent ID " + id + " is valid.");
        // Sequence Diagram: m18 - Validator to Service: validationSuccess (implicit return)
    }

    /**
     * Validates a list of student IDs.
     * Sequence Diagram: m19 - Service to Validator: validateStudentIds(newStudentIds)
     * Sequence Diagram: m46, m48 - Validator to Service: validationSuccess (implicit return for multiple student validations)
     * @param ids The list of student IDs to validate.
     * @throws ValidationException if the list is null, contains null/empty IDs, or any ID does not match the expected format.
     */
    public void validateStudentIds(List<String> ids) throws ValidationException {
        System.out.println("[Validator] Validating student IDs: " + ids);
        if (ids == null) {
            throw new ValidationException("Student ID list cannot be null.");
        }
        for (String id : ids) {
            if (id == null || id.trim().isEmpty()) {
                throw new ValidationException("Student ID cannot be null or empty within the list.");
            }
            if (!ID_PATTERN.matcher(id).matches()) {
                throw new ValidationException("Invalid Student ID format: " + id);
            }
            // In a real system, you might check if each student ID actually exists in a Student repository.
        }
        System.out.println("[Validator] Student IDs " + ids + " are valid.");
        // Sequence Diagram: m20 - Validator to Service: validationSuccess (implicit return)
    }
}