package com.example.controller;

import com.example.view.PasswordChangeForm;
import com.example.view.PasswordChangeView;
import com.example.service.UserService;

/**
 * Controller responsible for handling password change requests from the view.
 * It coordinates with the UserService and PasswordChangeView to manage the process.
 */
public class PasswordChangeController {
    private UserService userService;
    private PasswordChangeView passwordChangeView;

    /**
     * Constructs a new PasswordChangeController.
     *
     * @param userService The service responsible for password change business logic.
     * @param passwordChangeView The view responsible for user interaction.
     */
    public PasswordChangeController(UserService userService, PasswordChangeView passwordChangeView) {
        this.userService = userService;
        this.passwordChangeView = passwordChangeView;
    }

    /**
     * Initiates the password change process by instructing the view to display the form.
     * @param userId The ID of the user requesting the password change.
     */
    public void initiatePasswordChange(String userId) {
        System.out.println("\n[Controller] Initiating password change for user: " + userId);
        passwordChangeView.displayForm();
    }

    /**
     * Submits the password change request with data from the form.
     * Handles form validation, calls the UserService, and provides feedback to the view.
     *
     * @param userId The ID of the user changing the password.
     * @param form The PasswordChangeForm containing user's password input.
     */
    public void submitPasswordChange(String userId, PasswordChangeForm form) {
        System.out.println("\n[Controller] Submitting password change for user: " + userId);

        // Check if new passwords match (basic form validation)
        if (!form.isValid()) {
            passwordChangeView.displayErrorMessage("New passwords do not match or are invalid.");
            return; // Exit the sequence flow for invalid form
        }

        // Call the service to perform the actual password change
        boolean success = userService.changePassword(userId, form.getOldPassword(), form.getNewPassword());

        if (success) {
            passwordChangeView.displaySuccessMessage("Password changed successfully.");
        } else {
            // Generic error message, specific details handled by service/view
            passwordChangeView.displayErrorMessage("Failed to change password. Please check your old password or connection.");
        }
    }

    /**
     * Handles the user's cancellation of the password change operation.
     * Notifies the user through the view.
     * @param userId The ID of the user who cancelled.
     */
    public void cancelOperation(String userId) {
        System.out.println("\n[Controller] User " + userId + " cancelled the password change operation.");
        passwordChangeView.displayErrorMessage("Password change operation cancelled.");
    }
}