package com.example.addressapp;

/**
 * Represents the current user's session status.
 * Added to satisfy requirement EC1.
 */
public class UserSession {
    private boolean isLoggedIn;

    public UserSession() {
        // Default to not logged in for simplicity, or can be set by a login process
        this.isLoggedIn = false;
    }

    public UserSession(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    /**
     * Checks if the user is currently logged in.
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Sets the logged-in status of the user.
     * @param loggedIn The new logged-in status.
     */
    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
        System.out.println("UserSession: User logged in status set to " + isLoggedIn);
    }
}