package com.example.touristapp.repository;

import com.example.touristapp.infrastructure.DatabaseConnection;
import com.example.touristapp.model.SearchPreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Concrete implementation of ISearchPreferencesRepository using an in-memory map
 * to simulate database storage. It depends on a DatabaseConnection.\n */
public class SearchPreferencesRepositoryImpl implements ISearchPreferencesRepository {

    private DatabaseConnection databaseConnection;
    // In-memory storage to simulate database
    private Map<String, SearchPreferences> preferencesStore = new HashMap<>(); // Key: touristId

    /**
     * Constructor for SearchPreferencesRepositoryImpl.\n     * @param databaseConnection The database connection instance.\n     */
    public SearchPreferencesRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        // Populate with some initial data for demonstration
        String initialTouristId = "T1001";
        String initialPreferencesId = UUID.randomUUID().toString();
        preferencesStore.put(initialTouristId, new SearchPreferences(initialPreferencesId, initialTouristId, "Paris", "Medium", "Summer 2024"));
        System.out.println("[SearchPreferencesRepositoryImpl] Initialized with dummy data for tourist: " + initialTouristId);
    }

    @Override
    public SearchPreferences findByTouristId(String touristId) {
        System.out.println("[SearchPreferencesRepositoryImpl] Finding preferences for tourist ID: " + touristId);
        if (!databaseConnection.isConnected()) {
            System.err.println("[SearchPreferencesRepositoryImpl] Database connection interrupted during find operation.");
            // In a real scenario, this might throw an exception or return null based on error handling policy.
            return null;
        }
        // Return a copy to prevent external modification of the stored entity
        SearchPreferences found = preferencesStore.get(touristId);
        return found != null ? new SearchPreferences(found.getPreferencesId(), found.getTouristId(),
                                                  found.getDestination(), found.getBudgetRange(), found.getTravelDates()) : null;
    }

    @Override
    public void save(SearchPreferences preferences) throws Exception {
        System.out.println("[SearchPreferencesRepositoryImpl] Attempting to save preferences for tourist ID: " + preferences.getTouristId());
        if (!databaseConnection.isConnected()) {
            String errorMessage = "Database connection interrupted while saving preferences for tourist ID: " + preferences.getTouristId();
            System.err.println("[SearchPreferencesRepositoryImpl] " + errorMessage);
            throw new Exception(errorMessage); // Simulate ConnectionError from sequence diagram
        }

        // If preferencesId is null, it's a new entry, generate one.
        // In a real app, this might be handled by the database or a factory.
        String actualPreferencesId = preferences.getPreferencesId();
        if (actualPreferencesId == null || actualPreferencesId.isEmpty()) {
            actualPreferencesId = UUID.randomUUID().toString();
            // Create a new SearchPreferences object with the generated ID for storage
            preferences = new SearchPreferences(actualPreferencesId, preferences.getTouristId(),
                                                preferences.getDestination(), preferences.getBudgetRange(), preferences.getTravelDates());
        }

        // Store or update in the map. Assuming touristId is unique for preferences.
        preferencesStore.put(preferences.getTouristId(), preferences);
        System.out.println("[SearchPreferencesRepositoryImpl] Saved preferences for tourist ID: " + preferences.getTouristId() + ". Current state: " + preferencesStore.get(preferences.getTouristId()));
    }
}