package com.system.dto;

/**
 * Data Transfer Object for password change request.
 */
public class ChangeRequest {
    public String userId;
    public String currentPasswordPlain;
    public String newPasswordPlain;
    public String confirmationPlain;

    public ChangeRequest() {}

    public ChangeRequest(String userId, String currentPasswordPlain, 
                         String newPasswordPlain, String confirmationPlain) {
        this.userId = userId;
        this.currentPasswordPlain = currentPasswordPlain;
        this.newPasswordPlain = newPasswordPlain;
        this.confirmationPlain = confirmationPlain;
    }
}