package com.example.repository;

import com.example.entity.User;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete Repository (Adapter) that stores users in memory.
 */
public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<>();

    public InMemoryUserRepository() {
        // Pre-populate with some users for testing
        users.put("alice", new User("alice", "password123"));
        users.put("bob", new User("bob", "securepass"));
        users.put("charlie", new User("charlie", "test123"));
    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public User searchInArchive(String username) {
        // For simplicity, we treat archive search same as normal find.
        // In a real system, this might search a different data source.
        return findByUsername(username);
    }

    public User returnUserObject() {
        // This method corresponds to m28 in sequence diagram: return User object.
        // In the sequence diagram, m28 is a return message from Repository to Interactor.
        // The actual return is the result of searchInArchive, so we don't need a separate method.
        // We'll keep the existing logic; this method is just a placeholder to satisfy traceability.
        // It could be used to return a default user for testing.
        return null;
    }
}