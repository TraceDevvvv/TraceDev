package com.example.bannermanagement;

/**
 * Service for handling user authentication.
 * Added to satisfy requirement REQ-001.
 */
public class AuthenticationService {
    private boolean loggedIn = false; // Simple in-memory login state

    /**
     * Attempts to log in a user with the given username and password.
     * For this simulation, any non-empty username/password will result in success.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        // Simple authentication logic for demonstration
        if ("agency".equals(username) && "pass".equals(password)) {
            this.loggedIn = true;
            System.out.println("DEBUG: User logged in as " + username);
            return true;
        }
        System.out.println("DEBUG: Login failed for user " + username);
        this.loggedIn = false;
        return false;
    }

    /**
     * Checks if a user is currently logged in.
     * @return true if a user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        this.loggedIn = false;
        System.out.println("DEBUG: User logged out.");
    }
}