package com.example;

import java.util.Objects;

/**
 * Represents a user in the system.
 */
public class User {
    private String userId;
    private String username;
    private UserStatus status;

    public User(String userId, String username, UserStatus status) {
        this.userId = userId;
        this.username = username;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Activates the user by setting status to ACTIVE.
     */
    public void activate() {
        this.status = UserStatus.ACTIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}