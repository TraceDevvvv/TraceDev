package com.example.domain;

/**
 * Represents user credentials for authentication.
 * (Added to satisfy REQ-001)
 */
public class Credentials {
    private String username;
    private String password;

    /**
     * Constructs a Credentials object.
     *
     * @param username The username.
     * @param password The password.
     */
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}