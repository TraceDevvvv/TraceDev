package com.example;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing user-related business logic.
 */
public class UserManagementService {
    private IUserRepository userRepository;

    /**
     * Constructs a UserManagementService with an injected UserRepository.
     * @param userRepository The repository for user data access.
     */
    public UserManagementService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Deletes a user by their ID.
     * @param userId The ID of the user to delete.
     * @return true if the user was successfully deleted, false otherwise.
     */
    public boolean deleteUser(String userId) {
        System.out.println("UserManagementService: Attempting to delete user " + userId);
        // Delegates the deletion to the repository
        return userRepository.deleteUser(userId);
    }

    /**
     * Retrieves a list of all users.
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        System.out.println("UserManagementService: Retrieving all users.");
        // Delegates to the repository to find all users
        return userRepository.findAllUsers();
    }

    /**
     * Finds a user by ID.
     * Added for displayUserDetails functionality, which needs to retrieve a single user.
     * @param userId The ID of the user to find.
     * @return An Optional containing the User if found, empty otherwise.
     */
    public Optional<User> findUserById(String userId) {
        System.out.println("UserManagementService: Finding user by ID " + userId);
        return userRepository.findUserById(userId);
    }
}