package com.example.login;

import java.util.List;
import java.util.Objects; // For Objects.equals in isValidPassword

/**
 * Represents a user in the system.
 * Contains user details like username, hashed password, and privileges.
 */
public class User {
    private String username;
    private String hashedPassword;
    private List<String> privileges;

    /**
     * Constructs a User object.
     * @param username The unique username of the user.
     * @param hashedPassword The hashed password of the user.
     * @param privileges A list of privileges assigned to the user.
     */
    public User(String username, String hashedPassword, List<String> privileges) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.privileges = privileges;
    }

    /**
     * Retrieves the username of the user.
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Checks if the provided password matches the stored hashed password.
     * In a real application, this would involve hashing the input password
     * and comparing hashes securely. For this example, it's a direct string comparison.
     * @param password The password to validate.
     * @return true if the password is valid, false otherwise.
     */
    public boolean isValidPassword(String password) {
        // In a real system, 'password' would be hashed and then compared to 'hashedPassword'.
        // For simplicity, we directly compare it here.
        return Objects.equals(this.hashedPassword, password);
    }

    /**
     * Retrieves the list of privileges for the user.
     * @return A list of privileges.
     */
    public List<String> getPrivileges() {
        return privileges;
    }
}