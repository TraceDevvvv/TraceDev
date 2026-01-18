package com.example;

import java.util.List;
import java.util.ArrayList;

/**
 * Repository for accessing SiteFeedback data.
 * Simulates a database connection.
 */
public class SiteFeedbackRepository {
    /**
     * Retrieves feedback for a specific location.
     * @param locationId The ID of the location.
     * @return List of feedback for the location.
     * @throws ConnectionException if connection to server fails.
     */
    public List<SiteFeedback> findFeedbackByLocation(String locationId) throws ConnectionException {
        List<SiteFeedback> feedbackList = new ArrayList<>();
        feedbackList.add(new SiteFeedback(locationId, "Good traffic"));
        feedbackList.add(new SiteFeedback(locationId, "Needs improvement"));
        if (Math.random() < 0.1) {
            throw new ConnectionException("Connection lost while fetching feedback.");
        }
        return feedbackList;
    }
}