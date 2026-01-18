package com.example.model;

import java.util.Map;

/**
 * Represents an agency operator who can log in and out.
 * Precondition for deletion flow: operator must be logged in.
 */
public class AgencyOperator {
    private boolean loggedIn;

    public boolean login(Map<String, String> credentials) {
        // Simplified login: assume success if credentials contain "username" and "password"
        if (credentials != null && credentials.containsKey("username") && credentials.containsKey("password")) {
            loggedIn = true;
            return true;
        }
        loggedIn = false;
        return false;
    }

    public void logout() {
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}