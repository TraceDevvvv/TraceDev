package com.example.usermanagement;

import java.util.Objects;

/**
 * Represents a user in the system.
 * This class holds basic information about a user, such as their ID, username, and role.
 */
public class User {
    private final String id;
    private final String username;
    private final String role; // e.g., "Administrator", "RegularUser"

    /**
     * Constructs a new User object.
     *
     * @param id       The unique identifier for the user. Must not be null or empty.
     * @param username The username of the user. Must not be null or empty.
     * @param role     The role of the user (e.g., "Administrator"). Must not be null or empty.
     * @throws IllegalArgumentException if any of the parameters are null or empty.
     */
    public User(String id, String username, String role) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("User role cannot be null or empty.");
        }
        this.id = id;
        this.username = username;
        this.role = role;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return The user's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the username of the user.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the role of the user.
     *
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Provides a string representation of the User object.
     *
     * @return A formatted string containing the user's ID, username, and role.
     */
    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", role='" + role + '\'' +
               '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
               Objects.equals(username, user.username) &&
               Objects.equals(role, user.role);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, role);
    }
}