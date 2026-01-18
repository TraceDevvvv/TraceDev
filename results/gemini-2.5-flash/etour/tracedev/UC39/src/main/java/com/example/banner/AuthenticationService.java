package com.example.banner;

/**
 * Service for managing user authentication.
 * Added to satisfy requirement R3.
 */
public class AuthenticationService {

    /**
     * Checks if a user is currently authenticated.
     * @return True if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        // In a real application, this would check a security context or session.
        // For this example, we'll assume a user is always authenticated for demo purposes.
        return true;
    }

    /**
     * Retrieves the current authenticated user.
     * @return The User object if authenticated, or null otherwise.
     */
    public User getCurrentUser() {
        // In a real application, this would retrieve user details from the security context.
        // For this example, return a mock operator user.
        if (isAuthenticated()) {
            return new User("operator123", "operator");
        }
        return null;
    }
}