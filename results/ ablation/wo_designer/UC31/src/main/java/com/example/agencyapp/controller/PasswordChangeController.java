package com.example.agencyapp.controller;

import com.example.agencyapp.entity.User;
import com.example.agencyapp.service.PasswordChangeService;

/**
 * Controller class handling password change UI flow.
 */
public class PasswordChangeController {
    private PasswordChangeService passwordChangeService;
    private User loggedInUser;

    public PasswordChangeController(PasswordChangeService passwordChangeService, User loggedInUser) {
        this.passwordChangeService = passwordChangeService;
        this.loggedInUser = loggedInUser;
    }

    /**
     * Simulates the password change process as per the use case flow.
     * This method would be called when the Agency Operator initiates the change.
     *
     * @param currentPassword The current password entered.
     * @param newPassword The new password entered.
     * @param confirmPassword The confirmation of the new password.
     * @return A status message indicating success or failure.
     */
    public String processPasswordChange(String currentPassword, String newPassword, String confirmPassword) {
        try {
            boolean success = passwordChangeService.changePassword(loggedInUser,
                    currentPassword, newPassword, confirmPassword);
            if (success) {
                return "Password changed successfully.";
            } else {
                return "Current password is incorrect.";
            }
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            // Simulate connection interruption or other errors
            return "Operation failed due to system error or connection interruption.";
        }
    }
}