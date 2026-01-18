package com.example.application;

/**
 * Interface for authentication service.
 */
public interface AuthenticationService {
    /**
     * Checks if a user is authenticated.
     * @param userId the user identifier
     * @return true if authenticated, false otherwise
     */
    boolean isAuthenticated(String userId);
}