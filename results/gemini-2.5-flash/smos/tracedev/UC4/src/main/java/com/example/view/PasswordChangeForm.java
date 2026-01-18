package com.example.view;

/**
 * Represents the data submitted from a password change form.
 * Contains the old password, new password, and confirmation of the new password.
 */
public class PasswordChangeForm {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;

    /**
     * Constructs a new PasswordChangeForm instance.
     *
     * @param oldPassword The user's current (old) password.
     * @param newPassword The user's desired new password.
     * @param confirmNewPassword Confirmation of the new password to ensure accuracy.
     */
    public PasswordChangeForm(String oldPassword, String newPassword, String confirmNewPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    /**
     * Gets the old password.
     * @return The old password string.
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Gets the new password.
     * @return The new password string.
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Gets the confirmed new password.
     * @return The confirmed new password string.
     */
    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    /**
     * Validates the form data.
     * Checks if the new password and confirm new password fields match and are not empty.
     * @return true if the form data is valid, false otherwise.
     */
    public boolean isValid() {
        // Assume non-null/non-empty checks are done in a real UI, focus on matching new passwords.
        if (newPassword == null || newPassword.isEmpty()) {
            System.err.println("[PasswordChangeForm] New password cannot be empty.");
            return false;
        }
        if (!newPassword.equals(confirmNewPassword)) {
            System.err.println("[PasswordChangeForm] New passwords do not match.");
            return false;
        }
        return true;
    }
}