package com.loginapp.service;

import com.loginapp.exception.LoginError;
import com.loginapp.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class responsible for handling user authentication logic.
 * It simulates a user database and provides a method to authenticate users.
 */
public class AuthService {

    // A simple in-memory "database" to store registered users.
    // In a real application, this would interact with a persistent data store (e.g., database).
    private final Map<String, String> registeredUsers;

    /**
     * Constructs an AuthService instance and initializes a few dummy users.
     */
    public AuthService() {
        registeredUsers = new HashMap<>();
        // Add some dummy users for testing
        registeredUsers.put("user1", "pass1");
        registeredUsers.put("admin", "adminpass");
        registeredUsers.put("test", "test");
    }

    /**
     * Authenticates a user based on the provided username and password.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return true if authentication is successful.
     * @throws LoginError if authentication fails due to invalid credentials.
     */
    public boolean authenticate(String username, String password) throws LoginError {
        // Check if the username exists in our registered users
        if (!registeredUsers.containsKey(username)) {
            throw new LoginError("Invalid username.");
        }

        // Get the stored password for the given username
        String storedPassword = registeredUsers.get(username);

        // Compare the provided password with the stored password
        if (!storedPassword.equals(password)) {
            throw new LoginError("Invalid password.");
        }

        // If both username and password are correct, authentication is successful
        return true;
    }
}