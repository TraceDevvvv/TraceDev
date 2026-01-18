package com.example.service;

import com.example.entity.Session;
import com.example.entity.User;

/**
 * Authentication/Authorization service.
 * Service class from the Class Diagram.
 */
public class AuthenticationService {
    private Session currentSession;

    public AuthenticationService(Session currentSession) {
        this.currentSession = currentSession;
    }

    /**
     * Checks if a user is currently logged in.
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return currentSession != null && currentSession.isValid();
    }

    /**
     * Gets the current logged-in user.
     * Assumption: returns a dummy user for simplicity. In a real system, this would fetch from a database.
     * @return the current User.
     */
    public User getCurrentUser() {
        if (!isLoggedIn()) {
            throw new IllegalStateException("No user logged in");
        }
        // Dummy implementation - returns a user with ID from session
        return new User(currentSession.getUserId(), "agency_operator");
    }

    public void setCurrentSession(Session session) {
        this.currentSession = session;
    }

    /**
     * Checks login status (sequence diagram message m1).
     * @return true if logged in, false otherwise.
     */
    public boolean checkLoginStatus() {
        return isLoggedIn();
    }
}