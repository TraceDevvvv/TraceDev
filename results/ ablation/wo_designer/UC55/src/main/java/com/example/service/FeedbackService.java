package com.example.service;

import com.example.model.Feedback;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Service class for managing feedback operations.
 */
public class FeedbackService {
    // Simulating a database with an in-memory map (key: userId+siteId, value: Feedback)
    private Map<String, Feedback> feedbackMap = new HashMap<>();

    /**
     * Attempts to insert a new feedback for a user and site.
     * If feedback already exists, the operation is cancelled and the previous state is recovered.
     *
     * @param userId    The ID of the user.
     * @param siteId    The ID of the site.
     * @param content   The content of the feedback.
     * @return true if feedback was inserted, false if feedback already exists.
     */
    public boolean insertFeedback(String userId, String siteId, String content) {
        String key = userId + ":" + siteId;
        if (feedbackMap.containsKey(key)) {
            // Feedback already exists, so operation is cancelled.
            // No new feedback is inserted, previous state remains unchanged.
            return false;
        } else {
            Feedback newFeedback = new Feedback(
                    generateFeedbackId(),
                    userId,
                    siteId,
                    content,
                    LocalDateTime.now()
            );
            feedbackMap.put(key, newFeedback);
            return true;
        }
    }

    /**
     * Checks if a user has already issued feedback for a given site.
     *
     * @param userId The user ID.
     * @param siteId The site ID.
     * @return true if feedback exists, false otherwise.
     */
    public boolean hasFeedback(String userId, String siteId) {
        String key = userId + ":" + siteId;
        return feedbackMap.containsKey(key);
    }

    /**
     * Gets the existing feedback for a user and site.
     *
     * @param userId The user ID.
     * @param siteId The site ID.
     * @return The existing Feedback object, or null if not found.
     */
    public Feedback getFeedback(String userId, String siteId) {
        String key = userId + ":" + siteId;
        return feedbackMap.get(key);
    }

    // Helper method to generate a unique feedback ID (simple simulation)
    private String generateFeedbackId() {
        return "FB_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 1000);
    }
}