package com.example.view;

import com.example.dto.ChangePasswordRequest;

/**
 * Represents the form data in the UI.
 */
public class ChangePasswordForm {
    public String currentPassword;
    public String newPassword;
    public String confirmedPassword;

    public ChangePasswordForm(String currentPassword, String newPassword, String confirmedPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmedPassword = confirmedPassword;
    }

    /**
     * Converts the form to a ChangePasswordRequest (step 4 in sequence diagram).
     */
    public ChangePasswordRequest toRequest(String agencyId) {
        return new ChangePasswordRequest(agencyId, currentPassword, newPassword, confirmedPassword);
    }

    // Getters and setters
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}