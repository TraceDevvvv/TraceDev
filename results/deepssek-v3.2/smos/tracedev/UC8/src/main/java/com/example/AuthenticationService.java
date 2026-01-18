package com.example;

/**
 * Service for authentication and authorization.
 */
public class AuthenticationService {
    private User currentUser;
    private boolean sessionActive;

    public AuthenticationService() {
        this.sessionActive = false;
        this.currentUser = null;
    }

    /**
     * Simulates login.
     */
    public boolean login(Object credentials) {
        // Dummy implementation: always succeed and set current user as admin
        this.currentUser = new User("admin", "Admin", "admin@example.com", java.util.Collections.emptyMap());
        this.sessionActive = true;
        return true;
    }

    public boolean isAdmin() {
        // For simplicity, assume the current user is an admin if logged in.
        return sessionActive && currentUser != null;
    }

    public boolean isLoggedIn() {
        return sessionActive;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}