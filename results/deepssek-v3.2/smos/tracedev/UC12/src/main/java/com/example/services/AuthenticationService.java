package com.example.serv;

/**
 * Service for validating user sessions.
 * Corresponds to AuthenticationService in the class diagram.
 */
public class AuthenticationService {
    public boolean validateSession(String userId) {
        // Simulates session validation.
        // In a real system, this would check session tokens, expiry, etc.
        System.out.println("AuthenticationService: Validating session for user " + userId);
        return true; // Assume valid for demonstration.
    }
}