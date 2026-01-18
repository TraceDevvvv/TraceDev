package com.example;

import java.util.List;

/**
 * Repository interface for Feedback entities.
 */
public interface FeedbackRepository {
    /**
     * Finds a feedback by its ID.
     * @param id feedback ID
     * @return Feedback entity or null if not found
     */
    Feedback findById(String id);

    /**
     * Updates an existing feedback.
     * @param feedback the feedback to update
     * @return true if update succeeded
     */
    boolean update(Feedback feedback);

    /**
     * Finds all feedbacks for a given site.
     * @param siteId the site ID
     * @return list of feedbacks
     */
    List<Feedback> findAllBySiteId(String siteId);
}