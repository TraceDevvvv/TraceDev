package com.etoour.repository;

import com.etoour.model.Feedback;
import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of FeedbackRepository.
 * Uses an in‑memory map for simplicity.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    private Map<String, Feedback> storage = new HashMap<>();

    /**
     * Simulate connection errors randomly.
     */
    private boolean simulateConnectionError() {
        // 10% chance of error for demonstration
        return Math.random() < 0.1;
    }

    @Override
    public Feedback findById(String feedbackId) {
        if (simulateConnectionError()) {
            throw new RuntimeException("Connection error while finding feedback.");
        }
        return storage.get(feedbackId);
    }

    @Override
    public Feedback save(Feedback feedback) {
        if (simulateConnectionError()) {
            throw new RuntimeException("Connection error while saving feedback.");
        }
        storage.put(feedback.getFeedbackId(), feedback);
        return feedback;
    }

    @Override
    public boolean existsById(String feedbackId) {
        return storage.containsKey(feedbackId);
    }

    // Helper to pre‑populate for testing
    public void addFeedback(Feedback feedback) {
        storage.put(feedback.getFeedbackId(), feedback);
    }

    /**
     * Sequence diagram message: Feedback object (m9).
     * This is the return of findById, but we can keep as is.
     */
    public Feedback getFeedbackObject(String feedbackId) {
        return findById(feedbackId);
    }

    /**
     * Sequence diagram message: updated Feedback (m18).
     * This is the return of save, but we can keep as is.
     */
    public Feedback getUpdatedFeedback(Feedback feedback) {
        return save(feedback);
    }
}