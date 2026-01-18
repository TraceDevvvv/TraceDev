package com.example.builder;

/**
 * Builds error messages for password change scenarios.
 */
public class ErrorMessageBuilder {
    
    /**
     * Build error message for password confirmation mismatch.
     * @return confirmation error message
     */
    public String buildConfirmationError() {
        return "Passwords do not match";
    }

    /**
     * Build error message for password mismatch (e.g., current password incorrect).
     * @return password mismatch error message
     */
    public String buildPasswordMismatchError() {
        return "Current password is incorrect";
    }
}