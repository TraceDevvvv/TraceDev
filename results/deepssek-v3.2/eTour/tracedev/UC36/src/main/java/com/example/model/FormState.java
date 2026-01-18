package com.example.model;

/**
 * Represents the state of the login form.
 * Added to satisfy requirement Flow-4 (State persistence for error recovery).
 */
public class FormState {
    private String username;
    private String password;

    /**
     * Constructs a FormState with given username and password.
     * @param username the username
     * @param password the password
     */
    public FormState(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the stored username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the stored password.
     * @return password
     */
    public String getPassword() {
        return password;
    }
}