package com.duplicatefeedback.adapter.persistence;

import com.duplicatefeedback.application.ports.out.FeedbackRepository;
import com.duplicatefeedback.domain.model.Feedback;
import java.util.*;

/**
 * Concrete implementation of FeedbackRepository.
 * Simulates a data source (e.g., database) using an in-memory map.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    // In-memory storage for simulation; in real app would use DataSource
    private final Map<String, Feedback> storage = new HashMap<>();

    @Override
    public void save(Feedback feedback) {
        storage.put(feedback.getId(), feedback);
    }

    @Override
    public Optional<Feedback> findBySiteIdAndUserId(String siteId, String userId) {
        return storage.values().stream()
                .filter(f -> f.getSiteId().equals(siteId) && f.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public boolean existsBySiteIdAndUserId(String siteId, String userId) {
        return storage.values().stream()
                .anyMatch(f -> f.getSiteId().equals(siteId) && f.getUserId().equals(userId));
    }
}