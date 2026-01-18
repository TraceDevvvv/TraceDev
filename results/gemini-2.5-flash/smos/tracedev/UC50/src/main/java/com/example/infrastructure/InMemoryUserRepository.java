package com.example.infrastructure;

import com.example.domain.User;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of IUserRepository for demonstration purposes.
 * Stores User objects in a HashMap.
 */
public class InMemoryUserRepository implements IUserRepository {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, User> usersByUsername = new HashMap<>(); // For findByUsername

    @Override
    public void save(User user) {
        System.out.println("[Repo] Saving User: " + user.getUserId());
        users.put(user.getUserId(), user);
        usersByUsername.put(user.getUsername(), user);
    }

    @Override
    public User findById(String userId) {
        System.out.println("[Repo] Finding User by ID: " + userId);
        return users.get(userId);
    }

    @Override
    public User findByUsername(String username) {
        System.out.println("[Repo] Finding User by Username: " + username);
        return usersByUsername.get(username);
    }
}