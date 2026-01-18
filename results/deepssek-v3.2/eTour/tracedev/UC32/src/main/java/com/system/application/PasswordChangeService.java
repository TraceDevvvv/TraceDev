package com.system.application;

import com.system.domain.ValidationResult;
import com.system.domain.PasswordEntity;
import com.system.ports.PasswordValidator;

/**
 * Application service implementing business logic for password change.
 */
public class PasswordChangeService implements PasswordValidator {
    
    private PasswordEntity passwordEntity;

    public PasswordChangeService(PasswordEntity passwordEntity) {
        this.passwordEntity = passwordEntity;
    }

    /**
     * Validates and changes the password.
     * Returns ValidationResult indicating success or specific error.
     */
    public ValidationResult validateAndChange(String currentPasswordHash, 
                                              String newPasswordPlain, 
                                              String confirmationPlain) {
        // Validate new password and confirmation
        ValidationResult validationResult = validate(newPasswordPlain, confirmationPlain);
        if (validationResult != ValidationResult.SUCCESS) {
            // As per sequence diagram: current password validation omitted on confirmation mismatch
            return validationResult;
        }

        // Check if new password is different from current (after confirmation passes)
        String newHash = passwordEntity.hashPassword(newPasswordPlain);
        if (currentPasswordHash.equals(newHash)) {
            return ValidationResult.SAME_AS_CURRENT;
        }

        // Update password
        passwordEntity.updatePassword(newHash);
        return ValidationResult.SUCCESS;
    }

    /**
     * Implements PasswordValidator interface.
     * Validates new password and confirmation.
     */
    @Override
    public ValidationResult validate(String newPasswordPlain, String confirmationPlain) {
        // Check confirmation match
        if (!newPasswordPlain.equals(confirmationPlain)) {
            return ValidationResult.CONFIRMATION_MISMATCH;
        }

        // Check password strength (simplified example)
        if (newPasswordPlain.length() < 8) {
            return ValidationResult.WEAK_PASSWORD;
        }

        return ValidationResult.SUCCESS;
    }
}