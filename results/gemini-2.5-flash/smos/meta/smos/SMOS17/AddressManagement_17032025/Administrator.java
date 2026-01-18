package com.addressmanagement;

/**
 * Represents an Administrator user in the system.
 * This class handles administrator specific properties and login simulation.
 */
public class Administrator {
    private String username;
    private String password;
    private boolean isLoggedIn;

    /**
     * Constructs a new Administrator object with a given username and password.
     *
     * @param username The username for the administrator.
     * @param password The password for the administrator.
     */
    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = false; // Initially not logged in
    }

    /**
     * Attempts to log in the administrator with provided credentials.
     *
     * @param enteredUsername The username entered by the user.
     * @param enteredPassword The password entered by the user.
     * @return true if the credentials match and login is successful, false otherwise.
     */
    public boolean login(String enteredUsername, String enteredPassword) {
        if (this.username.equals(enteredUsername) && this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Administrator '" + this.username + "' logged in successfully.");
            return true;
        } else {
            this.isLoggedIn = false;
            System.out.println("Login failed for '" + enteredUsername + "'. Invalid credentials.");
            return false;
        }
    }

    /**
     * Logs out the administrator.
     */
    public void logout() {
        this.isLoggedIn = false;
        System.out.println("Administrator '" + this.username + "' logged out.");
    }

    /**
     * Checks if the administrator is currently logged in.
     *
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
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
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the administrator.
     * Note: In a real application, passwords should never be directly exposed.
     * This is for simulation purposes.
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
     */
    public void setPassword(String password) {
        this.password = password;
    }
}