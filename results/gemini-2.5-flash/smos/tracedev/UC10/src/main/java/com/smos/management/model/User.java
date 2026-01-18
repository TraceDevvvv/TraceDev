package com.smos.management.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a User in the system, with associated roles.
 * This class models the 'User' entity from the Class Diagram.
 */
public class User {
    private String userId;
    private String username;
    private List<Role> roles; // Represents the 0..* to 0..* relationship with Role

    /**
     * Constructs a new User with the specified ID and username.
     * Initializes an empty list of roles.
     *
     * @param userId the unique identifier for the user.
     * @param username the username.
     */
    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.roles = new ArrayList<>(); // Initialize roles list
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return the user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the username.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns an unmodifiable list of roles assigned to this user.
     *
     * @return an unmodifiable list of roles.
     */
    public List<Role> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    /**
     * Adds a role to the user's list of roles if it's not already present.
     *
     * @param role the role to add.
     */
    public void addRole(Role role) {
        if (role != null && !hasRole(role)) {
            this.roles.add(role);
        }
    }

    /**
     * Removes a role from the user's list of roles.
     *
     * @param role the role to remove.
     */
    public void removeRole(Role role) {
        if (role != null) {
            this.roles.remove(role);
        }
    }

    /**
     * Checks if the user has a specific role.
     *
     * @param role the role to check for.
     * @return true if the user has the role, false otherwise.
     */
    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }

    /**
     * Provides a string representation of the User object.
     *
     * @return a string containing the user ID, username, and roles.
     */
    @Override
    public String toString() {
        return "User{id='" + userId + "', username='\" + username + \"', roles=\" + roles + \"}";
    }

    /**
     * Compares this User object with another object for equality.
     * Two users are considered equal if they have the same userId.
     *
     * @param o the object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the userId.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}