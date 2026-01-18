package com.example.service;

import com.example.model.PointOfRestOperator;

/**
 * Interface for authentication serv.
 */
public interface AuthenticationService {
    /**
     * Check if a user is authenticated.
     */
    boolean isAuthenticated(int userId);

    /**
     * Get the current authenticated user.
     */
    PointOfRestOperator getCurrentUser();
}