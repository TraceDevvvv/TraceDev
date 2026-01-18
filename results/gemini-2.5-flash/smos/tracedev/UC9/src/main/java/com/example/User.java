package com.example;

import java.util.Objects;

/**
 * Represents a user entity with basic information.
 */
public class User {
    private String userId;
    private String username;
    private String email;

    /**
     * Constructs a new User.
     * @param userId The unique identifier for the user.
     * @param username The username of the user.
     * @param email The email address of the user.
     */
    public User(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    /**
     * Gets the unique ID of the user.
     * @return The user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Gets the username of the user.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the email address of the user.
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
               "userId='" + userId + '\'' +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               '}';
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
        return Objects.hash(userId);
    }
}