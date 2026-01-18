package com.example.login;

/**
 * Represents the credentials provided by a user during login.
 * This is a Data Transfer Object (DTO) for login information.
 */
public class LoginCredentials {
    private String username;
    private String password;

    /**
     * Constructs a LoginCredentials object with the given username and password.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the username.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
}