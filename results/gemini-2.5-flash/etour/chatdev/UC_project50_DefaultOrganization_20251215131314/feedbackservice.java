/**
 * Simulates an ETOUR server service for fetching sites with feedback.
 * This class handles hypothetical data retrieval and simulates network issues.
 */
package com.chatdev.etour;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 * Simulates an ETOUR server service for fetching sites with feedback.
 * This class handles hypothetical data retrieval and simulates network issues (e.g., delays, interruptions).
 * It provides mock data for different tourist IDs.
 */
public class FeedbackService {
    // Simple in-memory data store for demonstration
    private static final List<Site> MOCK_ALL_FEEDBACK_DATA = new ArrayList<>();
    private static final Random random = new Random();
    static {
        // Initialize with some dummy data for various sites.
        // In a real application, feedback entries would be properly linked to a tourist ID
        // in a database or external service.
        MOCK_ALL_FEEDBACK_DATA.add(new Site("Eiffel Tower", "Paris, France"));
        MOCK_ALL_FEEDBACK_DATA.add(new Site("Louvre Museum", "Paris, France"));
        MOCK_ALL_FEEDBACK_DATA.add(new Site("Colosseum", "Rome, Italy"));
        MOCK_ALL_FEEDBACK_DATA.add(new Site("Statue of Liberty", "New York, USA"));
        MOCK_ALL_FEEDBACK_DATA.add(new Site("Times Square", "New York, USA"));
        MOCK_ALL_FEEDBACK_DATA.add(new Site("Big Ben", "London, UK"));
        MOCK_ALL_FEEDBACK_DATA.add(new Site("Buckingham Palace", "London, UK"));
        MOCK_ALL_FEEDBACK_DATA.add(new Site("Sagrada Familia", "Barcelona, Spain"));
        MOCK_ALL_FEEDBACK_DATA.add(new Site("Acropolis of Athens", "Athens, Greece"));
    }
    /**
     * Simulates fetching a list of sites for which a given tourist has issued feedback.
     * This method also simulates potential network interruptions or server errors.
     *
     * @param touristId The unique identifier of the tourist.
     * @return An unmodifiable list of Site objects if successful.
     * @throws ConnectionInterruptionException if a simulated connection failure occurs.
     */
    public List<Site> getSitesWithFeedback(String touristId) throws ConnectionInterruptionException {
        // Simulate network delay to mimic real network latency
        try {
            Thread.sleep(1500 + random.nextInt(1000)); // Simulate 1.5 to 2.5 seconds delay
        } catch (InterruptedException e) {
            // Restore the interrupted status for proper thread handling
            Thread.currentThread().interrupt();
        }
        // Simulate connection interruption with a 20% chance
        if (random.nextInt(100) < 20) {
            throw new ConnectionInterruptionException("Connection to ETOUR server interrupted.");
        }
        // Filter and return mock data based on touristId.
        // This is a simplified filtering for demonstration purposes.
        List<Site> touristSites = new ArrayList<>();
        String lowerCaseTouristId = touristId.toLowerCase();
        switch (lowerCaseTouristId) {
            case "user1":
                // Assign specific sites to "user1"
                touristSites.add(MOCK_ALL_FEEDBACK_DATA.get(0)); // Eiffel Tower
                touristSites.add(MOCK_ALL_FEEDBACK_DATA.get(1)); // Louvre Museum
                touristSites.add(MOCK_ALL_FEEDBACK_DATA.get(2)); // Colosseum
                break;
            case "user2":
                // Assign specific sites to "user2"
                touristSites.add(MOCK_ALL_FEEDBACK_DATA.get(3)); // Statue of Liberty
                touristSites.add(MOCK_ALL_FEEDBACK_DATA.get(4)); // Times Square
                touristSites.add(MOCK_ALL_FEEDBACK_DATA.get(8)); // Acropolis
                break;
            case "user3":
                // Assign specific sites to "user3"
                touristSites.add(MOCK_ALL_FEEDBACK_DATA.get(5)); // Big Ben
                touristSites.add(MOCK_ALL_FEEDBACK_DATA.get(6)); // Buckingham Palace
                touristSites.add(MOCK_ALL_FEEDBACK_DATA.get(7)); // Sagrada Familia
                break;
            default:
                // For any other touristId, return an empty list or a subset to simulate
                // different user data. For this example, an empty list.
                break;
        }
        // Return an unmodifiable list to prevent external modification of the returned data.
        return Collections.unmodifiableList(touristSites);
    }
    /**
     * Custom exception to represent a simulated connection interruption to the ETOUR server.
     * This allows for specific error handling in the UI.
     */
    public static class ConnectionInterruptionException extends Exception {
        /**
         * Constructs a new ConnectionInterruptionException with the specified detail message.
         * @param message The detail message.
         */
        public ConnectionInterruptionException(String message) {
            super(message);
        }
        /**
         * Constructs a new ConnectionInterruptionException with the specified detail message and cause.
         * @param message The detail message.
         * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()} method).
         */
        public ConnectionInterruptionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}