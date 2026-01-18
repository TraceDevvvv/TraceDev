package com.example.touristapp.service;

/**
 * Service for handling user authentication.
 * In a real application, this would interact with an identity provider.\n */
public class AuthenticationService {

    /**
     * Checks if a tourist is authenticated.\n     * @param touristId The ID of the tourist to check.\n     * @return true if the tourist is authenticated, false otherwise.\n     */
    public boolean isAuthenticated(String touristId) {
        System.out.println("[AuthenticationService] Checking authentication for tourist ID: " + touristId);
        // Simulate authentication logic
        // For demonstration, let's assume any non-null touristId is authenticated.
        return touristId != null && !touristId.isEmpty();
    }
}