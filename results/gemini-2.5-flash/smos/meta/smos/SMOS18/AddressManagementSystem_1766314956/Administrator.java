package com.address_management_system;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Represents an Administrator user in the system.
 * This class handles basic administrator properties and login status.
 * For this use case, a simplified login mechanism is used.
 */
public class Administrator {
    private static final Logger LOGGER = Logger.getLogger(Administrator.class.getName());

    private final String username;
    private final String password; // In a real system, this would be hashed
    private boolean loggedIn;

    /**
     * Constructs a new Administrator instance.
     *
     * @param username The administrator's username. Must not be null or empty.
     * @param password The administrator's password. Must not be null or empty.
     * @throws IllegalArgumentException if username or password is null or empty.
     */
    public Administrator(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        this.username = username.trim();
        this.password = password; // Storing as plain text for simplicity in this example
        this.loggedIn = false; // Initially not logged in
        LOGGER.log(Level.INFO, "Administrator '{0}' created.", this.username);
    }

    /**
     * Attempts to log in the administrator.
     * For this simulation, it just sets the loggedIn status to true.
     * In a real application, this would involve credential verification.
     *
     * @param enteredUsername The username provided for login.
     * @param enteredPassword The password provided for login.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String enteredUsername, String enteredPassword) {
        if (this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
            this.loggedIn = true;
            LOGGER.log(Level.INFO, "Administrator '{0}' logged in successfully.", this.username);
            return true;
        } else {
            LOGGER.log(Level.WARNING, "Login failed for username '{0}'. Invalid credentials.", enteredUsername);
            return false;
        }
    }

    /**
     * Logs out the administrator.
     */
    public void logout() {
        if (this.loggedIn) {
            this.loggedIn = false;
            LOGGER.log(Level.INFO, "Administrator '{0}' logged out.", this.username);
        } else {
            LOGGER.log(Level.INFO, "Administrator '{0}' was not logged in.", this.username);
        }
    }

    /**
     * Checks if the administrator is currently logged in.
     *
     * @return true if the administrator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Returns the username of the administrator.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two Administrator objects are considered equal if their usernames are equal.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return username.equals(that.username);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Returns a string representation of the Administrator object.
     *
     * @return A string in the format "Administrator{username='[username]', loggedIn=[true/false]}"
     */
    @Override
    public String toString() {
        return "Administrator{" +
               "username='" + username + '\'' +
               ", loggedIn=" + loggedIn +
               '}';
    }
}