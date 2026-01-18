package com.example.report;

import java.util.List;

/**
 * ISiteFeedbackRepository interface
 * Defines the contract for accessing SiteFeedback data.
 */
public interface ISiteFeedbackRepository {
    /**
     * Finds all site feedback entries associated with a specific location ID.
     * @param locationId The ID of the location.
     * @return A list of SiteFeedback objects for the given location.
     */
    List<SiteFeedback> findByLocationId(String locationId);
}