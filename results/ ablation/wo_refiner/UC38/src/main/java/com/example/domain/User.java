package com.example.domain;

/**
 * User entity.
 */
public class User {
    private String id;
    private String username;
    private boolean isAuthenticated;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
        this.isAuthenticated = false;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}