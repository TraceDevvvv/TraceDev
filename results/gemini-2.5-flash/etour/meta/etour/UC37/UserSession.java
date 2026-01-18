package com.example.logoutapp;

/**
 * UserSession class manages the login status of a user.
 * It provides methods to log in, log out, and check the current login status.
 */
public class UserSession {
    private boolean isLoggedIn;
    private String username;

    /**
     * Constructor for UserSession.
     * Initializes the session as logged out.
     */
    public UserSession() {
        this.isLoggedIn = false;
        this.username = null;
    }

    /**
     * Logs in a user.
     *
     * @param username The username of the user logging in.
     */
    public void login(String username) {
        this.isLoggedIn = true;
        this.username = username;
        System.out.println(username + " has successfully logged in.");
    }

    /**
     * Logs out the current user.
     * Resets the login status and clears the username.
     */
    public void logout() {
        this.isLoggedIn = false;
        this.username = null;
    }

    /**
     * Checks if a user is currently logged in.
     *
     * @return true if a user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Gets the username of the currently logged-in user.
     *
     * @return The username if logged in, otherwise null.
     */
    public String getUsername() {
        return username;
    }
}