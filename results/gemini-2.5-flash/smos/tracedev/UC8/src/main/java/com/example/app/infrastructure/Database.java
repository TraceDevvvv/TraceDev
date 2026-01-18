package com.example.app.infrastructure;

import com.example.app.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulates a database for user persistence.
 * This class corresponds to the 'Database' in the UML Class Diagram.
 * It uses an in-memory HashMap to store User objects.
 */
public class Database {
    // In-memory storage to simulate a database
    private Map<String, User> userStore;

    public Database() {
        this.userStore = new HashMap<>();
    }

    /**
     * Persists a User object to the database.
     * If a user with the same ID already exists, it updates the existing user.
     * @param user The User object to persist.
     * @return The persisted User object.
     */
    public User persist(User user) {
        System.out.println("Database: Persisting user: " + user.getId() + " - " + user.getUsername());
        userStore.put(user.getId(), user);
        return user; // Return the user as if it was saved and potentially reloaded
    }

    /**
     * Retrieves a User object by its ID from the database.
     * @param id The ID of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    public User retrieve(String id) {
        System.out.println("Database: Retrieving user with ID: " + id);
        return userStore.get(id);
    }
}