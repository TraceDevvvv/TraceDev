package com.example.controller;

import com.example.model.User;

/**
 * Controller for user authentication and login management.
 * Handles user authentication and maintains current user session.
 */
public class LoginController {
    private User currentUser;

    public boolean authenticateUser(String username, String password) {
        // Simplified authentication for demonstration
        // In real implementation, this would validate against a user database
        if ("admin".equals(username) && "password".equals(password)) {
            currentUser = new User(username, "STAFF");
            return true;
        }
        return false;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }
}