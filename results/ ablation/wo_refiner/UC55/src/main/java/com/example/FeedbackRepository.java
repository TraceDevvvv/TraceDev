package com.example;

import java.util.List;

/**
 * Repository interface for Feedback persistence operations.
 * Stereotype: Repository
 */
public interface FeedbackRepository {
    /**
     * Find all feedback entries for a given site ID.
     * @param siteId the site identifier
     * @return list of Feedback objects, possibly empty
     */
    List<Feedback> findBySiteId(String siteId);
}