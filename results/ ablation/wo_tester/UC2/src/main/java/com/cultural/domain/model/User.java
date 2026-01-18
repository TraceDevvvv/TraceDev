package com.cultural.domain.model;

/**
 * Simple user model for authentication.
 * Created as an assumption since referenced by LoginService.
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