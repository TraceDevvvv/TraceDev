/*
Represents a registered user in the system.
*/
package com.chatdev.model;
/**
 * Represents a registered user in the system.
 * This class simply holds user credentials.
 */
public class User {
    private String username;
    private String password; // In a real application, this would be hashed and never stored or handled in plain text.
    /**
     * Constructs a new User object.
     *
     * @param username The unique username for the user.
     * @param password The password for the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    /**
     * Gets the username of this user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Checks if the provided password matches this user's password.
     *
     * @param password The password to check.
     * @return true if the passwords match, false otherwise.
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}