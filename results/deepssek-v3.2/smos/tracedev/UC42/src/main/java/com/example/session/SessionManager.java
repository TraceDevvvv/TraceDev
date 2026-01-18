package com.example.session;

/**
 * Manages user session information.
 */
public class SessionManager {
    private UserSession currentUser;

    // Constructor
    public SessionManager() {
        // For demonstration, start with no user logged in.
        this.currentUser = null;
    }

    /**
     * Sets the current user session (simulates login).
     * @param userSession the user session to set
     */
    public void setCurrentUser(UserSession userSession) {
        this.currentUser = userSession;
    }

    /**
     * Retrieves the current user session.
     * @return UserSession instance, or null if not logged in
     */
    public UserSession getCurrentUser() {
        return currentUser;
    }

    /**
     * Checks if a user is currently logged in.
     * Added for entry condition validation.
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}