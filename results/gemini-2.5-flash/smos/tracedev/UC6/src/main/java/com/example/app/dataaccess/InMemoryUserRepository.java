package com.example.app.dataaccess;

import com.example.app.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An in-memory implementation of the UserRepository interface for demonstration purposes.
 * Stores users in a HashMap.
 */
public class InMemoryUserRepository implements UserRepository {
    // Using ConcurrentHashMap to simulate a thread-safe in-memory store, though not strictly needed for this single-threaded example.
    private final Map<String, User> usersByLogin = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        System.out.println("[UserRepository] Saving user: " + user.getLogin());
        usersByLogin.put(user.getLogin(), user);
        System.out.println("[UserRepository] User " + user.getLogin() + " saved.");
    }

    @Override
    public User findByLogin(String login) {
        System.out.println("[UserRepository] Finding user by login: " + login);
        return usersByLogin.get(login);
    }

    @Override
    public List<User> findAll() {
        System.out.println("[UserRepository] Retrieving all users.");
        return new ArrayList<>(usersByLogin.values());
    }

    /**
     * Clears all users from the repository. Useful for testing.
     */
    public void clear() {
        usersByLogin.clear();
        System.out.println("[UserRepository] All users cleared.");
    }
}