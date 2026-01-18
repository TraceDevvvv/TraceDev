package com.example.agencyapp.service;

import com.example.agencyapp.entity.User;
import com.example.agencyapp.repository.UserRepository;

/**
 * Service class handling password change logic.
 */
public class PasswordChangeService {
    private UserRepository userRepository;

    public PasswordChangeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Changes the password for the logged-in agency operator.
     * Validates that the new password is not null/empty and matches confirmation.
     *
     * @param loggedInUser The currently logged-in user (must be Agency Operator).
     * @param currentPassword The current password for verification.
     * @param newPassword The new password to set.
     * @param confirmPassword Confirmation of the new password.
     * @return true if password change is successful, false otherwise.
     * @throws IllegalArgumentException if inputs are invalid.
     */
    public boolean changePassword(User loggedInUser, String currentPassword,
                                  String newPassword, String confirmPassword) {
        // Validate inputs
        if (loggedInUser == null) {
            throw new IllegalArgumentException("User must be logged in.");
        }
        if (!"AGENCY_OPERATOR".equals(loggedInUser.getRole())) {
            throw new IllegalArgumentException("Only Agency Operator can change password.");
        }
        if (currentPassword == null || currentPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Current password cannot be empty.");
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("New password cannot be empty.");
        }
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("New password and confirmation do not match.");
        }
        // Verify current password matches
        if (!loggedInUser.getPassword().equals(currentPassword)) {
            return false;
        }
        // Update password
        loggedInUser.setPassword(newPassword);
        return userRepository.updateUser(loggedInUser);
    }
}