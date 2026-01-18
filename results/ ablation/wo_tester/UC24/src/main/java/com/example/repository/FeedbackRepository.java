package com.example.repository;

import com.example.model.Feedback;
import com.example.exception.DatabaseException;
import java.util.List;

/**
 * Repository interface for Feedback entities.
 */
public interface FeedbackRepository {
    /**
     * Retrieves feedback for a given site ID.
     * @param siteId the site ID
     * @return List of Feedback objects
     * @throws DatabaseException if a database error occurs (e.g., connection interruption)
     */
    List<Feedback> findFeedbackBySiteId(int siteId) throws DatabaseException;

    /**
     * Checks the connection status to the database.
     * @return true if connected, false otherwise
     */
    boolean isConnected();
}