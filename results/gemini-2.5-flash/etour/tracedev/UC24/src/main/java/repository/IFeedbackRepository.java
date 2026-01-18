package com.example.repository;

import com.example.model.Feedback;
import com.example.service.NetworkConnectionException;

import java.util.List;

/**
 * Interface for operations related to Feedback data persistence.
 */
public interface IFeedbackRepository {
    /**
     * Retrieves a list of Feedback objects for a given site ID.
     * @param siteId The ID of the site for which to retrieve feedback.
     * @return A list of Feedback objects associated with the site.
     * @throws NetworkConnectionException if there's an issue connecting to the data source.
     */
    List<Feedback> getFeedbackBySiteId(String siteId) throws NetworkConnectionException;
}