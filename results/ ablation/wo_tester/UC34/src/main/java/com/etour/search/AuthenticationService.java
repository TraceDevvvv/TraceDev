package com.etour.search;

/**
 * Service for user authentication.
 * Added to satisfy requirement Entry Conditions: Guest User IS logged on
 */
public class AuthenticationService {
    
    public AuthenticationService() {
        // Constructor
    }

    /**
     * Checks if a user is logged in.
     * @param user The guest user to check.
     * @return true if the user is logged in, false otherwise.
     */
    public boolean isLoggedIn(GuestUser user) {
        return user != null && user.isAuthenticated();
    }

    /**
     * Authenticates a user with credentials.
     * @param userId User identifier.
     * @param password User password.
     * @return true if authentication succeeds, false otherwise.
     */
    public boolean authenticate(String userId, String password) {
        // In a real implementation, this would validate credentials against a database
        return userId != null && !userId.isEmpty() && 
               password != null && !password.isEmpty();
    }
}