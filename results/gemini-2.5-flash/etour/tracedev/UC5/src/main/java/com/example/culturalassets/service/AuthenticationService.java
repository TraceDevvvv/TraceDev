package com.example.culturalassets.service;

/**
 * Service for handling user authentication.
 * This is a dummy implementation for demonstration purposes.
 */
public class AuthenticationService {

    /**
     * Checks if a given session ID corresponds to an authenticated user.
     * For demonstration, it returns true if the sessionId is not "invalid".
     * @param sessionId The session identifier to check.
     * @return true if the session is authenticated, false otherwise.
     */
    public boolean isAuthenticated(String sessionId) {
        System.out.println("AuthService: Checking authentication for session ID: " + sessionId);
        // Simulate a logged-in user unless the session ID is explicitly "invalid"
        if ("invalid".equals(sessionId)) {
            System.out.println("AuthService: Session " + sessionId + " is NOT authenticated.");
            return false;
        }
        System.out.println("AuthService: Session " + sessionId + " IS authenticated.");
        return true;
    }
}