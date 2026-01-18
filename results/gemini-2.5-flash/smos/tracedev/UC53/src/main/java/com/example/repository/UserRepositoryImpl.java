package com.example.repository;

import com.example.model.User;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of IUserRepository.
 * This class simulates data access to a database for User objects.
 * Added to satisfy authentication and authorization requirements.
 */
public class UserRepositoryImpl implements IUserRepository {

    // Simulate an in-memory database of users
    private final Map<String, User> users;

    public UserRepositoryImpl() {
        users = new HashMap<>();
        users.put("staff123", new User("staff123", "ATA Staff User", Arrays.asList("ATA staff", "admin")));
        users.put("student456", new User("student456", "Student User", Arrays.asList("student")));
        // A user that is logged in but doesn't have the staff role
        users.put("manager789", new User("manager789", "Manager User", Arrays.asList("manager")));
    }

    /**
     * Simulates finding a user by ID from a database.
     * @param id The unique identifier of the user.
     * @return The User object if found, otherwise null.
     */
    @Override
    public User findById(String id) {
        System.out.println("  UserRepository: Searching for user with ID: " + id);
        User user = users.get(id);
        if (user != null) {
            System.out.println("  UserRepository: User found: " + user.getUsername());
        } else {
            System.out.println("  UserRepository: User not found for ID: " + id);
        }
        return user; // Simulate database lookup
    }
}