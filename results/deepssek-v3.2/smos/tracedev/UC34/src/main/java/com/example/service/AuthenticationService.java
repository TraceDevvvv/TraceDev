package com.example.service;

/**
 * Service for validating administrator privileges.
 * This satisfies requirement R3.
 */
public class AuthenticationService {
    /**
     * Validates if a given user has administrator privileges.
     * @param userId the user identifier
     * @return true if the user is an administrator, false otherwise
     */
    public boolean validateAdmin(String userId) {
        // In a real implementation, this would check against a user directory or database
        // For demonstration, assume userId "admin" is an administrator
        return "admin".equals(userId);
    }
}