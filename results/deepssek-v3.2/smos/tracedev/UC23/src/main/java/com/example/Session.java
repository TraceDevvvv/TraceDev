package com.example;

/**
 * Represents a user session in the system.
 * Tracks the current user and session state.
 */
public class Session {
    private Administrator currentUser;
    private boolean isActive;

    /**
     * Constructor for Session.
     * Assumption: Initializes with no current user and inactive session.
     */
    public Session() {
        this.currentUser = null;
        this.isActive = false;
    }

    /**
     * Gets the current logged-in user.
     * @return the current Administrator user
     */
    public Administrator getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user and activates session.
     * Assumption: Added for session management.
     * @param user the Administrator to set as current user
     */
    public void setCurrentUser(Administrator user) {
        this.currentUser = user;
        this.isActive = true;
    }

    /**
     * Checks if the session is authenticated.
     * @return true if the session is active and user is authenticated
     */
    public boolean isAuthenticated() {
        return isActive && currentUser != null && currentUser.isAuthenticated();
    }

    /**
     * Logs out the current user and ends the session.
     */
    public void logout() {
        this.currentUser = null;
        this.isActive = false;
    }
}