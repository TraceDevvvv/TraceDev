/*
Service class to handle the business logic for loading and saving
SearchPreferences for a Tourist.
This class simulates interaction with a backend system or database.
It also simulates potential server connection interruptions.
*/
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
public class PreferenceService {
    // A static map to simulate persistence for different users.
    // In a real application, this would be a database or remote service.
    private static final Map<String, SearchPreferences> userPreferencesStore = new HashMap<>();
    // Simulate potential network issues/server interruptions
    private static final Random random = new Random();
    private static final double CONNECTION_FAILURE_RATE = 0.1; // 10% chance of failure
    /**
     * Simulates loading search preferences for a given tourist.
     * It may simulate a connection interruption.
     *
     * @param tourist The Tourist whose preferences are to be loaded.
     * @return An Optional containing the SearchPreferences if found, otherwise empty.
     * @throws ConnectionInterruptionException If a simulated connection failure occurs.
     */
    public Optional<SearchPreferences> loadPreferences(Tourist tourist) throws ConnectionInterruptionException {
        // Simulate network delay
        try {
            Thread.sleep(200); // Small delay to simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            throw new ConnectionInterruptionException("Failed to load preferences due to interrupted operation.", e);
        }
        // Simulate connection interruption
        if (random.nextDouble() < CONNECTION_FAILURE_RATE) {
            throw new ConnectionInterruptionException("Simulated interruption of connection to the server ETOUR during load.");
        }
        // Return a copy to prevent direct modification of stored object
        SearchPreferences storedPrefs = userPreferencesStore.get(tourist.getUserId());
        return Optional.ofNullable(storedPrefs != null ? copyPreferences(storedPrefs) : null);
    }
    /**
     * Simulates saving search preferences for a given tourist.
     * It may simulate a connection interruption.
     *
     * @param tourist The Tourist whose preferences are to be saved.
     * @param preferences The SearchPreferences object to save.
     * @throws ConnectionInterruptionException If a simulated connection failure occurs.
     * @throws IllegalArgumentException If the preferences object is null.
     */
    public void savePreferences(Tourist tourist, SearchPreferences preferences) throws ConnectionInterruptionException {
        if (preferences == null) {
            throw new IllegalArgumentException("Preferences cannot be null.");
        }
        // Simulate network delay
        try {
            Thread.sleep(300); // Small delay to simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            throw new ConnectionInterruptionException("Failed to save preferences due to interrupted operation.", e);
        }
        // Simulate connection interruption
        if (random.nextDouble() < CONNECTION_FAILURE_RATE) {
            throw new ConnectionInterruptionException("Simulated interruption of connection to the server ETOUR during save.");
        }
        // Store a copy to prevent external modification of the stored object
        userPreferencesStore.put(tourist.getUserId(), copyPreferences(preferences));
        System.out.println("Preferences saved successfully for " + tourist.getUserId() + ": " + preferences);
    }
    /**
     * Helper method to create a deep copy of SearchPreferences.
     * Essential to ensure that the retrieved/stored objects are independent
     * of the objects passed from/to the UI.
     *
     * @param original The original SearchPreferences object.
     * @return A new SearchPreferences object with copied values.
     */
    private SearchPreferences copyPreferences(SearchPreferences original) {
        return new SearchPreferences(
                original.getDestination(),
                original.getMinPrice(),
                original.getMaxPrice(),
                new HashSet<>(original.getCategories()) // Copy the set
        );
    }
    /**
     * Custom exception for simulating connection failures.
     */
    public static class ConnectionInterruptionException extends Exception {
        public ConnectionInterruptionException(String message) {
            super(message);
        }
        public ConnectionInterruptionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    /**
     * For demonstration purposes, pre-populate some preferences for a mock user.
     * This ensures the application can load something at startup.
     */
    static {
        SearchPreferences defaultPrefs = new SearchPreferences(
                "Paris",
                100.0,
                500.0,
                new HashSet<>(java.util.Arrays.asList("Culture", "Food"))
        );
        userPreferencesStore.put("tourist123", defaultPrefs);
        SearchPreferences anotherUserPrefs = new SearchPreferences(
                "Rome",
                50.0,
                300.0,
                new HashSet<>(java.util.Arrays.asList("History", "Food", "Art"))
        );
        userPreferencesStore.put("user456", anotherUserPrefs);
    }
}