package com.example.repository;

import com.example.exception.ConnectionException;
import com.example.model.Feedback;
import java.util.List;

/**
 * Interface for feedback data access operations.
 */
public interface FeedbackRepository {
    /**
     * Finds all feedback entities for a given site ID.
     * @param siteId the site identifier
     * @return list of Feedback entities
     * @throws ConnectionException if connection to the database is interrupted
     */
    List<Feedback> findBySiteId(String siteId) throws ConnectionException;
}