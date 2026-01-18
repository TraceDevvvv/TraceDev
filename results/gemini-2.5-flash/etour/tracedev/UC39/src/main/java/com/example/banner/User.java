package com.example.banner;

/**
 * Represents a user in the system.
 * Added to satisfy requirement R3.
 */
public class User {
    private String id;
    private String role;

    /**
     * Constructs a new User instance.
     * @param id The unique identifier for the user.
     * @param role The role of the user (e.g., "operator", "admin").
     */
    public User(String id, String role) {
        this.id = id;
        this.role = role;
    }

    /**
     * Gets the unique identifier of the user.
     * @return The user's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the role of the user.
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', role='" + role + "'}";
    }
}