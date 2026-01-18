package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of FeedbackRepository.
 * Stereotype: Repository
 */
public class InMemoryFeedbackRepository implements FeedbackRepository {
    private Map<String, List<Feedback>> feedbackStorage;

    public InMemoryFeedbackRepository() {
        feedbackStorage = new HashMap<>();
        // Initialize with some sample data for testing
        Feedback sampleFeedback = new Feedback("SITE001", "Great site!");
        List<Feedback> list = new ArrayList<>();
        list.add(sampleFeedback);
        feedbackStorage.put("SITE001", list);
    }

    @Override
    public List<Feedback> findBySiteId(String siteId) {
        // Return a copy of the list or empty list if not found
        return feedbackStorage.getOrDefault(siteId, new ArrayList<>());
    }
}