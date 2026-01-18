package com.example.model;

/**
 * Represents a User actor in the system.
 * Trace: Actor in duplicate feedback notification use case.
 */
public class User {
    private String userId;
    private String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}