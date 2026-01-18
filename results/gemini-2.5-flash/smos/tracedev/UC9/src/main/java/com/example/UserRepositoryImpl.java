package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Concrete implementation of IUserRepository.
 * Manages user data and integrates with connection and security serv.
 */
public class UserRepositoryImpl implements IUserRepository {
    // In-memory archive for storing users.
    private Map<String, User> archive;
    // Dependency for handling external system connections (R9).
    private IConnectionHandler connectionHandler;
    // Dependency for security operations (R10).
    private ISecurityService securityService;

    /**
     * Constructs a UserRepositoryImpl with injected dependencies.
     * @param connectionHandler The connection handler for external systems (R9).
     * @param securityService The security service for data erasure (R10).
     */
    public UserRepositoryImpl(IConnectionHandler connectionHandler, ISecurityService securityService) {
        this.archive = new HashMap<>();
        this.connectionHandler = connectionHandler; // Modified to satisfy requirement R9
        this.securityService = securityService;     // Modified to satisfy requirement R10

        // Assumption: Populate with some dummy data for demonstration.
        archive.put("U001", new User("U001", "alice", "alice@example.com"));
        archive.put("U002", new User("U002", "bob", "bob@example.com"));
        archive.put("U003", new User("U003", "charlie", "charlie@example.com"));
    }

    @Override
    public boolean deleteUser(String userId) {
        System.out.println("UserRepositoryImpl: Attempting to delete user with ID: " + userId);

        // R9: Check connection before attempting to delete (e.g., from an external SMOS system or related data)
        if (!connectionHandler.checkConnection()) {
            System.out.println("UserRepositoryImpl: Deletion failed. Connection to SMOS server interrupted.");
            return false; // Indicate deletion failure due to connection
        }

        // Check if user exists in the archive before proceeding
        if (!archive.containsKey(userId)) {
            System.out.println("UserRepositoryImpl: User with ID " + userId + " not found in archive. No deletion performed.");
            return false; // User not found, so cannot delete.
        }

        // R10: Securely erase data before removing from local archive
        boolean eraseSuccess = securityService.securelyEraseData(userId);
        if (!eraseSuccess) {
            System.out.println("UserRepositoryImpl: Deletion failed. Secure erase operation failed for user " + userId);
            return false; // Indicate deletion failure due to secure erase failure
        }

        // If connection is successful and secure erase succeeded, proceed with internal deletion
        archive.remove(userId);
        System.out.println("UserRepositoryImpl: User with ID " + userId + " successfully deleted from internal archive.");
        return true;
    }

    @Override
    public List<User> findAllUsers() {
        System.out.println("UserRepositoryImpl: Retrieving all users.");
        return new ArrayList<>(archive.values());
    }

    @Override
    public Optional<User> findUserById(String userId) {
        System.out.println("UserRepositoryImpl: Finding user by ID: " + userId);
        return Optional.ofNullable(archive.get(userId));
    }
}