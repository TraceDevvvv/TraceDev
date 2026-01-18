package com.example.entity;

/**
 * Represents a user in the system.
 * Not explicitly in the diagram but implied by AuthenticationService.getCurrentUser().
 */
public class User {
    private int id;
    private String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}