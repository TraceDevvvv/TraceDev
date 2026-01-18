package com.example.dto;

/**
 * Data Transfer Object for password modification request.
 * Contains all necessary fields from the UI form.
 * Traceability: Satisfies R8, R9 (Form data object for password change)
 */
public class ModifyPasswordRequest {
    private String agencyId;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
    
    public ModifyPasswordRequest() {}
    
    public ModifyPasswordRequest(String agencyId, String currentPassword, String newPassword, String confirmPassword) {
        this.agencyId = agencyId;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
    
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
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}