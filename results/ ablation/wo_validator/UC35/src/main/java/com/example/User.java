package com.example;

/**
 * Represents a user entity with username, password hash, and role.
 */
public class User {
    private String username;
    private String passwordHash;
    private String role;

    /**
     * Constructs a new User.
     * @param username the username
     * @param passwordHash the hashed password
     * @param role the user role
     */
    public User(String username, String passwordHash, String role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password hash.
     * @return the password hash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Gets the user role.
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Validates an input password against the stored hash using the given strategy.
     * @param inputPassword the password to validate
     * @param strategy the authentication strategy to use for verification
     * @return true if the password matches, false otherwise
     */
    public boolean validatePassword(String inputPassword, AuthenticationStrategy strategy) {
        return strategy.verify(inputPassword, passwordHash);
    }
}