package com.example.model;

import com.example.service.PasswordService;

/**
 * Represents an Agency Operator with login credentials.
 * Contains methods for password validation and changing.
 */
public class AgencyOperator {
    private String id;
    private String username;
    private String encryptedPassword;
    
    public AgencyOperator(String id, String username, String encryptedPassword) {
        this.id = id;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
    
    /**
     * Validates the input password against the stored encrypted password.
     * Uses PasswordService to perform the matching.
     * Visibility: protected as per UML diagram.
     * @param inputPassword the raw password input
     * @return true if the password matches, false otherwise
     */
    protected boolean validatePassword(String inputPassword, PasswordService passwordService) {
        return passwordService.matches(inputPassword, this.encryptedPassword);
    }
    
    /**
     * Changes the encrypted password to a new one.
     * @param newEncryptedPassword the new encrypted password
     */
    public void changePassword(String newEncryptedPassword) {
        this.encryptedPassword = newEncryptedPassword;
    }

    /**
     * isValid - corresponds to message m15 in sequence diagram.
     * This is a return message from Agency to MPC (ModifyPasswordController) with a boolean.
     * In the sequence, after validating the password, the Agency returns a boolean.
     * We implement this as a method that returns the validation result.
     */
    public boolean isValid(String inputPassword, PasswordService passwordService) {
        return validatePassword(inputPassword, passwordService);
    }
}