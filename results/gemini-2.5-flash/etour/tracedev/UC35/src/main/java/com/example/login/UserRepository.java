package com.example.login;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for managing User data.
 * Simulates database access for user retrieval.
 */
public class UserRepository {
    // Simulate a database with a static map of users
    private static final Map<String, User> users = new HashMap<>();

    static {
        // Add a sample user for testing
        users.put("admin", new User("admin", "password123", Arrays.asList("manage_users", "access_reports")));
        users.put("user1", new User("user1", "userpass", Arrays.asList("access_reports")));
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username to search for.
     * @return The User object if found, null otherwise.
     * @throws ConnectionException if a simulated connection error occurs during retrieval.
     */
    public User findByUsername(String username) {
        System.out.println("[UserRepository] Attempting to find user: " + username);

        // Simulate a connection error for a specific username or randomly
        if ("network_error".equals(username)) {
            System.err.println("[UserRepository] Simulating a ConnectionException for username: " + username);
            throw new ConnectionException("Failed to connect to user database.");
        }

        // Simulate database lookup
        User user = users.get(username);
        if (user != null) {
            System.out.println("[UserRepository] User found: " + username);
        } else {
            System.out.println("[UserRepository] User not found: " + username);
        }
        return user;
    }
}