package com.example.culturalheritage.util;

/**
 * A Utility class to manage guest user session information.
 * For this context, it simply provides a dummy user ID.
 */
public class GuestUserSession {

    /**
     * Retrieves the ID of the currently logged-in guest user.
     * In a real application, this would fetch the ID from a session management system.
     * @return A string representing the guest user's ID.
     */
    public String getLoggedInUserId() {
        System.out.println("GuestUserSession: getLoggedInUserId() - Returning dummy user ID.");
        // Simulate a logged-in guest user.
        return "guestUser123";
    }
}