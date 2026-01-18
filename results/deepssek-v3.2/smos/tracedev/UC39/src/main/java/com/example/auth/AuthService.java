package com.example.auth;

import com.example.presentation.Administrator;

/**
 * Service for authentication and session management.
 * Singleton for simplicity.
 */
public class AuthService {
    private static AuthService instance = new AuthService();
    private Administrator currentAdmin;

    private AuthService() {
        // Private constructor for singleton.
        currentAdmin = null;
    }

    public static AuthService getInstance() {
        return instance;
    }

    /**
     * Checks if an administrator is logged in.
     * @return true if an admin is logged in, false otherwise
     */
    public boolean isAdminLoggedIn() {
        return currentAdmin != null && currentAdmin.isAuthenticated();
    }

    /**
     * Logs in an administrator.
     * @param admin the administrator to log in
     */
    public void login(Administrator admin) {
        if (admin != null) {
            admin.setAuthenticated(true);
            currentAdmin = admin;
            System.out.println("AuthService: Administrator logged in: " + admin.getUsername());
        }
    }

    /**
     * Logs out the current administrator.
     */
    public void logout() {
        if (currentAdmin != null) {
            currentAdmin.setAuthenticated(false);
            System.out.println("AuthService: Administrator logged out: " + currentAdmin.getUsername());
            currentAdmin = null;
        }
    }
}