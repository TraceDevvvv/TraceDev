package com.etour.session;

/**
 * Validates that Agency Operator HAS logged in before operations (Entry Conditions)
 */
public class SessionManager {
    private String currentUserId = "operator123"; // Assumption: default logged-in user for demo

    public boolean isLoggedIn(String userId) {
        // For demo, assume user is logged in if userId matches currentUserId
        return currentUserId != null && currentUserId.equals(userId);
    }

    public String getCurrentUser() {
        return currentUserId;
    }
}