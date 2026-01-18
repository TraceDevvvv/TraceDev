package com.smos.rolemanagement.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a user in the system, including their unique identifier, username,
 * and the set of roles currently assigned to them.
 */
public class User {
    private String id; // Unique identifier for the user
    private String username; // User's login name
    private Set<Role> roles; // Set of roles assigned to this user

    /**
     * Default constructor for User.
     * Initializes the roles set to an empty HashSet.
     * Required for some frameworks (e.g., JSON serialization/deserialization).
     */
    public User() {
        this.roles = new HashSet<>();
    }

    /**
     * Constructs a new User with the specified ID and username.
     * Initializes the roles set to an empty HashSet.
     *
     * @param id       The unique identifier for the user.
     * @param username The user's login name.
     */
    public User(String id, String username) {
        this.id = id;
        this.username = username;
        this.roles = new HashSet<>();
    }

    /**
     * Constructs a new User with the specified ID, username, and an initial set of roles.
     *
     * @param id       The unique identifier for the user.
     * @param username The user's login name.
     * @param roles    The initial set of roles for the user.
     */
    public User(String id, String username, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.roles = new HashSet<>(roles); // Create a new HashSet to avoid direct reference
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
     * Sets the unique identifier of the user.
     *
     * @param id The new ID for the user.
     */
    public void setId(String id) {
        this.id = id;
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
     * Sets the username of the user.
     *
     * @param username The new username for the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the set of roles assigned to this user.
     *
     * @return A set of Role objects.
     */
    public Set<Role> getRoles() {
        return new HashSet<>(roles); // Return a copy to prevent external modification of the internal set
    }

    /**
     * Sets the roles for this user.
     *
     * @param roles The new set of roles for the user.
     */
    public void setRoles(Set<Role> roles) {
        this.roles = new HashSet<>(roles); // Create a new HashSet to avoid direct reference
    }

    /**
     * Adds a single role to the user's set of roles.
     *
     * @param role The role to add.
     * @return true if the role was added (i.e., it was not already present), false otherwise.
     */
    public boolean addRole(Role role) {
        return this.roles.add(role);
    }

    /**
     * Removes a single role from the user's set of roles.
     *
     * @param role The role to remove.
     * @return true if the role was removed (i.e., it was present), false otherwise.
     */
    public boolean removeRole(Role role) {
        return this.roles.remove(role);
    }

    /**
     * Checks if the user has a specific role.
     *
     * @param role The role to check for.
     * @return true if the user has the role, false otherwise.
     */
    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }

    /**
     * Compares this User object with another object for equality.
     * Two users are considered equal if their IDs are the same.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    /**
     * Returns a hash code value for the object.
     * The hash code is based on the user's ID.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string containing the user's ID, username, and assigned roles.
     */
    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               ", roles=" + roles +
               '}';
    }
}