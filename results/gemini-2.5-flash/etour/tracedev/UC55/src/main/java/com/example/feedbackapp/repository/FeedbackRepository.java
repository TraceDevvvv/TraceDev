package com.example.feedbackapp.repository;

import com.example.feedbackapp.model.Feedback;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IFeedbackRepository.
 * This class simulates a data store for Feedback objects using an in-memory map.
 */
public class FeedbackRepository implements IFeedbackRepository {

    // Simulating a database/data store with an in-memory map
    private final Map<String, Feedback> feedbackStore = new HashMap<>();

    /**
     * Constructor for FeedbackRepository.
     * Initializes the repository with some sample data for demonstration.
     */
    public FeedbackRepository() {
        // Adding some initial feedback for testing existing feedback scenarios
        feedbackStore.put("site123", new Feedback("f001", "site123", "Great site!"));
        feedbackStore.put("site456", new Feedback("f002", "site456", "Content was helpful."));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Feedback findBySiteId(String siteId) {
        System.out.println("[IFeedbackRepository] Querying for existing feedback for site: " + siteId);
        // Simulate a delay or complex query
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return feedbackStore.get(siteId);
    }

    /**
     * Simulates saving a new feedback object to the store.
     * This method is not in the original class diagram but is useful for testing `createNewFeedback`.
     *
     * @param feedback The Feedback object to save.
     */
    public void save(Feedback feedback) {
        feedbackStore.put(feedback.getSiteId(), feedback);
        System.out.println("[IFeedbackRepository] Saved new feedback for site: " + feedback.getSiteId());
    }
}