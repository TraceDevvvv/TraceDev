package com.example;

/**
 * Manages user session and authentication state.
 * Added to satisfy requirement REQ-003.
 */
public class SessionManager {
    private User currentUser;
    private boolean isAuthenticated;

    public SessionManager() {
        this.isAuthenticated = false;
    }

    /**
     * Gets the currently logged-in user.
     * @return current user or null if no user is logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return isAuthenticated;
    }

    /**
     * Validates the current session.
     * @return true if session is valid, false otherwise.
     */
    private boolean validateSession() {
        return isAuthenticated && currentUser != null;
    }

    // Simulate login for the use case precondition
    public void login(User user) {
        this.currentUser = user;
        this.isAuthenticated = true;
    }

    public void logout() {
        this.currentUser = null;
        this.isAuthenticated = false;
    }
}