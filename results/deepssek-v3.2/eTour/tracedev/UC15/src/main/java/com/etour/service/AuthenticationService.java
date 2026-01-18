package com.etour.service;

/**
 * Simulates authentication service.
 * Checks if a user is logged in.
 */
public class AuthenticationService {
    private String currentUserId;

    public AuthenticationService() {
        // Default: no user logged in
        this.currentUserId = null;
    }

    public void login(String userId) {
        this.currentUserId = userId;
        System.out.println("User " + userId + " logged in.");
    }

    public void logout() {
        this.currentUserId = null;
        System.out.println("User logged out.");
    }

    public boolean isLoggedIn(String userId) {
        // For simplicity, we check if any user is logged in
        return currentUserId != null && currentUserId.equals(userId);
    }

    public String getCurrentUser() {
        return currentUserId;
    }
}