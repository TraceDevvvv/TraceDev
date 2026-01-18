package com.example.service;

import java.util.Map;

/**
 * Service for authentication of the agency operator.
 * Simulates login/logout and session management.
 */
public class AuthenticationService {
    private String sessionId;
    private String operatorId;
    private boolean loggedIn;

    public AuthenticationService() {
        this.loggedIn = false;
    }

    /**
     * Simulates login with credentials.
     * For simplicity, assumes any non-empty credentials succeed.
     */
    public boolean login(Map<String, String> credentials) {
        if (credentials != null && !credentials.isEmpty()) {
            this.loggedIn = true;
            this.operatorId = credentials.get("username");
            this.sessionId = "session-" + System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getCurrentOperatorId() {
        return operatorId;
    }

    public void logout() {
        this.loggedIn = false;
        this.operatorId = null;
        this.sessionId = null;
    }
}