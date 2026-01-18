package com.example.service;

import com.example.model.Administrator;

/**
 * Service interface for handling user authentication.
 */
public interface AuthenticationService {
    /**
     * Checks if the current user is authenticated.
     * @return true if an administrator is authenticated, false otherwise.
     */
    boolean isAuthenticated();

    /**
     * Retrieves the currently authenticated administrator.
     * @return The Administrator object if authenticated, null otherwise.
     */
    Administrator getCurrentUser();
}