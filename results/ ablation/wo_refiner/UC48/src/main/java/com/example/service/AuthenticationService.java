package com.example.service;

/**
 * Authentication service to authenticate tourists and validate sessions.
 * Added to satisfy requirement 4.
 */
public class AuthenticationService {
    public AuthenticationService() {
    }

    /**
     * Authenticates a tourist by ID.
     * Linked to sequence message m1.
     */
    public boolean authenticate(String touristId) {
        // Simplified authentication logic.
        System.out.println("AuthenticationService: authenticating tourist " + touristId);
        return touristId != null && !touristId.trim().isEmpty();
    }

    /**
     * Validates a session by session ID.
     */
    public boolean validateSession(String sessionId) {
        // Simplified session validation.
        return sessionId != null && sessionId.startsWith("session");
    }
}