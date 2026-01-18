'''
Singleton class to manage user session state
Ensures authentication is maintained across the application
'''
package com.chatdev.newsapp;
public class SessionManager {
    private static SessionManager instance;
    private boolean isLoggedIn;
    private String username;
    // Private constructor for singleton pattern
    private SessionManager() {
        isLoggedIn = false;
        username = null;
    }
    /**
     * Gets the singleton instance
     * @return The SessionManager instance
     */
    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    /**
     * Logs in a user and establishes a session
     * @param username The authenticated username
     * @return true if login successful, false if already logged in
     */
    public boolean login(String username) {
        if (isLoggedIn) {
            return false; // Already logged in
        }
        this.username = username;
        this.isLoggedIn = true;
        return true;
    }
    /**
     * Logs out the current user
     */
    public void logout() {
        this.username = null;
        this.isLoggedIn = false;
    }
    /**
     * Checks if a user is currently logged in
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    /**
     * Gets the current logged in username
     * @return The username or null if not logged in
     */
    public String getUsername() {
        return username;
    }
    /**
     * Validates session - checks if agency operator is logged in
     * @throws SecurityException if not logged in
     */
    public void validateSession() throws SecurityException {
        if (!isLoggedIn) {
            throw new SecurityException("Access denied: Agency operator must be logged in");
        }
    }
}