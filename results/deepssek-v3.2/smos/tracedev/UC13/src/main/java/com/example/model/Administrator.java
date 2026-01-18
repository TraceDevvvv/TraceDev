package com.example.model;

/**
 * Represents an Administrator user in the system.
 */
public class Administrator {
    private String userId;
    private String name;
    private String role;
    private boolean loggedIn;

    public Administrator(String userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.loggedIn = false;
    }

    /**
     * Checks if the administrator is currently logged in.
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}