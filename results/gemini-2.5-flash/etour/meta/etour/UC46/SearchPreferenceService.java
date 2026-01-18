package com.example.touristapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service class to handle the business logic for managing search preferences.
 * This includes loading, updating, and persisting search preferences for tourists.
 */
public class SearchPreferenceService {

    // In a real application, this would be a database or a persistent storage.
    // Using a HashMap for demonstration purposes to simulate storage.
    private final Map<String, SearchPreferences> userPreferencesStore;

    /**
     * Constructs a new SearchPreferenceService.
     */
    public SearchPreferenceService() {
        this.userPreferencesStore = new HashMap<>();
        // Pre-populate with some dummy data for demonstration
        userPreferencesStore.put("testUser", new SearchPreferences("Paris", 100.0, 1000.0, 2, "Summer 2024"));
    }

    /**
     * Loads the search preferences for a given tourist.
     *
     * @param tourist The tourist whose preferences are to be loaded.
     * @return An Optional containing the SearchPreferences if found, otherwise empty.
     * @throws IllegalStateException if the tourist is not authenticated.
     */
    public Optional<SearchPreferences> loadPreferences(Tourist tourist) {
        if (!tourist.isAuthenticated()) {
            throw new IllegalStateException("Tourist must be authenticated to load preferences.");
        }
        // Simulate fetching from a data store
        return Optional.ofNullable(userPreferencesStore.get(tourist.getUsername()));
    }

    /**
     * Updates and persists the search preferences for a given tourist.
     * This method includes validation and simulates saving to a data store.
     *
     * @param tourist The tourist whose preferences are to be updated.
     * @param newPreferences The new search preferences to save.
     * @return true if preferences were successfully updated and saved, false otherwise.
     * @throws IllegalStateException if the tourist is not authenticated.
     * @throws IllegalArgumentException if the new preferences are invalid (e.g., minPrice > maxPrice).
     */
    public boolean updatePreferences(Tourist tourist, SearchPreferences newPreferences) {
        if (!tourist.isAuthenticated()) {
            throw new IllegalStateException("Tourist must be authenticated to update preferences.");
        }

        // Validate new preferences before saving
        if (newPreferences.getMinPrice() > newPreferences.getMaxPrice()) {
            throw new IllegalArgumentException("Minimum price cannot be greater than maximum price.");
        }
        if (newPreferences.getNumberOfTravelers() <= 0) {
            throw new IllegalArgumentException("Number of travelers must be at least 1.");
        }

        // Simulate saving to a data store
        userPreferencesStore.put(tourist.getUsername(), newPreferences);
        System.out.println("Preferences for " + tourist.getUsername() + " updated successfully.");
        return true;
    }

    /**
     * Simulates a connection interruption to the ETOUR server.
     * In a real scenario, this would be handled by network error catching.
     * For this simulation, it just prints a message.
     */
    public void simulateConnectionInterruption() {
        System.err.println("Connection to ETOUR server interrupted. Please try again later.");
        // In a real system, this might throw a custom exception or return a specific error code.
    }

    /**
     * Provides a way to initialize preferences for a new user or for testing.
     * @param username The username for whom to set initial preferences.
     * @param preferences The initial preferences.
     */
    public void initializePreferencesForUser(String username, SearchPreferences preferences) {
        userPreferencesStore.put(username, preferences);
    }
}