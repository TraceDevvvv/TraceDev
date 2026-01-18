package com.example.form;

import com.example.dto.ChangePasswordRequest;

/**
 * Represents the UI form for password change.
 */
public class PasswordChangeForm {
    public String oldPassword;
    public String newPassword;
    public String confirmPassword;

    // In a real application, these would be bound from HTML form fields.

    /**
     * Validates the form data.
     *
     * @return true if the form is valid
     */
    public boolean validate() {
        // Simple validation: non‑empty and new password matches confirmation
        return oldPassword != null && !oldPassword.trim().isEmpty() &&
               newPassword != null && !newPassword.trim().isEmpty() &&
               confirmPassword != null && !confirmPassword.trim().isEmpty() &&
               newPassword.equals(confirmPassword);
    }

    /**
     * Submits the form (for demonstration, returns true if valid).
     *
     * @return true if submission is successful
     */
    public boolean submit() {
        // In a real app, this would trigger the controller.
        return validate();
    }

    /**
     * Converts this form to a ChangePasswordRequest DTO.
     * Assumes username is obtained from the current session or context.
     *
     * @return a ChangePasswordRequest instance
     */
    public ChangePasswordRequest toChangePasswordRequest() {
        // For demo, we use a fixed username; in a real app, retrieve from session.
        String username = "john_doe";
        return new ChangePasswordRequest(username, oldPassword, newPassword, confirmPassword);
    }
}