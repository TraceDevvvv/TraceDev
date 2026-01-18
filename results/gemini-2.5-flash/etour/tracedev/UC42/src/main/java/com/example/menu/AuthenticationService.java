package com.example.menu;

/**
 * Interface for an authentication service.
 * REQ-R3: Provides a method to check if a user is authenticated.
 */
public interface AuthenticationService {
    /**
     * REQ-R3: Checks if the current user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    boolean isAuthenticated();
}