package com.example;

/**
 * Represents the result of an authentication attempt.
 */
public class AuthenticationResult {
    private boolean success;
    private User user;
    private String errorMessage;

    /**
     * Constructs an AuthenticationResult.
     * @param success true if authentication succeeded
     * @param user the authenticated user (nullable)
     * @param errorMessage error message if failed
     */
    public AuthenticationResult(boolean success, User user, String errorMessage) {
        this.success = success;
        this.user = user;
        this.errorMessage = errorMessage;
    }

    /**
     * Checks if authentication succeeded.
     * @return true if successful
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the authenticated user (if successful).
     * @return the User object, or null if failed
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the error message (if failed).
     * @return the error message, or null if successful
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}