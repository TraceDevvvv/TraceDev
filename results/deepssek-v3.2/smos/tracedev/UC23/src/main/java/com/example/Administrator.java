package com.example;

/**
 * Represents an administrator user in the system.
 * Implements actor from the class diagram.
 */
public class Administrator {
    private int id;
    private String username;

    /**
     * Constructor for Administrator.
     * @param username the administrator's username
     */
    public Administrator(String username) {
        this.username = username;
        this.id = generateId(); // Assumption: ID is auto-generated
    }

    /**
     * Gets the administrator's username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks if the administrator is authenticated.
     * Assumption: For simplicity, returns true.
     * @return true if authenticated
     */
    public boolean isAuthenticated() {
        return true;
    }

    /**
     * Simulates ID generation.
     * @return a generated ID
     */
    private int generateId() {
        return (int) (Math.random() * 1000);
    }
}