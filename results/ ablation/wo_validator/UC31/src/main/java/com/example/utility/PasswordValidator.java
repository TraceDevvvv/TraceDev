package com.example.utility;

import com.example.value.ValidationResult;

/**
 * Utility class for password validation.
 */
public class PasswordValidator {
    /**
     * Validates the password change request.
     * @param oldPassword the old password
     * @param newPassword the new password
     * @param confirmPassword the confirmation of new password
     * @return ValidationResult indicating success or failure with error message
     */
    public ValidationResult validatePassword(String oldPassword, String newPassword, String confirmPassword) {
        // Check if new password matches confirmation
        if (!newPassword.equals(confirmPassword)) {
            return new ValidationResult(false, "New password and confirmation do not match");
        }

        // Check if new password is different from old password
        if (newPassword.equals(oldPassword)) {
            return new ValidationResult(false, "New password must be different from old password");
        }

        // Validate password strength (example rules)
        if (newPassword.length() < 8) {
            return new ValidationResult(false, "New password must be at least 8 characters long");
        }

        boolean hasUpper = false, hasLower = false, hasDigit = false;
        for (char c : newPassword.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        if (!hasUpper || !hasLower || !hasDigit) {
            return new ValidationResult(false, "New password must contain uppercase, lowercase and digit");
        }

        // All validations passed
        return new ValidationResult(true, null);
    }
}