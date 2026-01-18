package com.example.repository;

import com.example.model.User;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for managing User entities.
 */
public class UserRepository {
    // In-memory storage for demonstration.
    private Map<String, User> users = new HashMap<>();

    /**
     * Finds a user by their ID.
     * @param userId the user ID
     * @return the User object, or null if not found
     */
    public User findById(String userId) {
        return users.get(userId);
    }

    /**
     * Saves (or updates) a user.
     * @param user the user to save
     */
    public void save(User user) {
        users.put(user.getUserId(), user);
    }
}