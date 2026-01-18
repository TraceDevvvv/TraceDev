package com.agency.presentation;

import com.agency.application.ChangePasswordController;

/**
 * Presentation layer boundary class representing the password change form.
 * Interacts with the Agency Operator actor and the ChangePasswordController.
 */
public class ChangePasswordForm {
    private ChangePasswordController controller;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public ChangePasswordForm(ChangePasswordController controller) {
        this.controller = controller;
    }

    /**
     * Displays the password change form to the user (UI simulation).
     * This method simulates the UI rendering.
     */
    public void displayForm() {
        System.out.println("Displaying password change form...");
        // In a real UI, this would render the form components.
    }

    /**
     * Simulates user input: retrieves the entered old password.
     * @return the old password as entered by the user.
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Simulates user input: retrieves the entered new password.
     * @return the new password as entered by the user.
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Simulates user input: retrieves the entered confirmation password.
     * @return the confirmation password as entered by the user.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the password fields (simulates user entering data).
     * This is a helper for simulation; in real UI, fields would be set via user input.
     */
    public void setPasswordFields(String oldPass, String newPass, String confirmPass) {
        this.oldPassword = oldPass;
        this.newPassword = newPass;
        this.confirmPassword = confirmPass;
    }

    /**
     * Called when the user presses the "Change Password" button.
     * Retrieves the input and delegates to the controller.
     * This method corresponds to step 4 in the sequence diagram.
     */
    public void onPasswordChangeRequest() {
        System.out.println("Change Password button pressed.");
        // Retrieve input (simulating UI field reads)
        oldPassword = getOldPassword();
        newPassword = getNewPassword();
        confirmPassword = getConfirmPassword();
        // Delegate to controller
        boolean success = controller.handlePasswordChange(oldPassword, newPassword, confirmPassword);
        if (success) {
            System.out.println("Password change process returned success.");
        } else {
            System.out.println("Password change process returned failure.");
        }
    }

    /**
     * Displays a success message to the user.
     * Corresponds to showSuccess in the sequence diagram.
     */
    public void showSuccess() {
        System.out.println("SUCCESS: Password changed successfully!");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Displays a connection error message to the user.
     * Corresponds to Exit Condition 2 in the sequence diagram.
     */
    public void showConnectionError() {
        System.out.println("CONNECTION ERROR: Database connection lost. Please try again later.");
    }
}