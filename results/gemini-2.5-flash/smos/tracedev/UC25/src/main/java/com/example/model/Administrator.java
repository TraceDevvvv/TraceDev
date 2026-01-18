package com.example.model;

/**
 * Represents an Administrator user.
 * This is a simple placeholder class.
 */
public class Administrator {
    private String username;

    /**
     * Constructs an Administrator with a given username.
     * @param username The administrator's username.
     */
    public Administrator(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Administrator{" +
               "username='" + username + '\'' +
               '}';
    }
}