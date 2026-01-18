package com.example.service;

import com.example.model.UserCredentials;

/**
 * Service responsible for handling password change operations.
 */
public class PasswordChangeService {

    /**
     * Attempts to change the user's password.
     * Validates that the new password and confirmation match.
     *
     * @param credentials       the user credentials object containing username and current password
     * @param newPassword       the new password entered by the user
     * @param confirmPassword   the confirmation of the new password
     * @return true if the password change is successful, false if confirmation fails
     * @throws IllegalArgumentException if any input is null or empty
     */
    public boolean changePassword(UserCredentials credentials, String newPassword, String confirmPassword) {
        // Validate inputs
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials cannot be null.");
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("New password cannot be null or empty.");
        }
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Confirm password cannot be null or empty.");
        }

        // Check if new password and confirmation match
        if (!newPassword.equals(confirmPassword)) {
            return false; // Confirmation incorrect
        }

        // Update the password in credentials (in a real app, you would also hash it)
        credentials.setPassword(newPassword);
        return true;
    }
}