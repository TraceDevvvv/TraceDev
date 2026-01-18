package com.example.login;

/**
 * Provides serv for login-related operations, such as validating credentials.
 */
public class LoginService {

    /**
     * Validates the provided login credentials.
     *
     * @param credentials The LoginData object containing username and password.
     * @return true if credentials are valid, false otherwise.
     *         For this specific sequence, it always returns false to simulate an incorrect login.
     */
    public boolean validateLoginCredentials(LoginData credentials) {
        System.out.println("[LoginService] Validating credentials for user: " + credentials.getUsername());
        // As per the sequence diagram's entry condition, we simulate incorrect login data.
        // In a real scenario, this would involve checking against a database or authentication system.
        boolean isValid = false; // Always return false for this specific sequence diagram's scenario.
        System.out.println("[LoginService] Validation result: " + isValid);
        return isValid;
    }
}