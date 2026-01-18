package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;

import java.util.List;

/**
 * The Application Service Layer component responsible for user-related business logic.
 * It orchestrates data retrieval from the repository and can incorporate caching logic.
 */
public class UserService {
    private UserRepository userRepository;

    /**
     * Constructs a new UserService with the specified UserRepository.
     * @param userRepository The data access object for users.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all users from the system.
     * Considers caching for efficiency (R9). For this simulation, caching is not explicitly implemented
     * but the method signature is set up to allow for it.
     * @return A list of all User objects.
     */
    public List<User> getAllUsers() {
        System.out.println("[UserService] Requesting all users from repository.");
        // In a real application, caching logic would go here.
        // For example:
        // if (cache.contains("allUsers")) {
        //     return cache.get("allUsers");
        // }
        List<User> users = userRepository.findAllUsers();
        // cache.put("allUsers", users); // Store in cache if not found
        System.out.println("[UserService] Received " + users.size() + " users from repository.");
        return users;
    }
}