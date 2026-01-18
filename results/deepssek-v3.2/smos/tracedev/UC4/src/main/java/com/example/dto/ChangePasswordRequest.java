package com.example.dto;

/**
 * Data Transfer Object for password change request.
 */
public class ChangePasswordRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    // Constructor
    public ChangePasswordRequest(String username, String oldPassword, String newPassword, String confirmPassword) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}