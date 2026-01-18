package com.example.banner;

/**
 * Represents the current user session state.
 * Added to satisfy requirement R3.
 */
public class UserSession {
    public boolean isAuthenticated;
    public String userId;

    /**
     * Constructs a new UserSession instance.
     * @param isAuthenticated True if a user is currently authenticated, false otherwise.
     * @param userId The ID of the authenticated user, or null if not authenticated.
     */
    public UserSession(boolean isAuthenticated, String userId) {
        this.isAuthenticated = isAuthenticated;
        this.userId = userId;
    }

    /**
     * Sets the authentication status and user ID for the session.
     * @param isAuthenticated True if authenticated, false otherwise.
     * @param userId The ID of the user.
     */
    public void setSession(boolean isAuthenticated, String userId) {
        this.isAuthenticated = isAuthenticated;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserSession{isAuthenticated=" + isAuthenticated + ", userId='" + userId + "'}";
    }
}