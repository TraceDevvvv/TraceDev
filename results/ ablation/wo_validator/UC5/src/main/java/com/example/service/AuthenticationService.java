package com.example.service;

import com.example.model.User;

/**
 * Interface for authentication and authorization serv.
 */
public interface AuthenticationService {

    /**
     * Retrieves the currently logged-in user.
     * @return the current user
     */
    User getCurrentUser();

    /**
     * Checks if a user has a specific role.
     * @param user the user to check
     * @param role the role to verify
     * @return true if the user has the role, false otherwise
     */
    boolean isUserInRole(User user, String role);
}