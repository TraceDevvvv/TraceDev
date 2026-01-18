package com.example.service;

/**
 * Application Service for managing user sessions.
 * Implements requirement REQ ID 3 implicitly.
 * For this demo, it provides a mocked logged-in state.
 */
public class SessionManager {

    // For simplicity, a static mock user for demo purposes
    private static AgencyOperator currentUser = new AgencyOperator("demo_operator", "Admin");
    private static boolean loggedIn = true; // Always logged in for this demo

    /**
     * Checks if a user is currently logged in.
     * Implements requirement REQ ID 3.
     * @return true if a user is logged in, false otherwise.
     */
    public boolean isUserLoggedIn() {
        System.out.println("SessionManager: isUserLoggedIn() called, returning " + loggedIn);
        return loggedIn;
    }

    /**
     * Retrieves the currently logged-in user.
     * @return The AgencyOperator object of the current user, or null if no user is logged in.
     */
    public AgencyOperator getCurrentUser() {
        if (loggedIn) {
            return currentUser;
        }
        return null;
    }

    // --- Mock methods for demonstration purposes ---

    public static void setLoggedInUser(AgencyOperator operator) {
        currentUser = operator;
        loggedIn = true;
    }

    public static void setLoggedOut() {
        currentUser = null;
        loggedIn = false;
    }
}