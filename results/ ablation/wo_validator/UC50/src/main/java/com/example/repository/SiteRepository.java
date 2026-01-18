package com.example.repository;

import com.example.model.Site;
import java.util.List;

/**
 * Repository interface for site data access.
 */
public interface SiteRepository {
    /**
     * Finds sites with feedback for a given tourist.
     * @param touristId the tourist's ID
     * @return list of Site objects with feedback
     */
    List<Site> findSitesWithFeedback(int touristId);
}