package com.example.controller;

import com.example.usecase.ChangePasswordUseCase;
import com.example.dto.ChangePasswordRequest;
import com.example.dto.ChangePasswordResponse;
import com.example.form.PasswordChangeForm;

/**
 * MVC Controller for handling password change UI interactions.
 */
public class ChangePasswordController {
    private ChangePasswordUseCase changePasswordUseCase;

    public ChangePasswordController(ChangePasswordUseCase changePasswordUseCase) {
        this.changePasswordUseCase = changePasswordUseCase;
    }

    /**
     * Displays the password change form to the user.
     */
    public void displaysPasswordChangeForm() {
        System.out.println("Displaying password change form.");
        // In a real MVC app, this would return a view name.
    }

    /**
     * Handles submission of the password change form.
     *
     * @param oldPassword the old password
     * @param newPassword the new password
     * @param confirmPassword the confirmation password
     */
    public void submitPasswordChangeForm(String oldPassword, String newPassword, String confirmPassword) {
        System.out.println("ChangePasswordController.submitPasswordChangeForm called");
        // Create form object
        PasswordChangeForm form = new PasswordChangeForm();
        form.oldPassword = oldPassword;
        form.newPassword = newPassword;
        form.confirmPassword = confirmPassword;
        
        // Validate form (client‑side validation is already done, but server‑side too)
        if (!form.validate()) {
            System.out.println("Form validation failed.");
            return;
        }

        // Create request DTO from form
        ChangePasswordRequest request = form.toChangePasswordRequest();

        // Delegate to use case
        ChangePasswordResponse response = changePassword(request);

        // Display result to user
        if (response.isSuccess()) {
            System.out.println("Success: " + response.getMessage());
            // Optional success notification
            redirectToProfilePage();
        } else {
            System.out.println("Error: " + response.getMessage());
        }
    }

    /**
     * Creates a ChangePasswordRequest from form data.
     *
     * @param oldPassword the old password
     * @param newPassword the new password
     * @param confirmPassword the confirmation password
     * @return a ChangePasswordRequest instance
     */
    public ChangePasswordRequest createChangePasswordRequest(String oldPassword, String newPassword, String confirmPassword) {
        // For demo, we use a fixed username; in a real app, retrieve from session.
        String username = "john_doe";
        return new ChangePasswordRequest(username, oldPassword, newPassword, confirmPassword);
    }

    /**
     * Direct method for password change (can be called via API).
     *
     * @param request the change password request
     * @return the change password response
     */
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
        return changePasswordUseCase.execute(request);
    }

    /**
     * Displays an error message to the user.
     */
    public void displayErrorMessage() {
        System.out.println("Displaying error message.");
    }

    /**
     * Displays a success message to the user.
     */
    public void displaySuccessMessage() {
        System.out.println("Displaying success message.");
    }

    /**
     * Handles disconnection or interruption during the password change process.
     */
    public void connectionLost() {
        System.out.println("Handling connection interruption...");
        // Rollback transaction if needed (simulated)
        System.out.println("Rollback transaction if needed.");
        System.out.println("Display connection error to user.");
    }

    /**
     * Cancels the password change process.
     */
    public void cancelPasswordChange() {
        System.out.println("Cancelling password change...");
        // Cleanup resources (simulated)
        System.out.println("Cleaning up resources.");
        System.out.println("Password change cancelled.");
        System.out.println("Show confirmation message to user.");
    }

    /**
     * Tells the user that the password change was cancelled.
     */
    public void passwordChangeCancelled() {
        System.out.println("Password change cancelled.");
    }

    /**
     * Redirects the user to the profile page.
     */
    private void redirectToProfilePage() {
        System.out.println("Redirecting to profile page.");
    }

    // Overloaded method for backward compatibility
    public void submitPasswordChangeForm(PasswordChangeForm form) {
        submitPasswordChangeForm(form.oldPassword, form.newPassword, form.confirmPassword);
    }
}