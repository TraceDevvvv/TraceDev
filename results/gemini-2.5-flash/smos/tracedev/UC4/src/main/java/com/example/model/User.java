package com.example.model;

/**
 * Represents a user in the system with their ID, username, and hashed password.
 */
public class User {
    public String id; // Changed from private to public as per class diagram
    public String username; // Changed from private to public as per class diagram
    private String hashedPassword;

    /**
     * Constructs a new User instance.
     *
     * @param id The unique identifier for the user.
     * @param username The user's chosen username.
     * @param hashedPassword The securely hashed password of the user.
     */
    public User(String id, String username, String hashedPassword) {
        this.id = id;
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    /**
     * Gets the user's unique ID.
     * @return The user ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the user's username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the user's hashed password.
     * @return The hashed password.
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Sets a new hashed password for the user.
     * @param newHashedPassword The new securely hashed password.
     */
    public void setHashedPassword(String newHashedPassword) {
        this.hashedPassword = newHashedPassword;
    }

    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               '}';
    }
}