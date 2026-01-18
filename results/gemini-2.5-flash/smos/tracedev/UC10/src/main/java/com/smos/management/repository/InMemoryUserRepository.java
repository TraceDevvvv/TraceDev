package com.smos.management.repository;

import com.smos.management.exceptions.SMOSConnectionException;
import com.smos.management.model.Role;
import com.smos.management.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * An in-memory implementation of IUserRepository for testing and demonstration purposes.
 * Simulates data storage using a HashMap.
 */
public class InMemoryUserRepository implements IUserRepository {
    private final Map<String, User> users = new HashMap<>();
    private boolean simulateConnectionError = false;

    public InMemoryUserRepository() {
        // Populate with some initial users and roles
        User adminUser = new User("user123", "AliceAdmin");
        adminUser.addRole(new Role("R1", "ROLE_ADMIN"));
        adminUser.addRole(new Role("R2", "ROLE_EDITOR"));
        users.put("user123", adminUser);

        User regularUser = new User("user456", "BobRegular");
        regularUser.addRole(new Role("R4", "ROLE_VIEWER"));
        users.put("user456", regularUser);
    }

    /**
     * Configures the repository to simulate a connection error for testing purposes.
     *
     * @param simulateConnectionError true to simulate an error, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }

    @Override
    public User findById(String userId) throws SMOSConnectionException {
        if (simulateConnectionError) {
            throw new SMOSConnectionException("Simulated connection error during findById user: " + userId);
        }
        // Return a defensive copy to prevent external modification of the stored object's list
        User originalUser = users.get(userId);
        if (originalUser != null) {
            // Simulate deep copy for roles
            User copiedUser = new User(originalUser.getUserId(), originalUser.getUsername());
            originalUser.getRoles().forEach(copiedUser::addRole);
            System.out.println("UserRepo: findById(" + userId + ") called, found user: " + copiedUser.getUsername());
            return copiedUser;
        }
        System.out.println("UserRepo: findById(" + userId + ") called, user not found.");
        return null;
    }

    @Override
    public void save(User user) throws SMOSConnectionException {
        if (simulateConnectionError) {
            throw new SMOSConnectionException("Simulated connection error during save user: " + user.getUserId());
        }
        if (user != null) {
            // In a real system, this would persist the user. Here, we update the in-memory map.
            // Create a defensive copy of the user to store, preventing external references
            User userToStore = new User(user.getUserId(), user.getUsername());
            user.getRoles().forEach(userToStore::addRole); // Copy roles as well
            users.put(userToStore.getUserId(), userToStore);
            System.out.println("UserRepo: save(" + user.getUserId() + ") called. User updated: " + userToStore);
        } else {
            System.err.println("UserRepo: Attempted to save a null user.");
        }
    }
}