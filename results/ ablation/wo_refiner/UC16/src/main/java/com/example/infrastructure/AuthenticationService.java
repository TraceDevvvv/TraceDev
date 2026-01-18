package com.example.infrastructure;

/**
 * Represents entry condition - Added to satisfy requirement REQ-004.
 * Simulates authentication state.
 */
public class AuthenticationService {
    // Simulating session
    private int currentOperatorId = 123; // Example operator ID
    private boolean loggedIn = true;     // Assume logged in for simulation

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public int getCurrentOperatorId() {
        return currentOperatorId;
    }

    // For testing alternative flows
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setCurrentOperatorId(int currentOperatorId) {
        this.currentOperatorId = currentOperatorId;
    }
}