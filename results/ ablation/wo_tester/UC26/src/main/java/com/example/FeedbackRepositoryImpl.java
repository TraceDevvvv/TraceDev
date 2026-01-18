package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of FeedbackRepository.
 * Simulates database operations.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    private Database database;
    // Simulated in-memory storage for demonstration
    private List<Feedback> feedbackStore = new ArrayList<>();

    public FeedbackRepositoryImpl(Database database) {
        this.database = database;
        // Pre-populate with some sample feedbacks
        feedbackStore.add(new Feedback("f1", "s1", "Initial comment", "open"));
        feedbackStore.add(new Feedback("f2", "s1", "Another feedback", "closed"));
        feedbackStore.add(new Feedback("f3", "s2", "Site 2 feedback", "open"));
    }

    @Override
    public Feedback findById(String id) {
        // Simulate database query
        database.query("SELECT * FROM feedback WHERE id = " + id);
        return feedbackStore.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(Feedback feedback) {
        // Simulate database update
        database.update("UPDATE feedback SET comment = '" + feedback.getComment() + "' WHERE id = " + feedback.getId());
        for (int i = 0; i < feedbackStore.size(); i++) {
            if (feedbackStore.get(i).getId().equals(feedback.getId())) {
                feedbackStore.set(i, feedback);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Feedback> findAllBySiteId(String siteId) {
        // Simulate database query
        database.query("SELECT * FROM feedback WHERE siteId = " + siteId);
        return feedbackStore.stream()
                .filter(f -> f.getSiteId().equals(siteId))
                .toList();
    }
}