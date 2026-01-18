package com.example.service;

/**
 * Service for validating user credentials.
 */
public class LoginService {
    /**
     * Validates the given username and password.
     * For demonstration, always returns false (invalid) as per sequence diagram.
     * @param username the username
     * @param password the password
     * @return true if credentials are valid, false otherwise
     */
    public boolean validateCredentials(String username, String password) {
        // In a real implementation, this would check against a database or identity provider.
        // According to sequence diagram, returns false for invalid login.
        return false;
    }

    /**
     * Returns false (invalid) as per sequence diagram message m3.
     * This method explicitly implements the return message from LoginSvc to Presenter.
     */
    public boolean returnFalseInvalid() {
        return false;
    }
}