package com.example.teachermanagementsystem;

import java.util.Objects;

/**
 * Represents an Administrator user in the system.
 * An administrator has an ID, a username, and a password for authentication.
 * In a real-world application, passwords would be securely hashed and not stored in plain text.
 */
public class Administrator {
    private String id;
    private String username;
    private String password; // Storing in plain text for simulation; hash in production

    /**
     * Constructs a new Administrator instance.
     * @param id A unique identifier for the administrator.
     * @param username The username used for logging in.
     * @param password The password for authentication.
     */
    public Administrator(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the unique ID of the administrator.
     * @return The administrator's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the username of the administrator.
     * @return The administrator's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Authenticates the administrator by comparing the provided password attempt
     * with the stored password.
     * @param passwordAttempt The password string provided by the user.
     * @return true if the password attempt matches the stored password, false otherwise.
     */
    public boolean authenticate(String passwordAttempt) {
        // In a real system, this would involve hashing the passwordAttempt and comparing hashes.
        return this.password.equals(passwordAttempt);
    }

    /**
     * Provides a string representation of the Administrator, including their ID and username.
     * (Password is intentionally excluded for security reasons).
     * @return A formatted string representing the administrator.
     */
    @Override
    public String toString() {
        return "Administrator{" +
               "id='" + id + '\'' +
               ", username='" + username + '\'' +
               '}';
    }

    /**
     * Compares this Administrator to the specified object. The result is true if and only if
     * the argument is not null and is an Administrator object that has the same ID as this object.
     * @param o The object to compare this Administrator against.
     * @return true if the given object represents an Administrator equivalent to this administrator, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}