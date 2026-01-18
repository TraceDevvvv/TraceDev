package com.schoolsystem.authentication;

/**
 * Service for authentication and session validation.
 */
public class AuthenticationService {
    // In a real system, SessionManager would be a separate class.
    // For simplicity, we simulate session validation.
    public AuthenticationService() {
    }

    public boolean isLoggedInAsParent(String userId) {
        // Simulated logic: assume true for demo
        return true;
    }

    public boolean validateSession(String sessionId) {
        // Simulated session validation
        return sessionId != null && !sessionId.trim().isEmpty();
    }
}