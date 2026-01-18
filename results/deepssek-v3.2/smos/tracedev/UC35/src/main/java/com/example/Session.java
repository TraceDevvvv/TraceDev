package com.example;

/**
 * Represents a user session.
 * Supports requirement R1 - Entry Condition: user must be logged in as administrator.
 */
public class Session {
    private String username;
    private String role;
    private boolean active;

    /**
     * Constructs a Session with the given username and role.
     *
     * @param username the username
     * @param role the user role
     */
    public Session(String username, String role) {
        this.username = username;
        this.role = role;
        this.active = true;
    }

    /**
     * Validates that the current session belongs to an administrator.
     *
     * @return true if the user is an administrator and session is active, false otherwise
     */
    public boolean validateAdmin() {
        // Requirement R1: user must be logged in as administrator
        return active && "administrator".equalsIgnoreCase(role);
    }

    /**
     * Terminates the session, marking it as inactive.
     */
    public void terminate() {
        this.active = false;
        System.out.println("Session terminated for user: " + username);
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Checks if the session is active.
     *
     * @return true if active, false otherwise
     */
    public boolean isActive() {
        return active;
    }
}