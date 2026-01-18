package com.loginapp.model;

/**
 * Represents a user with a username and password.
 * This is a simple model class to hold user credentials.
 */
public class User {
    private final String username;
    private final String password;

    /**
     * Constructs a new User object.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Provides a string representation of the User object (excluding password for security).
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               '}';
    }
}