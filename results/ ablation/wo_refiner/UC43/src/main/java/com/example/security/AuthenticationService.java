package com.example.security;

import com.example.user.User;

/**
 * Security component that handles user authentication.
 */
public class AuthenticationService {
    
    /**
     * Simulates checking if a user is authenticated.
     * In a real implementation, this might check a session or token.
     */
    public boolean isUserAuthenticated(String userId) {
        // Assumption: For demonstration, any non-null userId is considered authenticated.
        return userId != null && !userId.trim().isEmpty();
    }

    /**
     * Returns the current user. In a real application, this would
     * retrieve the user from a security context.
     */
    public User getCurrentUser() {
        // Assumption: Return a dummy user for demonstration.
        return new User("user123", "operator", "RESTAURANT_OPERATOR");
    }
}