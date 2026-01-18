package com.example.service;

import com.example.model.Tourist;

/**
 * Simulates authentication service.
 * For demonstration, we assume the Tourist is already authenticated.
 */
public class AuthenticationService {

    // Simulates checking if a Tourist is authenticated.
    public boolean isAuthenticated(Tourist tourist) {
        // In a real system, this would check tokens/sessions.
        // For this demo, we assume the Tourist is authenticated.
        return tourist != null;
    }
}