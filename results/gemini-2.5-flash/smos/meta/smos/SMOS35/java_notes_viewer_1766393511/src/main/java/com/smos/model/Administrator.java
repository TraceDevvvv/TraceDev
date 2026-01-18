package com.smos.model;

import java.util.Objects;

/**
 * Represents an Administrator user in the School Management and Operations System (SMOS).
 * This class holds authentication details for an administrator.
 */
public class Administrator {
    private String userId;
    private String username;
    private String password; // In a real application, passwords should be hashed and never stored in plain text.

    /**
     * Constructs a new Administrator object.
     *
     * @param userId The unique identifier for the administrator.
     * @param username The username used for logging in.
     * @param password The password used for logging in.
     * @throws IllegalArgumentException if any of the parameters are null or empty.
     */
    public Administrator(String userId, String username, String password) {
        // Validate userId
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }
        // Validate username
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        // Validate password
        if (password == null || password.isEmpty()) { // Password can be empty string if allowed, but not null
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the unique identifier of the administrator.
     *
     * @return The administrator's user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the administrator.
     *
     * @param userId The new user ID.
     * @throws IllegalArgumentException if the userId is null or empty.
     */
    public void setUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }
        this.userId = userId;
    }

    /**
     * Returns the username of the administrator.
     *
     * @return The administrator's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the administrator.
     *
     * @param username The new username.
     * @throws IllegalArgumentException if the username is null or empty.
     */
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        this.username = username;
    }

    /**
     * Returns the password of the administrator.
     *
     * @return The administrator's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the administrator.
     *
     * @param password The new password.
     * @throws IllegalArgumentException if the password is null or empty.
     */
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        this.password = password;
    }

    /**
     * Compares this Administrator object to the specified object. The result is true if and only if
     * the argument is not null and is an Administrator object that has the same userId as this object.
     *
     * @param o The object to compare this Administrator against.
     * @return true if the given object represents an Administrator equivalent to this administrator, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(userId, that.userId);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables suchs as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    /**
     * Returns a string representation of the Administrator object.
     *
     * @return A string containing the administrator's user ID and username.
     */
    @Override
    public String toString() {
        return "Administrator{" +
               "userId='" + userId + '\'' +
               ", username='" + username + '\'' +
               '}';
    }
}