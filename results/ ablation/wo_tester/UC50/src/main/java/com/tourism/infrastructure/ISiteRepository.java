package com.tourism.infrastructure;

import com.tourism.domain.Site;
import java.util.List;

/**
 * Repository interface for Site data access.
 */
public interface ISiteRepository {
    /**
     * Finds sites for which the given tourist has left feedback.
     * @param touristId the tourist's ID
     * @return list of Site entities
     */
    List<Site> findSitesByTouristFeedback(String touristId);
}