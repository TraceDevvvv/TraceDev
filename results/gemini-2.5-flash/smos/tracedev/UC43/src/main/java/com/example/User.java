package com.example;

import java.util.List;

/**
 * Represents a user in the system, typically for authentication purposes.
 */
public class User {
    private String id;
    private String username;
    private List<String> roles;

    /**
     * Constructor for the User class.
     *
     * @param id       The unique ID of the user.
     * @param username The username of the user.
     * @param roles    A list of roles assigned to the user.
     */
    public User(String id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    // --- Getters ---

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
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