package com.example.validator;

/**
 * Validates password change requirements.
 */
public class PasswordValidator {
    
    /**
     * Validate password change including current password check.
     * @param currentPassword the current password
     * @param newPassword the new password
     * @param confirmPassword the confirmation password
     * @return true if password change is valid
     */
    public boolean validatePasswordChange(String currentPassword, String newPassword, String confirmPassword) {
        // Assumption: In real implementation, this would check current password against stored hash
        // For this example, we assume current password is correct if not empty
        boolean isCurrentPasswordValid = currentPassword != null && !currentPassword.trim().isEmpty();
        boolean isConfirmationValid = validateConfirmation(newPassword, confirmPassword);
        
        return isCurrentPasswordValid && isConfirmationValid;
    }

    /**
     * Validate that new password and confirmation match.
     * @param newPassword the new password
     * @param confirmPassword the confirmation password
     * @return true if passwords match
     */
    public boolean validateConfirmation(String newPassword, String confirmPassword) {
        return newPassword != null && newPassword.equals(confirmPassword);
    }
}