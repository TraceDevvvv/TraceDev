package com.example.model;

/**
 * Represents an agency operator (user) with login capabilities.
 */
public class AgencyOperator {
    private String username;
    private String role;

    public AgencyOperator(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Simulates login operation.
     * @return true if login is successful, false otherwise.
     */
    public boolean login() {
        // In a real application, this would involve authentication logic
        System.out.println(username + " logged in.");
        return true;
    }

    /**
     * Performs logout.
     */
    public void logout() {
        System.out.println(username + " logged out.");
    }
}