package com.tourism.application;

/**
 * Service for authenticating tourists.
 * Required by REQ‑001.
 */
public class AuthenticationService {
    /**
     * Authenticates a tourist by ID.
     * @param touristId the tourist's ID
     * @return true if authenticated, false otherwise
     */
    public boolean authenticate(String touristId) {
        // Simplified authentication logic for demonstration
        // In a real system, this would validate against a user store or token.
        return touristId != null && (touristId.startsWith("TOURIST") || touristId.startsWith("USER"));
    }
}