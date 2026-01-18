package com.example.statistics;

/**
 * Utility class to manage user session, specifically authentication status.
 * Added to satisfy requirement REQ_ENTRY_COND_1.
 */
public class SessionManager {
    // In a real application, this would come from a security context or session store.
    private String authenticatedOperatorId;
    private boolean authenticated;

    public SessionManager() {
        // Mock an authenticated user by default for the example flow.
        this.authenticatedOperatorId = "OP001";
        this.authenticated = true;
    }

    /**
     * Gets the ID of the currently authenticated operator.
     * @return The authenticated operator's ID, or null if no operator is authenticated.
     */
    public String getAuthenticatedOperatorId() {
        System.out.println("SessionManager: getAuthenticatedOperatorId() called.");
        return authenticated ? authenticatedOperatorId : null;
    }

    /**
     * Checks if a user is currently authenticated.
     * @return true if a user is authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        System.out.println("SessionManager: isAuthenticated() called. Result: " + authenticated);
        return authenticated;
    }

    // For testing or dynamic authentication, setters could be added
    public void setAuthenticatedOperatorId(String authenticatedOperatorId) {
        this.authenticatedOperatorId = authenticatedOperatorId;
        this.authenticated = (authenticatedOperatorId != null && !authenticatedOperatorId.isEmpty());
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        if (!authenticated) {
            this.authenticatedOperatorId = null;
        }
    }
}