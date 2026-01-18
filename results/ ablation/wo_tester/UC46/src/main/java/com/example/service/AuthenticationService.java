package com.example.service;

/**
 * Service for authentication checks.
 */
public class AuthenticationService {
    // Checks if the tourist is authenticated.
    public boolean isAuthenticated(String touristId) {
        // In a real system, this would validate tokens or session.
        // For simplicity, we assume all provided IDs are authenticated.
        return touristId != null && !touristId.trim().isEmpty();
    }
}