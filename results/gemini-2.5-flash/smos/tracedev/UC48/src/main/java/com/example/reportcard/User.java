package com.example.reportcard;

import java.util.List;
import java.util.Objects;

/**
 * Base class for all users in the system.
 * Includes basic user attributes and role management.
 * Satisfies REQ-001: User Login & Role Management.
 */
public class User {
    private final String username;
    private final List<String> roles;
    private boolean loggedIn; // For simplicity, assume true for demonstration

    /**
     * Constructs a new User.
     *
     * @param username The unique username of the user.
     * @param roles A list of roles assigned to the user.
     */
    public User(String username, List<String> roles) {
        this.username = Objects.requireNonNull(username, "Username cannot be null");
        this.roles = Objects.requireNonNull(roles, "Roles list cannot be null");
        this.loggedIn = true; // Assume logged in for the purpose of this simulation
    }

    public String getUsername() {
        return username;
    }

    /**
     * Checks if the user is currently logged in.
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Checks if the user has a specific role.
     *
     * @param role The role to check for.
     * @return true if the user has the role, false otherwise.
     */
    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", roles=" + roles +
               ", loggedIn=" + loggedIn +
               '}';
    }
}