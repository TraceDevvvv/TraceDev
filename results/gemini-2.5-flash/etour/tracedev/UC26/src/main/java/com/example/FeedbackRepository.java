package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory implementation of IFeedbackRepository.
 * This class simulates database interactions using a HashMap.
 * It includes a flag for simulating database connection errors (REQ-002).
 */
public class FeedbackRepository implements IFeedbackRepository {
    private final Map<String, Feedback> feedbackStore = new HashMap<>();
    private boolean simulateDbError = false; // Flag to simulate REQ-002 network resilience issues

    /**
     * Constructor for FeedbackRepository. Initializes with some dummy data.
     */
    public FeedbackRepository() {
        feedbackStore.put("f1", new Feedback("f1", "s1", "Initial comment for site 1.", "PENDING"));
        feedbackStore.put("f2", new Feedback("f2", "s1", "Another comment for site 1.", "RESOLVED"));
        feedbackStore.put("f3", new Feedback("f3", "s2", "Comment for site 2.", "PENDING"));
    }

    /**
     * Sets the flag to simulate database errors.
     * @param simulateDbError If true, repository methods will simulate failure.
     */
    public void setSimulateDbError(boolean simulateDbError) {
        this.simulateDbError = simulateDbError;
    }

    @Override
    public Feedback findById(String feedbackId) {
        if (simulateDbError) {
            System.err.println("SIMULATED DB ERROR: Failed to retrieve feedback by ID due to connection issues.");
            return null; // Simulate connection error for REQ-002
        }
        return feedbackStore.get(feedbackId);
    }

    @Override
    public boolean save(Feedback feedback) {
        if (simulateDbError) {
            System.err.println("SIMULATED DB ERROR: Failed to save feedback due to connection issues.");
            return false; // Simulate connection error for REQ-002
        }
        if (feedback != null && feedback.getId() != null) {
            feedbackStore.put(feedback.getId(), feedback);
            return true;
        }
        return false;
    }

    @Override
    public List<Feedback> findBySiteId(String siteId) {
        if (simulateDbError) {
            System.err.println("SIMULATED DB ERROR: Failed to retrieve feedback by site ID due to connection issues.");
            return new ArrayList<>(); // Simulate connection error for REQ-002
        }
        return feedbackStore.values().stream()
                .filter(feedback -> feedback.getSiteId().equals(siteId))
                .collect(Collectors.toList());
    }
}