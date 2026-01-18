package com.example.validation;

/**
 * Validates that the new password matches the confirmation.
 */
public class PasswordConfirmationStrategy implements PasswordValidationStrategy {

    /**
     * Validates that the new password and confirmation are equal.
     * @param newPassword the new password
     * @param confirmation the password confirmation
     * @return true if passwords match, false otherwise
     */
    @Override
    public boolean validate(String newPassword, String confirmation) {
        return newPassword != null && newPassword.equals(confirmation);
    }
}