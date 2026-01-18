package com.school.auth;

/**
 * Actor representing an administrator.
 */
public class Administrator {
    private String username;

    public Administrator(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}