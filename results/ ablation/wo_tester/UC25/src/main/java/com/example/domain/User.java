package com.example.domain;

/**
 * Domain entity representing a user.
 * Added to satisfy requirement Entry Conditions.
 */
public class User {
    private String id;
    private String username;
    private boolean isLoggedIn;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
        this.isLoggedIn = false;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void login() {
        isLoggedIn = true;
    }

    public void logout() {
        isLoggedIn = false;
    }
}