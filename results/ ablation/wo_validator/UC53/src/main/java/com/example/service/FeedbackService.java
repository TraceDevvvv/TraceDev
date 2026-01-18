package com.example.service;

import com.example.dto.Feedback;

import java.util.Optional;

/**
 * Service interface for feedback operations.
 */
public interface FeedbackService {
    /**
     * Saves feedback to the repository.
     * @param feedback The feedback entity to save.
     * @return true if successful, false otherwise.
     */
    boolean saveFeedback(Feedback feedback);

    /**
     * Finds existing feedback for a tourist and site.
     * @param touristId Tourist identifier.
     * @param siteId Site identifier.
     * @return Optional containing Feedback if found, empty otherwise.
     */
    Optional<Feedback> findExistingFeedback(String touristId, String siteId);
}