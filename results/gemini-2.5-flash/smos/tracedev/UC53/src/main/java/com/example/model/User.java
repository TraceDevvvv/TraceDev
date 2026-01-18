package com.example.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a user in the system.
 * Added to satisfy authentication and authorization requirements from the sequence diagram.
 */
public class User {
    private String id;
    private String username;
    private List<String> roles; // List of roles associated with the user

    /**
     * Default constructor.
     */
    public User() {
        this.roles = new ArrayList<>();
    }

    /**
     * Constructs a new User with specified details.
     * @param id The unique identifier of the user.
     * @param username The username of the user.
     * @param roles A list of roles assigned to the user.
     */
    public User(String id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = new ArrayList<>(roles); // Ensure roles list is mutable and not directly referenced
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return new ArrayList<>(roles); // Return a copy to prevent external modification
    }

    // --- Setters ---
    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRoles(List<String> roles) {
        this.roles = new ArrayList<>(roles);
    }

    /**
     * Checks if the user has a specific role.
     * @param role The role to check for.
     * @return true if the user has the role, false otherwise.
     */
    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", roles=" + roles +
               '}';
    }
}