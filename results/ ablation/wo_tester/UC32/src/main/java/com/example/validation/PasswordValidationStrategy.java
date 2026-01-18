package com.example.validation;

/**
 * Strategy interface for validating a password change request.
 */
public interface PasswordValidationStrategy {
    /**
     * Validates the new password and its confirmation.
     * @param newPassword the new password
     * @param confirmation the password confirmation
     * @return true if validation passes, false otherwise
     */
    boolean validate(String newPassword, String confirmation);
}