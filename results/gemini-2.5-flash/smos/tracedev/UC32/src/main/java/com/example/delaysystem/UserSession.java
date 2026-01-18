package com.example.delaysystem;

/**
 * Represents the current user session.
 * Added to satisfy R3, R4.
 * The sequence diagram does not explicitly use this class's methods in the depicted flow,
 * but it's part of the Use Case Controller's dependencies.
 */
public class UserSession {
    private boolean isLoggedIn;
    private String currentUserId;

    /**
     * Constructor for UserSession.
     *
     * @param isLoggedIn Initial login status.
     * @param currentUserId The ID of the currently logged-in user, or null if not logged in.
     */
    public UserSession(boolean isLoggedIn, String currentUserId) {
        this.isLoggedIn = isLoggedIn;
        this.currentUserId = currentUserId;
        System.out.println("[UserSession] Initialized: User " + (isLoggedIn ? currentUserId + " is logged in." : "is not logged in."));
    }

    /**
     * Checks if a user is currently logged in.
     *
     * @return true if a user is logged in, false otherwise.
     */
    public boolean checkLoggedInStatus() {
        return isLoggedIn;
    }

    /**
     * Gets the ID of the current logged-in user.
     *
     * @return The current user's ID, or null if no user is logged in.
     */
    public String getCurrentUserId() {
        return currentUserId;
    }

    /**
     * Simulates user login.
     *
     * @param userId The ID of the user logging in.
     */
    public void login(String userId) {
        this.isLoggedIn = true;
        this.currentUserId = userId;
        System.out.println("[UserSession] User " + userId + " logged in.");
    }

    /**
     * Simulates user logout.
     */
    public void logout() {
        this.isLoggedIn = false;
        this.currentUserId = null;
        System.out.println("[UserSession] User logged out.");
    }
}