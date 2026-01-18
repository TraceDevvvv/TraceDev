package com.example.infrastructure.persistence;

import com.example.domain.User;
import com.example.ports.UserRepository;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of UserRepository (stub for JPA).
 */
public class JpaUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<>();

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
        System.out.println("Saved user: " + user.getId());
    }

    // Helper method for testing
    public void addUser(User user) {
        save(user);
    }
}