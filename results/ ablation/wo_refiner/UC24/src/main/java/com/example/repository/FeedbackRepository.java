package com.example.repository;

import com.example.model.Feedback;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Repository for feedback data access.
 * Marked as reliable with >99.9% reliability (requirement R10).
 */
public class FeedbackRepository {
    // In-memory simulation of a database
    private List<Feedback> feedbackDatabase;

    public FeedbackRepository() {
        feedbackDatabase = new ArrayList<>();
        // Initialize with some sample data for demonstration
        feedbackDatabase.add(new Feedback("F1", "S1", "Great maintenance", new Date(System.currentTimeMillis() - 86400000)));
        feedbackDatabase.add(new Feedback("F2", "S1", "Needs more benches", new Date(System.currentTimeMillis() - 172800000)));
        feedbackDatabase.add(new Feedback("F3", "S2", "Informative exhibits", new Date()));
    }

    /**
     * Finds all feedback for a given site ID.
     * Sequence diagram message m14: query feedback data
     */
    public List<Feedback> findBySiteId(String siteId) {
        // Simulating database query - sequence diagram message m14
        System.out.println("Querying feedback data for site ID: " + siteId);
        List<Feedback> result = feedbackDatabase.stream()
                .filter(feedback -> feedback.getSiteId().equals(siteId))
                .collect(Collectors.toList());
        // Sequence diagram message m15: feedback list (return from DB)
        System.out.println("Returning feedback list from DB: " + result.size() + " feedback items");
        return result;
    }

    /**
     * Saves a new feedback entry.
     * Sequence diagram message m22: store feedback
     */
    public void save(Feedback feedback) {
        // Simulating database storage - sequence diagram message m22
        System.out.println("Storing feedback in DB: " + feedback);
        feedbackDatabase.add(feedback);
    }
}