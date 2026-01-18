package com.example.model;

/**
 * Represents a User entity in the system.
 * This class holds basic user information.
 */
public class User {
    public String id;
    public String username;
    public String email;
    public boolean isActive;

    /**
     * Constructs a new User instance.
     * @param id The unique identifier for the user.
     * @param username The user's chosen username.
     * @param email The user's email address.
     * @param isActive The active status of the user account.
     */
    public User(String id, String username, String email, boolean isActive) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isActive = isActive;
    }

    // Getters and Setters (omitted for brevity as fields are public as per diagram, but good practice is to use them)
    // For the purpose of this exercise and strict adherence to diagram, public fields are used.

    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", isActive=" + isActive +
               '}';
    }
}