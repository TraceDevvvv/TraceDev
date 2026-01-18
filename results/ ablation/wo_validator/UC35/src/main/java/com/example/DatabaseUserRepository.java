package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of UserRepository that simulates a database data source.
 * Uses an in-memory map for demonstration purposes.
 */
public class DatabaseUserRepository implements UserRepository {

    // Simulated data source (in-memory map for demonstration)
    private Map<String, User> userMap = new HashMap<>();

    /**
     * Constructor that initializes some sample users for demonstration.
     */
    public DatabaseUserRepository() {
        // Create a sample user with hashed password (using BCrypt strategy for demo)
        AuthenticationStrategy strategy = new BCryptAuthenticationStrategy();
        String hash = strategy.generateHash("password123");
        User sampleUser = new User("john_doe", hash, "ADMIN");
        userMap.put("john_doe", sampleUser);
    }

    /**
     * Finds a user by username.
     * @param username the username to look up
     * @return the User object if found, otherwise null
     */
    @Override
    public User findByUsername(String username) {
        System.out.println("DatabaseUserRepository: Looking up user '" + username + "'");
        return userMap.get(username);
    }
}