package com.etour.login.repository;

import com.etour.login.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of UserRepository.
 * In a real application, this would connect to a database.
 * For demonstration, we use an in-memory list.
 */
public class UserRepositoryImpl implements UserRepository {
    // Simulating a database with an in-memory list
    private List<User> userDatabase;

    public UserRepositoryImpl() {
        userDatabase = new ArrayList<>();
        // Adding a sample user for testing
        // Password "password123" encrypted with BCrypt (dummy hash)
        userDatabase.add(new User("user123", "john_doe", "$2a$10$dummyhashfordemo", List.of("READ", "WRITE")));
        userDatabase.add(new User("user456", "jane_doe", "$2a$10$anotherdummyhash", List.of("READ")));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        // Simulate database query with performance optimization
        return userDatabase.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }
}