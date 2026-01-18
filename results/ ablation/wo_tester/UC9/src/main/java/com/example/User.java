package com.example;

/**
 * Represents a user in the system.
 * Entry condition: User IS authenticated.
 */
public class User {
    private String id;
    private boolean isAuthenticated;

    public User(String id, boolean isAuthenticated) {
        this.id = id;
        this.isAuthenticated = isAuthenticated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    /**
     * Authenticates the user.
     * @return true if authentication succeeds, false otherwise.
     */
    public boolean authenticate() {
        // Simplified authentication logic.
        // In a real implementation, this would involve credentials check.
        this.isAuthenticated = true;
        return this.isAuthenticated;
    }
}