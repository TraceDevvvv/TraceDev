package com.example.login;

/**
 * Represents the data structure for user login credentials.
 */
public class LoginData {
    private String username;
    private String password;

    /**
     * Constructs a new LoginData object with the given username and password.
     *
     * @param username The user's login username.
     * @param password The user's login password.
     */
    public LoginData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the username.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
}