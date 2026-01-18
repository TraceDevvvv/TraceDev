package com.example.editabsence;

/**
 * Represents an administrator user in the system.
 * This class is used to simulate administrator login and authorization.
 */
public class Administrator {
    private String username;
    private String password;
    private boolean isAdmin;

    /**
     * Constructs a new Administrator object.
     *
     * @param username The username of the administrator.
     * @param password The password of the administrator.
     * @param isAdmin  A boolean indicating if the user has administrator privileges.
     */
    public Administrator(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Gets the username of the administrator.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks if the provided username and password match this administrator's credentials.
     *
     * @param inputUsername The username entered by the user.
     * @param inputPassword The password entered by the user.
     * @return True if credentials match and the user is an administrator, false otherwise.
     */
    public boolean authenticate(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword) && this.isAdmin;
    }

    /**
     * Checks if this user has administrator privileges.
     *
     * @return True if the user is an administrator, false otherwise.
     */
    public boolean isAdmin() {
        return isAdmin;
    }
}