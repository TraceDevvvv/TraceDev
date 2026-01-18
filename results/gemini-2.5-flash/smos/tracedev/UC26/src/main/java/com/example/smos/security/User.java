package com.example.smos.security;

/**
 * Represents a user of the system with authentication and authorization details.
 * Corresponds to the 'User' class in the UML Class Diagram.
 */
public class User {
    private String username;
    private String email;
    private UserRole role; // Association with UserRole enum

    /**
     * Constructs a new User object.
     * @param username The unique username for the user.
     * @param email The email address of the user.
     * @param role The role assigned to the user, determining their permissions.
     */
    public User(String username, String email, UserRole role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getters for all attributes
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    // Setters for attributes (if mutable)
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", role=" + role +
               '}';
    }
}