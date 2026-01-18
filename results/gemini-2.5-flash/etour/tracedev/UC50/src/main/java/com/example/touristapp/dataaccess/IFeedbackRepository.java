package com.example.touristapp.dataaccess;

import com.example.touristapp.domain.Feedback;

/**
 * Interface for data access operations related to {@link Feedback} entities.
 * Specifies the contract for retrieving feedback information from a data source.
 */
public interface IFeedbackRepository {
    /**
     * Finds feedback for a specific site given by a specific tourist.
     * Satisfies REQ-007: Exit Conditions: Connection to the server ETOUR IS interrupted.
     * // Traceability: Sequence Diagram: --> FeedbackRepo: Feedback feedbackEntity (or null if no feedback)
     * // Traceability: Sequence Diagram: --x FeedbackRepo: throws NetworkConnectionException
     *
     * @param siteId The unique identifier of the site.
     * @param touristId The unique identifier of the tourist.
     * @return A {@link Feedback} object if found, or null if no feedback exists for the given site and tourist.
     * @throws NetworkConnectionException If a network connection issue occurs during data retrieval.
     */
    Feedback findFeedbackBySiteAndTourist(String siteId, String touristId) throws NetworkConnectionException;
}