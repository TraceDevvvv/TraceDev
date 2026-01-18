package com.example.login;

/**
 * Domain Entity: Represents a user in the system.
 */
public class User {
    private String id;
    private String username;
    private String passwordHash; // Storing hashed password for security
    private boolean isAccountLocked;

    /**
     * Constructs a new User instance.
     * @param id A unique identifier for the user.
     * @param username The user's username.
     * @param passwordHash The hashed password of the user.
     * @param isAccountLocked True if the account is locked, false otherwise.
     */
    public User(String id, String username, String passwordHash, boolean isAccountLocked) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.isAccountLocked = isAccountLocked;
    }

    /**
     * Retrieves the username of the user.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the unique ID of the user.
     * @return The user's ID.
     */
    public String getId() {
        return id;
    }

    // Note: No setter for passwordHash, and no getter for security reasons.
    // Password hash should only be used internally for comparison.
    // isAccountLocked is not used in this specific sequence diagram, but included as per class diagram.
}