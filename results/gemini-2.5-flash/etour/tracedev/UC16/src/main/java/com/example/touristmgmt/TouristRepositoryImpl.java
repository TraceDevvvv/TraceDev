package com.example.touristmgmt;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of ITouristRepository.
 * Manages Tourist data using an in-memory map for demonstration purposes.
 * In a real application, this would interact with a database.
 */
public class TouristRepositoryImpl implements ITouristRepository {

    // Simulating a database connection object. Not actively used here beyond declaration.
    private Object databaseConnection;

    // In-memory store for tourists for demonstration
    private final Map<String, Tourist> touristStore = new HashMap<>();

    public TouristRepositoryImpl() {
        this.databaseConnection = new Object(); // Dummy database connection
        // Populate with some initial data
        touristStore.put("T001", new Tourist("T001", "Alice Smith", "alice.s@example.com"));
        touristStore.put("T002", new Tourist("T002", "Bob Johnson", "bob.j@example.com"));
        touristStore.put("T003", new Tourist("T003", "Charlie Brown", "charlie.b@example.com"));
    }

    /**
     * Deletes a tourist record from the repository based on their ID.
     * Deletion is designed to be irreversible and secure.
     *
     * @param touristId The unique identifier of the tourist to delete.
     */
    @Override
    public void delete(String touristId) {
        if (touristStore.containsKey(touristId)) {
            touristStore.remove(touristId);
            System.out.println("Repository: Successfully deleted tourist with ID: " + touristId);
        } else {
            System.out.println("Repository: Tourist with ID: " + touristId + " not found for deletion.");
        }
    }

    /**
     * Finds a tourist record by their unique ID.
     *
     * @param touristId The unique identifier of the tourist to find.
     * @return The Tourist object if found, otherwise null.
     */
    @Override
    public Tourist findById(String touristId) {
        System.out.println("Repository: Searching for tourist with ID: " + touristId);
        return touristStore.get(touristId);
    }
}