package com.example;

/**
 * Manages user sessions and authentication.
 */
public class SessionManager {
    private AgencyOperator currentUser;

    public boolean login(Credentials credentials) {
        // In real implementation, validate credentials
        if ("admin".equals(credentials.getUsername()) && "password".equals(credentials.getPassword())) {
            currentUser = new AgencyOperator("user1", "Admin User", 
                java.util.Arrays.asList("edit_banner", "view_banner"));
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public AgencyOperator getCurrentUser() {
        return currentUser;
    }
}