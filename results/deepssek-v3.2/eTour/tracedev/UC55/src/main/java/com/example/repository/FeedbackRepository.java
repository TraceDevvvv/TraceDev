package com.example.repository;

import com.example.model.Feedback;
import java.util.Optional;

/**
 * Interface for feedback repository operations.
 */
public interface FeedbackRepository {
    /**
     * Finds feedback by site and user.
     * @param siteId the site identifier.
     * @param userId the user identifier.
     * @return an Optional containing the feedback if found.
     */
    Optional<Feedback> findBySiteAndUser(String siteId, String userId);

    /**
     * Finds the latest feedback by user (assumed for state recovery).
     * Added for simplicity in confirmNotificationRead.
     * @param userId the user identifier.
     * @return an Optional containing the latest feedback.
     */
    default Optional<Feedback> findLatestByUser(String userId) {
        // Default implementation returns empty; real implementation would query.
        return Optional.empty();
    }

    /**
     * Query existing feedback.
     * Corresponds to sequence diagram message m5.
     */
    default Optional<Feedback> queryExistingFeedback(String siteId, String userId) {
        return findBySiteAndUser(siteId, userId);
    }
}