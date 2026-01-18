
package com.example.repository;

import com.example.model.Feedback;
import com.example.model.Site;
import com.example.model.FeedbackStatus;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory implementation of FeedbackRepository.
 * Simulates EntityManager with a map.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    // Simulating EntityManager with a map
    private Map<Integer, Feedback> feedbackMap = new HashMap<>();
    private static int nextId = 1;

    public FeedbackRepositoryImpl() {
        // Initialize with some dummy data
        feedbackMap.put(1, new Feedback(1, "Initial comment", FeedbackStatus.PENDING));
        feedbackMap.put(2, new Feedback(2, "Another feedback", FeedbackStatus.REVIEWED));
    }

    @Override
    public Feedback findById(int id) {
        System.out.println("FeedbackRepositoryImpl: finding feedback by id " + id);
        return feedbackMap.get(id);
    }

    @Override
    public void save(Feedback feedback) {
        if (feedback.getFeedbackId() <= 0) {
            feedback.setFeedbackId(nextId++);
        }
        feedbackMap.put(feedback.getFeedbackId(), feedback);
        System.out.println("FeedbackRepositoryImpl: saved feedback " + feedback.getFeedbackId());
    }

    @Override
    public List<Feedback> findBySite(Site site) {
        System.out.println("FeedbackRepositoryImpl: finding feedbacks for site " + site.getSiteId());
        // For simplicity, we return all feedbacks; in reality, filter by site relation
        return new ArrayList<>(feedbackMap.values())
                .stream()
                .limit(2) // Just simulating a subset for demo
                .collect(Collectors.toList());
    }
}
