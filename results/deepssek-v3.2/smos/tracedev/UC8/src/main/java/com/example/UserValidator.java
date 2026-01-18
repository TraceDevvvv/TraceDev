package com.example;

/**
 * Validates user data for editing.
 */
public class UserValidator {
    /**
     * Validates the DTO against the existing user for business rules.
     * For simplicity, we only check that email is not already used by another user.
     * In a real system, this would involve a database query.
     */
    public ValidationResult validateForEdit(EditUserDTO dto, User existingUser) {
        ValidationResult result = new ValidationResult();
        // Example business rule: email must be unique (if changed)
        if (dto.getEmail() != null && !dto.getEmail().equals(existingUser.getEmail())) {
            // In a real application, we would check if another user already has this email.
            // For now, we assume it's okay.
        }
        // Add more business rules as needed.
        return result;
    }
}