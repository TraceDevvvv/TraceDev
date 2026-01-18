package com.example.core;

/**
 * Service for validating password match.
 */
public class PasswordValidationService {
    
    /**
     * Validates if the provided password matches the confirmed password.
     * @param providedPassword the password provided by the user
     * @param confirmedPassword the password confirmation
     * @return ValidationResult indicating the outcome
     */
    public ValidationResult validatePasswordMatch(String providedPassword, String confirmedPassword) {
        if (providedPassword == null || providedPassword.trim().isEmpty() || 
            confirmedPassword == null || confirmedPassword.trim().isEmpty()) {
            return ValidationResult.PASSWORD_EMPTY;
        }
        if (providedPassword.equals(confirmedPassword)) {
            return ValidationResult.PASSWORDS_MATCH;
        } else {
            return ValidationResult.PASSWORDS_DO_NOT_MATCH;
        }
    }
}