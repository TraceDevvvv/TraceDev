package com.example.etour;

/**
 * Handles login-related errors.
 */
public class LoginErrorHandler {
    /**
     * Handles authentication failure.
     * @param loginDTO the login data.
     * @param errorMessage the error message.
     */
    public void handleAuthenticationFailure(LoginDTO loginDTO, String errorMessage) {
        System.out.println("Authentication failed for user: " + loginDTO.username + " - " + errorMessage);
    }

    /**
     * Handles connection error.
     * @param loginDTO the login data.
     */
    public void handleConnectionError(LoginDTO loginDTO) {
        System.out.println("Connection error during login for user: " + loginDTO.username);
    }

    /**
     * Logs an authentication exception.
     * @param error the exception.
     */
    public void logError(AuthenticationException error) {
        System.out.println("Authentication error: " + error.getErrorCode() + " - " + error.getMessage());
    }
}