package com.example.actor;

/**
 * Actor: Guest User
 * Represents a guest user who can log in and log out.
 * Added to satisfy requirement Actors: Guest User
 */
public class GuestUser {
    private String userId;
    private boolean isLoggedIn;

    public GuestUser(String userId) {
        this.userId = userId;
        this.isLoggedIn = false;
    }

    public boolean login(String username, String password) {
        // Simplified authentication logic. In a real application, this would validate credentials.
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            isLoggedIn = true;
            return true;
        }
        return false;
    }

    public void logout() {
        isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getUserId() {
        return userId;
    }
}