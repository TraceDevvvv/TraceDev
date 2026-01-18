package com.example.dto;

/**
 * Data transfer object for change password request.
 */
public class ChangePasswordRequest {
    public String agencyId;
    public String currentPassword;
    public String newPassword;
    public String confirmedPassword;

    public ChangePasswordRequest(String agencyId, String currentPassword, String newPassword, String confirmedPassword) {
        this.agencyId = agencyId;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmedPassword = confirmedPassword;
    }

    /**
     * Validates the request: checks that new password matches confirmed password and fields are not empty.
     */
    public boolean validate() {
        return agencyId != null && !agencyId.isEmpty() &&
               currentPassword != null && !currentPassword.isEmpty() &&
               newPassword != null && !newPassword.isEmpty() &&
               confirmedPassword != null && !confirmedPassword.isEmpty() &&
               newPassword.equals(confirmedPassword);
    }

    // Getters and setters
    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

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