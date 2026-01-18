package com.example.session;

/**
 * Utility class managing the operator's session.
 */
public class Session {
    private boolean loggedIn;
    private String operatorId;

    public Session() {
        this.loggedIn = false;
        this.operatorId = null;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getOperatorId() {
        return operatorId;
    }

    /**
     * Simulates login with credentials.
     * For simplicity, any non-null credentials log in as "operator1".
     * @param credentials the login credentials
     * @return true if login successful, false otherwise.
     */
    public boolean login(String credentials) {
        if (credentials != null && !credentials.trim().isEmpty()) {
            loggedIn = true;
            operatorId = "operator1"; // default operator ID
            return true;
        }
        return false;
    }

    public void logout() {
        loggedIn = false;
        operatorId = null;
    }
}