package com.example.touristapp.dataaccess;

import com.example.touristapp.domain.Feedback;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Concrete implementation of {@link IFeedbackRepository}.
 * This class simulates data access to a database for {@link Feedback} entities.
 * It includes a mechanism to simulate {@link NetworkConnectionException} for testing error handling.
 */
public class FeedbackRepository implements IFeedbackRepository {

    // --- Mock Database Simulation ---
    // In a real application, this would interact with a database (e.g., via JDBC, JPA, etc.)
    private static final List<Feedback> MOCK_FEEDBACKS = Arrays.asList(
            new Feedback("F001", "S001", "T001", "Amazing architecture, a must-see!", 5),
            new Feedback("F002", "S002", "T001", "Crowded but worth it for the art.", 4),
            new Feedback("F003", "S003", "T001", "Historically rich, a bit run down.", 3),
            new Feedback("F004", "S004", "T002", "Beautiful fountain, very busy.", 4),
            new Feedback("F005", "S005", "T002", "Still under construction but impressive.", 4),
            new Feedback("F006", "S001", "T003", "A bit cliché, but iconic.", 4)
    );

    // --- Network Error Simulation ---
    private boolean simulateNetworkError = false;
    private Random random = new Random();

    /**
     * Sets whether to simulate a network error.
     * @param simulateNetworkError If true, find methods will randomly throw NetworkConnectionException.
     */
    public void setSimulateNetworkError(boolean simulateNetworkError) {
        this.simulateNetworkError = simulateNetworkError;
    }

    /**
     * Finds feedback for a specific site given by a specific tourist.
     * This implementation simulates database interaction and can throw {@link NetworkConnectionException}.
     * Satisfies REQ-007: Exit Conditions: Connection to the server ETOUR IS interrupted.
     *
     * @param siteId The unique identifier of the site.
     * @param touristId The unique identifier of the tourist.
     * @return A {@link Feedback} object if found, or null if no feedback exists for the given site and tourist.
     * @throws NetworkConnectionException If a simulated network connection issue occurs.
     */
    @Override
    public Feedback findFeedbackBySiteAndTourist(String siteId, String touristId) throws NetworkConnectionException {
        // Simulate network latency or interruption
        try {
            Thread.sleep(50); // Simulate some processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate network error with a 30% chance if enabled
        if (simulateNetworkError && random.nextInt(10) < 3) {
            System.out.println("DEBUG: FeedbackRepository simulating NetworkConnectionException for site " + siteId + " and tourist " + touristId);
            throw new NetworkConnectionException("Failed to connect to feedback database while finding feedback for site " + siteId + " and tourist " + touristId);
        }

        // Simulate database query("SELECT * FROM Feedback WHERE siteId = :siteId AND touristId = :touristId")
        System.out.println("DEBUG: FeedbackRepository querying database for feedback on site " + siteId + " by tourist " + touristId);
        return MOCK_FEEDBACKS.stream()
                .filter(feedback -> feedback.getSiteId().equals(siteId) && feedback.getTouristId().equals(touristId))
                .findFirst()
                .orElse(null); // Return null if no feedback is found, as per sequence diagram note
    }
}