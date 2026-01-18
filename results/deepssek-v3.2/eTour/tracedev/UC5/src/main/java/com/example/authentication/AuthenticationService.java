package com.example.authentication;

/**
 * Service responsible for authentication checks.
 * According to the class diagram and sequence diagram pre-conditions,
 * it verifies if a user is logged in.
 */
public class AuthenticationService {
    /**
     * Checks if the user with the given ID is logged in.
     * @param userId the user identifier
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn(String userId) {
        // Simplified implementation: assume user is logged in if userId is not null and not empty.
        // In a real system, this would check a session or token.
        return userId != null && !userId.trim().isEmpty();
    }
}