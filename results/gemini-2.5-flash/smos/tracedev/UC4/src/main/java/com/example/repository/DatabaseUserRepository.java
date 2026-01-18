package com.example.repository;

import com.example.model.User;
import com.example.exception.ConnectionLostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IUserRepository that simulates interaction with a database.
 * Uses an in-memory map for storage and can simulate connection loss.
 */
public class DatabaseUserRepository implements IUserRepository {
    // Represents a connection to the database - for simulation purposes, this is an Object.
    // In a real application, this would be a real database connection or connection pool.
    private Object dbConnection;

    // In-memory store to simulate database records
    private Map<String, User> users;

    // Flag to simulate database connection loss for findById method
    private boolean simulateFindByIdConnectionLoss = false;
    // Flag to simulate database connection loss for update method
    private boolean simulateUpdateConnectionLoss = false;

    /**
     * Constructs a new DatabaseUserRepository.
     * Initializes an in-memory user store and a mock database connection.
     */
    public DatabaseUserRepository() {
        this.dbConnection = new Object(); // Mock database connection
        this.users = new HashMap<>();
        // Pre-populate with a test user
        users.put("user123", new User("user123", "testuser", "argon2hashed_oldPass123"));
        System.out.println("[DatabaseUserRepository] Initialized with test user: user123 (oldPass123)");
    }

    @Override
    public User findById(String userId) throws ConnectionLostException {
        System.out.println("[DatabaseUserRepository] Querying database for user: " + userId);
        if (simulateFindByIdConnectionLoss) {
            System.err.println("[DatabaseUserRepository] Simulating ConnectionLostError during findById.");
            throw new ConnectionLostException("Database connection lost during user retrieval.");
        }
        // Simulate DB lookup delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        User user = users.get(userId);
        System.out.println(user != null ? "[DatabaseUserRepository] Found user: " + user.getUsername() : "[DatabaseUserRepository] User not found.");
        return user;
    }

    @Override
    public boolean update(User user) throws ConnectionLostException {
        if (user == null) {
            System.err.println("[DatabaseUserRepository] Attempted to update a null user.");
            return false;
        }
        System.out.println("[DatabaseUserRepository] Updating user: " + user.getId());
        if (simulateUpdateConnectionLoss) {
            System.err.println("[DatabaseUserRepository] Simulating ConnectionLostError during update.");
            throw new ConnectionLostException("Database connection lost during user update.");
        }
        // Simulate DB update delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user); // Update the user
            System.out.println("[DatabaseUserRepository] User " + user.getId() + " updated successfully.");
            return true;
        }
        System.err.println("[DatabaseUserRepository] User " + user.getId() + " not found for update.");
        return false;
    }

    /**
     * Sets the simulation flag for connection loss during findById.
     * @param simulate true to simulate connection loss, false otherwise.
     */
    public void setSimulateFindByIdConnectionLoss(boolean simulate) {
        this.simulateFindByIdConnectionLoss = simulate;
    }

    /**
     * Sets the simulation flag for connection loss during update.
     * @param simulate true to simulate connection loss, false otherwise.
     */
    public void setSimulateUpdateConnectionLoss(boolean simulate) {
        this.simulateUpdateConnectionLoss = simulate;
    }
}