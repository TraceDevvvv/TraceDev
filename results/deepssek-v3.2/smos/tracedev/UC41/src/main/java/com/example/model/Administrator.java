package com.example.model;

import java.io.Serializable;

/**
 * Represents an Administrator user in the system.
 * Contains login credentials and basic session management.
 */
public class Administrator implements Serializable {
    private String username;
    private String password;

    /**
     * Default constructor.
     */
    public Administrator() {
    }

    /**
     * Constructor with username and password.
     *
     * @param username the administrator's username
     * @param password the administrator's password
     */
    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Attempts to log in the administrator.
     * In a real system, this would validate credentials against a database.
     *
     * @return true if login is successful, false otherwise
     */
    public Boolean login() {
        // Simplified login logic. Assumption: For demonstration, login always succeeds.
        // In a real application, this would involve credential verification.
        return Boolean.TRUE;
    }

    /**
     * Logs out the administrator.
     * This method satisfies exit condition for administrator logout workflow.
     */
    public void logout() {
        // Perform logout operations such as invalidating session.
        System.out.println("Administrator " + username + " logged out.");
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}