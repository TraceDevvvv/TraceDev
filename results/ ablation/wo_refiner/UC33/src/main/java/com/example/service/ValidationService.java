package com.example.service;

/**
 * Service for validating registration data.
 */
public class ValidationService {
    
    /**
     * Validates email format.
     * @param email the email to validate
     * @return true if email is valid
     */
    public boolean validateEmail(String email) {
        if (email == null) return false;
        // Simple email validation for example
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
    
    /**
     * Validates password strength.
     * @param password the password to validate
     * @return true if password is valid
     */
    public boolean validatePassword(String password) {
        if (password == null) return false;
        // At least 8 characters for example
        return password.length() >= 8;
    }
    
    /**
     * Validates username.
     * @param username the username to validate
     * @return true if username is valid
     */
    public boolean validateUsername(String username) {
        if (username == null) return false;
        // Username must be at least 3 characters
        return username.length() >= 3 && !username.contains(" ");
    }
}