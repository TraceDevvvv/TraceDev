package com.example;

import java.util.Arrays;
import java.util.List;

/**
 * A mock authentication service for checking user login status.
 * Added to satisfy requirement REQ-003.
 */
public class AuthenticationService {

    private User loggedInUser;

    public AuthenticationService() {
        // Initialize with a mock logged-in administrator for demonstration
        this.loggedInUser = new User("admin123", "administrator", Arrays.asList("ADMIN", "USER"));
    }

    /**
     * Checks if a user is currently logged in.
     *
     * @return true if a user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        // In a real application, this would check session, tokens, etc.
        return loggedInUser != null;
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The User object if logged in, null otherwise.
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Simulates a user logging in.
     * @param user The user to log in.
     */
    public void login(User user) {
        this.loggedInUser = user;
        System.out.println("AuthenticationService: User " + user.getUsername() + " logged in.");
    }

    /**
     * Simulates a user logging out.
     */
    public void logout() {
        if (loggedInUser != null) {
            System.out.println("AuthenticationService: User " + loggedInUser.getUsername() + " logged out.");
            this.loggedInUser = null;
        }
    }
}