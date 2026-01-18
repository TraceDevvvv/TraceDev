package com.etour.application;

/**
 * Service for authenticating tourist sessions.
 * Satisfies REQ-Auth.
 */
public class AuthenticationService {
    /**
     * Validates the tourist's session.
     * @param touristId the ID of the tourist
     * @return true if session is valid, false otherwise
     */
    public boolean validateSession(String touristId) {
        // Simplified validation: assume session is valid if touristId is not null and not empty.
        return touristId != null && !touristId.trim().isEmpty();
    }
}