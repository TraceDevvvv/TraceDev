package com.example.agency.service;

import com.example.agency.model.SiteFeedback;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Service to retrieve site feedback for a location.
 * Simulates the SearchSite use case (Step 7).
 * Handles connection interruptions.
 */
public class SiteFeedbackService {
    private Random random = new Random();

    /**
     * Retrieves site feedback for a given location ID.
     * Simulates fetching from a midsize site feedback database.
     *
     * @param locationId The location ID.
     * @return List of SiteFeedback objects.
     */
    public List<SiteFeedback> getSiteFeedbackForLocation(String locationId) {
        // Simulate connection interruption handling (Quality Requirement).
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Connection interrupted while fetching site feedback.");
            return new ArrayList<>();
        }

        List<SiteFeedback> feedbacks = new ArrayList<>();
        // Generate a variable number of feedback entries (5 to 15) for realism.
        int count = 5 + random.nextInt(11);
        for (int i = 1; i <= count; i++) {
            int rating = 1 + random.nextInt(5); // 1-5
            String comment = generateComment(rating);
            String timestamp = "2025-01-" + String.format("%02d", i) + " 10:" + String.format("%02d", i * 3);
            feedbacks.add(new SiteFeedback("FB" + locationId + "-" + i, locationId, rating, comment, timestamp));
        }
        return feedbacks;
    }

    private String generateComment(int rating) {
        switch (rating) {
            case 5: return "Excellent experience!";
            case 4: return "Very good, would recommend.";
            case 3: return "Average, nothing special.";
            case 2: return "Below expectations.";
            case 1: return "Poor, needs improvement.";
            default: return "No comment.";
        }
    }
}