package com.example.app.repository;

import com.example.app.model.User;
import com.example.app.exception.ConnectionError;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IUserRepository.
 * This class simulates a data access layer, fetching User objects from a "database".
 * It includes logic to simulate a ConnectionError as per the sequence diagram.
 */
public class UserRepository implements IUserRepository {

    // In-memory "database" for demonstration purposes
    private final Map<String, User> users;
    // Flag to simulate connection errors
    private boolean simulateConnectionError = false;

    /**
     * Constructor initializes the in-memory user data.
     */
    public UserRepository() {
        users = new HashMap<>();
        users.put("user123", new User("user123", "John", "Doe", "john.doe@example.com", "123-456-7890", "johndoe", "pass123"));
        users.put("admin456", new User("admin456", "Jane", "Smith", "jane.smith@example.com", "098-765-4321", "janesmith", "securepass"));
        // Add more dummy users as needed
    }

    /**
     * Simulates setting a flag to trigger a connection error in subsequent calls.
     * @param simulate true to simulate connection errors, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulate) {
        this.simulateConnectionError = simulate;
    }

    /**
     * Finds a User by their unique ID.
     * @param userId The ID of the user to find.
     * @return The User object if found, or null if not found (and no connection error).
     * @throws ConnectionError If the simulation flag is set, or if an actual connection issue occurs.
     */
    @Override
    public User findById(String userId) throws ConnectionError {
        // Simulate a connection error if the flag is set
        if (simulateConnectionError) {
            System.out.println("DEBUG: Simulating ConnectionError in UserRepository.findById()");
            throw new ConnectionError("Simulated: Database connection interrupted.");
        }

        // Simulate database lookup
        User user = users.get(userId);
        if (user == null) {
            System.out.println("DEBUG: User with ID '" + userId + "' not found in repository.");
        }
        return user;
    }
}