package com.example.infrastructure;

import com.example.domain.TouristData;

import java.io.IOException; // For simulating connection errors
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Infrastructure Layer: Concrete implementation of ITouristRepository.
 * This simulates a data store (e.g., a database) using an in-memory HashMap.
 */
public class TouristRepositoryImpl implements ITouristRepository {

    // Simulates a database table for TouristData
    private final Map<String, TouristData> dataStore = new HashMap<>();
    private boolean simulateConnectionFailure = false; // Flag to simulate ETOUR

    public TouristRepositoryImpl() {
        // Initialize with some dummy data
        dataStore.put("T123", new TouristData("T123", "John Doe", "john.doe@example.com", "123-456-7890", new Date(90, 0, 1))); // Jan 1, 1990
        dataStore.put("T124", new TouristData("T124", "Jane Smith", "jane.smith@example.com", "987-654-3210", new Date(85, 5, 15))); // Jun 15, 1985
    }

    /**
     * Sets a flag to simulate a connection failure.
     * @param fail True to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean fail) {
        this.simulateConnectionFailure = fail;
        System.out.println("Repository: Simulating connection failure set to: " + fail);
    }

    /**
     * Finds a tourist by their unique identifier.
     * Corresponds to `findById(id: String)` in the class diagram.
     * @param id The ID of the tourist to find.
     * @return The TouristData object if found, null otherwise.
     * @throws IOException If a simulated connection error occurs.
     */
    @Override
    public TouristData findById(String id) throws IOException {
        System.out.println("Repository: Searching for tourist with ID: " + id);
        if (simulateConnectionFailure) {
            System.err.println("Repository: Simulating connection failure during findById.");
            throw new IOException("Simulated network connection lost (ETOUR) during findById.");
        }
        TouristData foundData = dataStore.get(id);
        if (foundData != null) {
            System.out.println("Repository: Found tourist: " + foundData.getName());
        } else {
            System.out.println("Repository: Tourist with ID " + id + " not found.");
        }
        return foundData;
    }

    /**
     * Saves (creates or updates) a TouristData object.
     * Corresponds to `save(touristData: TouristData)` in the class diagram.
     * Stereotype <<EnsuresIntegrity>> implies that the repository should handle database-level integrity.
     * @param touristData The TouristData object to save.
     * @throws IOException If a simulated connection error occurs.
     */
    @Override
    public void save(TouristData touristData) throws IOException {
        System.out.println("Repository: Saving tourist data for ID: " + touristData.getId());
        if (simulateConnectionFailure) {
            System.err.println("Repository: Simulating connection failure during save.");
            throw new IOException("Simulated network connection lost (ETOUR) during save.");
        }
        dataStore.put(touristData.getId(), touristData);
        System.out.println("Repository: Tourist data for ID " + touristData.getId() + " saved/updated.");
    }
}