package com.example.application;

import com.example.domain.Credentials;

/**
 * Service for handling user authentication.
 * Added to satisfy R3.
 */
public class AuthenticationService {

    /**
     * Authenticates a user with the provided credentials.
     * For demonstration, uses hardcoded credentials.
     * @param credentials The user's credentials.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(Credentials credentials) {
        System.out.println("[AuthService] Attempting to authenticate user: " + credentials.getUsername());
        // Simulate authentication logic
        if ("operator".equals(credentials.getUsername()) && "password".equals(credentials.getPassword())) {
            System.out.println("[AuthService] Authentication successful for " + credentials.getUsername());
            return true;
        } else {
            System.out.println("[AuthService] Authentication failed for " + credentials.getUsername());
            return false;
        }
    }
}