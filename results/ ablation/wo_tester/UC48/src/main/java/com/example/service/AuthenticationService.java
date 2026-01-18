package com.example.service;

/**
 * Service for authentication operations.
 */
public class AuthenticationService {
    public boolean authenticate(String touristId) {
        System.out.println("Authenticating tourist: " + touristId);
        // For simplicity, assume authentication always succeeds
        return true;
    }
    
    public void logout() {
        System.out.println("Logging out...");
    }
}