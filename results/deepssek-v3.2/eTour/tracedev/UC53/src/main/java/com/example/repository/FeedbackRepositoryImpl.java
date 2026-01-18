package com.example.repository;

import com.example.model.Feedback;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IFeedbackRepository.
 * Simulating a database with an in-memory list.
 */
public class FeedbackRepositoryImpl implements IFeedbackRepository {
    private List<Feedback> feedbackStore = new ArrayList<>();

    @Override
    public void save(Feedback feedback) {
        feedbackStore.add(feedback);
        // Simulate database insert
        System.out.println("Feedback saved to database: " + feedback.getFeedbackId());
    }

    @Override
    public List<Feedback> findByTouristAndSite(String touristId, String siteId) {
        return feedbackStore.stream()
                .filter(f -> f.getTouristId().equals(touristId) && f.getSiteId().equals(siteId))
                .collect(Collectors.toList());
    }
}