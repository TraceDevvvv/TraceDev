package com.example.login;

/**
 * Infrastructure/Session Management: Manages user sessions.
 */
public class UserSessionManager {
    private User currentUser; // Simulates the currently logged-in user

    /**
     * Logs in a user by setting them as the current session user.
     * @param user The user to log in.
     */
    public void login(User user) {
        this.currentUser = user;
        System.out.println("UserSessionManager: User '" + user.getUsername() + "' logged in successfully.");
    }

    /**
     * Checks if a specific user is currently logged in.
     * @param user The user to check.
     * @return true if the user is logged in, false otherwise.
     */
    public boolean isLoggedIn(User user) {
        return currentUser != null && currentUser.equals(user);
    }

    /**
     * Retrieves the currently logged-in user.
     * @return The current User, or null if no user is logged in.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        if (currentUser != null) {
            System.out.println("UserSessionManager: User '" + currentUser.getUsername() + "' logged out.");
            this.currentUser = null;
        }
    }
}