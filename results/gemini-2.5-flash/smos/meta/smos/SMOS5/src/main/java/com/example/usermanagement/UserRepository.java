package com.example.usermanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Handles user data storage and retrieval.
 * This class simulates a data access layer for User objects, providing methods
 * to retrieve user information from a "database" (in this case, an in-memory list).
 */
public class UserRepository {

    // Simulate a database with a static list of users.
    // In a real application, this would interact with a persistent storage like a database.
    private static final List<User> users = new ArrayList<>();

    static {
        // Initialize with some sample users.
        // Ensure there's at least one administrator for the precondition.
        users.add(new User("admin1", "admin", "Administrator"));
        users.add(new User("user1", "john.doe", "RegularUser"));
        users.add(new User("user2", "jane.smith", "RegularUser"));
        users.add(new User("manager1", "mike.manager", "Manager"));
    }

    /**
     * Retrieves all users currently stored in the system.
     * This method simulates fetching all user records from a data source.
     *
     * @return A read-only list of all User objects. Returns an empty list if no users are found.
     */
    public List<User> findAllUsers() {
        // Return an unmodifiable list to prevent external modification of the internal data store.
        return Collections.unmodifiableList(users);
    }

    /**
     * Finds a user by their username.
     * This method is useful for authentication purposes, e.g., checking if a user exists
     * and retrieving their details for role verification.
     *
     * @param username The username to search for.
     * @return The User object if found, otherwise null.
     */
    public User findByUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return null; // Invalid username provided
        }
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username.trim())) {
                return user;
            }
        }
        return null; // User not found
    }
}