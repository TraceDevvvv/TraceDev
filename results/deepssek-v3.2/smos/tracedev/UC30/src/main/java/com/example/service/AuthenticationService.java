package com.example.service;

import com.example.model.User;

/**
 * Service for authentication and role validation.
 * Satisfies entry condition: User IS logged in and has Administrator role.
 */
public class AuthenticationService {
    
    /**
     * Gets the currently logged-in user.
     * @return the current user
     */
    public User getCurrentUser() {
        // Simplified implementation: return a dummy user for demonstration.
        return new User("admin", "Administrator");
    }
    
    /**
     * Validates if the current user has the specified role.
     * @param role the role to validate
     * @return true if the user has the role, false otherwise
     */
    public boolean validateRole(String role) {
        User currentUser = getCurrentUser();
        return currentUser != null && role.equals(currentUser.getRole());
    }
}