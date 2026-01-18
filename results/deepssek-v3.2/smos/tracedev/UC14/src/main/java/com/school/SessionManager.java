package com.school;

/**
 * Manages user session and authentication.
 * Requirement REQ-007 (precondition-related classes)
 */
public class SessionManager {
    private Administrator currentUser;

    public SessionManager(Administrator admin) {
        this.currentUser = admin;
    }

    public boolean isAuthenticated() {
        return currentUser != null && currentUser.isLoggedIn();
    }

    public Administrator getCurrentUser() {
        return currentUser;
    }
}