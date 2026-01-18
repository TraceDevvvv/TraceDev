package com.example.feedbackapp.repository;

import com.example.feedbackapp.model.Feedback;

/**
 * Repository interface for managing Feedback entities.
 * Defines operations for data access related to Feedback.
 */
public interface IFeedbackRepository {
    /**
     * Finds a Feedback entry by its associated site ID.
     *
     * @param siteId The ID of the site for which to find feedback.
     * @return The Feedback object if found, otherwise null.
     */
    Feedback findBySiteId(String siteId);
}