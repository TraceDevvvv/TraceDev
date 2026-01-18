package com.example.repository;

import com.example.model.User;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple in-memory implementation of UserRepository for demonstration.
 * In a real application, this would use a database via DataSource.
 */
public class UserRepositoryImpl implements UserRepository {
    private DataSource dataSource;
    // In-memory storage for demo purposes
    private static final Map<String, User> userStore = new HashMap<>();

    static {
        // Initialize with a sample user for testing
        userStore.put("john_doe", new User("u001", "john_doe", "hashed_old_password"));
    }

    public UserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findByUsername(String username) {
        System.out.println("UserRepositoryImpl.findByUsername called for username: " + username);
        // Simulate database lookup
        return userStore.get(username);
    }

    @Override
    public void updateUser(User user) {
        System.out.println("UserRepositoryImpl.updateUser called for user: " + user.getUsername());
        // Update the user in the store
        userStore.put(user.getUsername(), user);
        // In a real app, this would execute a SQL update via dataSource
    }
}