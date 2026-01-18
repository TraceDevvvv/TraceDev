package com.example.model;

/**
 * User class for authentication context.
 * Represents the Tourist actor in the domain.
 */
public class User {
    private final String id;
    private final String username;
    private final boolean authenticated;

    public User(String id, String username, boolean authenticated) {
        this.id = id;
        this.username = username;
        this.authenticated = authenticated;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}