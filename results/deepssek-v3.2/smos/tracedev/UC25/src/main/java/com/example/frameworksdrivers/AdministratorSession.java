package com.example.frameworksdrivers;

/**
 * Represents the administrator's session.
 * Manages login state.
 */
public class AdministratorSession {
    private boolean isLoggedIn = false;

    public AdministratorSession() {
        // By default, not logged in.
    }

    /**
     * Validates the current session.
     * Returns true if the administrator is logged in.
     */
    public boolean validateSession() {
        return isLoggedIn;
    }

    public void login() {
        isLoggedIn = true;
    }

    public void logout() {
        isLoggedIn = false;
    }
}