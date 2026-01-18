package com.example;

/**
 * Represents an Administrator user.
 * As per the class diagram.
 */
public class Administrator {
    private String id;
    private String username;
    private String role;
    private boolean loggedIn;

    public Administrator(String id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.loggedIn = false;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdministrator() {
        // Simple check: assume role "ADMIN" means administrator.
        return "ADMIN".equals(role);
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}