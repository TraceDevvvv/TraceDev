'''
Handles agency operator authentication for the system
Validates login credentials and manages session state
'''
package com.chatdev.newsapp;
public class AuthenticationService {
    // Hardcoded agency operator credentials for demonstration
    // In a real system, these would come from a secure database
    private static final String AGENCY_USERNAME = "agency_operator";
    private static final String AGENCY_PASSWORD = "secure123";
    /**
     * Authenticates an agency operator
     * @param username The username to validate
     * @param password The password to validate
     * @return true if authentication successful, false otherwise
     */
    public boolean authenticateAgencyOperator(String username, String password) {
        // Simple authentication logic - in production, use secure password hashing
        return AGENCY_USERNAME.equals(username) && AGENCY_PASSWORD.equals(password);
    }
    /**
     * Validates if the provided credentials match agency operator requirements
     * @param username The username to check
     * @param password The password to check
     * @return Validation message, empty string if valid
     */
    public String validateCredentials(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            return "Username cannot be empty";
        }
        if (password == null || password.trim().isEmpty()) {
            return "Password cannot be empty";
        }
        if (username.length() < 3) {
            return "Username must be at least 3 characters";
        }
        if (password.length() < 6) {
            return "Password must be at least 6 characters";
        }
        return ""; // Valid
    }
}