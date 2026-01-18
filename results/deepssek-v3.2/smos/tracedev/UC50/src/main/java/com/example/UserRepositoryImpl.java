package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of UserRepository.
 */
public class UserRepositoryImpl implements UserRepository {
    // In-memory data store
    private Map<String, User> userDataStore = new HashMap<>();

    @Override
    public User findById(String userId) {
        // Return user from map, or null if not present
        return userDataStore.get(userId);
    }

    @Override
    public void save(User user) {
        // Store or update user in map
        userDataStore.put(user.getUserId(), user);
    }
}