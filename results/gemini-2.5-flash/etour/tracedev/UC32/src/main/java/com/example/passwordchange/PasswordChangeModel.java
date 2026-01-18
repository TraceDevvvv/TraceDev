package com.example.passwordchange;

/**
 * Model class for managing password change data and state.
 * Holds the new password, confirmation, and any current error messages.
 */
public class PasswordChangeModel {
    private String newPassword;
    private String confirmPassword;
    private String currentErrorMessage;

    /**
     * Sets the new password.
     *
     * @param password The new password string.
     */
    public void setNewPassword(String password) {
        this.newPassword = password;
        System.out.println("Model: New password set.");
    }

    /**
     * Sets the confirmation password.
     *
     * @param password The confirmation password string.
     */
    public void setConfirmPassword(String password) {
        this.confirmPassword = password;
        System.out.println("Model: Confirm password set.");
    }

    /**
     * Sets the current error message to be displayed.
     *
     * @param message The error message string.
     */
    public void setCurrentErrorMessage(String message) {
        this.currentErrorMessage = message;
        System.out.println("Model: Error message set: '" + message + "'");
    }

    /**
     * Gets the currently stored new password.
     *
     * @return The new password.
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Gets the currently stored confirmation password.
     *
     * @return The confirmation password.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Gets the current error message.
     *
     * @return The current error message, or null if no error.
     */
    public String getCurrentErrorMessage() {
        return currentErrorMessage;
    }

    /**
     * Clears the current error message.
     */
    public void clearErrorMessage() {
        this.currentErrorMessage = null;
        System.out.println("Model: Error message cleared.");
    }

    /**
     * Checks if the new password and confirm password fields match and are valid.
     *
     * @return true if passwords match and are not empty, false otherwise.
     */
    public boolean isValidConfirmation() {
        // Assumption: Passwords must not be null or empty and must match.
        if (newPassword == null || newPassword.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty()) {
            System.out.println("Model: isValidConfirmation() - Passwords cannot be empty.");
            return false;
        }
        boolean isValid = newPassword.equals(confirmPassword);
        System.out.println("Model: isValidConfirmation() - Passwords match: " + isValid);
        return isValid;
    }
}