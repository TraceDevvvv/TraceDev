package com.example.agencyapp.repository;

import com.example.agencyapp.entity.User;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of UserRepository for demonstration.
 */
public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> userStore = new HashMap<>();

    public InMemoryUserRepository() {
        // Initialize with a sample agency operator
        userStore.put("operator1", new User("operator1", "oldPass123", "AGENCY_OPERATOR"));
    }

    @Override
    public boolean updateUser(User user) {
        // Simulate update; in real scenario, this would persist to database.
        if (user == null || user.getUsername() == null) {
            return false;
        }
        userStore.put(user.getUsername(), user);
        return true;
    }

    public User findByUsername(String username) {
        return userStore.get(username);
    }
}