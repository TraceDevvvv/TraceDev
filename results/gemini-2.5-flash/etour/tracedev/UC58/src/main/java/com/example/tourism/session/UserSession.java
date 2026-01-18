package com.example.tourism.session;

/**
 * Context/Session Management: Manages the authentication state of the user.
 * Implemented as a simple static class for demonstration, representing conceptual session data.
 * Added to satisfy Audit Recommendation for REQ-EC01.
 */
public class UserSession {
    // Represents the authentication status of the current user.
    // Made static for simplicity to simulate a global session state.
    private static boolean isAuthenticated = false; // Default to not authenticated

    /**
     * Checks if the user is currently authenticated.
     * @return true if the user is authenticated, false otherwise.
     */
    public static boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Sets the authentication status of the user.
     * @param authenticated The new authentication status.
     */
    public static void setAuthenticated(boolean authenticated) {
        UserSession.isAuthenticated = authenticated;
        System.out.println("UserSession: Authentication status set to " + (authenticated ? "AUTHENTICATED" : "NOT AUTHENTICATED"));
    }
}