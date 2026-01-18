package com.example.absencesystem;

import java.util.Objects;

/**
 * Represents an administrator user in the absence management system.
 * Administrators have a username and a password for authentication.
 */
public class Administrator {
    private final String username;
    private final String password; // In a real system, passwords should be hashed and salted.

    /**
     * Constructs a new Administrator object.
     *
     * @param username The unique username for the administrator. Cannot be null or empty.
     * @param password The password for the administrator. Cannot be null or empty.
     * @throws IllegalArgumentException if username or password is null or empty.
     */
    public Administrator(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (password == null || password.isEmpty()) { // Password can be just empty if allowed, but not null.
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        this.username = username.trim();
        this.password = password;
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
     * Returns the password of the administrator.
     * Note: In a production system, this method should not expose the raw password.
     * It's included here for demonstration purposes of a simple login check.
     *
     * @return The administrator's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Authenticates the administrator with the given username and password.
     * In a real application, this would involve comparing hashed passwords.
     *
     * @param inputUsername The username provided for authentication.
     * @param inputPassword The password provided for authentication.
     * @return true if the provided credentials match this administrator's, false otherwise.
     */
    public boolean authenticate(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    /**
     * Compares this Administrator object to the specified object. The result is true if and only if
     * the argument is not null and is an Administrator object that has the same username as this object.
     * Passwords are not considered for equality.
     *
     * @param o The object to compare this Administrator against.
     * @return true if the given object represents an Administrator equivalent to this one, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return username.equals(that.username);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap. The hash code is based on the username.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Provides a string representation of the Administrator object.
     *
     * @return A string containing the administrator's username.
     */
    @Override
    public String toString() {
        return "Administrator{" +
               "username='" + username + '\'' +
               '}';
    }
}