package com.example.administrator;

import com.example.authentication.AuthenticationService;

/**
 * Represents an Administrator actor.
 */
public class Administrator {
    private boolean isLoggedIn;
    private AuthenticationService authService;

    public Administrator(AuthenticationService authService) {
        this.authService = authService;
        this.isLoggedIn = false;
    }

    public boolean login(String credentials) {
        // Assuming credentials validation is done by AuthenticationService
        // For simplicity, we set logged in to true if credentials are not null/empty.
        this.isLoggedIn = credentials != null && !credentials.trim().isEmpty();
        return this.isLoggedIn;
    }

    public void logout() {
        this.isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }
}