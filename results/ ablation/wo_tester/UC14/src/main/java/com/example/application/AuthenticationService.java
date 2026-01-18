package com.example.application;

/**
 * Handles user authentication.
 */
public class AuthenticationService {
    private boolean sessionActive;

    public AuthenticationService() {
        this.sessionActive = false;
    }

    public boolean isLoggedIn(String user) {
        // Simplified: for demo, we assume sessionActive reflects login status
        return sessionActive;
    }

    public boolean login(String user, String credentials) {
        // Simplified authentication logic
        if (user != null && credentials != null && credentials.length() > 0) {
            sessionActive = true;
            return true;
        }
        return false;
    }

    public void logout(String user) {
        sessionActive = false;
    }
}