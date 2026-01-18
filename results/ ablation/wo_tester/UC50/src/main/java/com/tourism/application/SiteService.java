package com.tourism.application;

import com.tourism.domain.Feedback;
import com.tourism.domain.Site;
import com.tourism.infrastructure.ISiteRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Application service that coordinates site‑related operations.
 */
public class SiteService {
    private ISiteRepository siteRepository;

    public SiteService(ISiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    /**
     * Retrieves sites for which the given tourist has left feedback.
     * Performs verification that feedback exists for each site (REQ-007).
     * @param touristId the tourist's ID
     * @return list of Site entities with confirmed feedback
     */
    public List<Site> getSitesWithFeedback(String touristId) {
        List<Site> sites = siteRepository.findSitesByTouristFeedback(touristId);
        List<Site> result = new ArrayList<>();

        for (Site site : sites) {
            // Verify feedback exists for this site and tourist (REQ-007)
            if (retrieveFeedback(site.getSiteId(), touristId) != null) {
                result.add(site);
            }
        }
        return result;
    }

    /**
     * Simulates retrieving a Feedback entity for a given site and tourist.
     * In a real system, this would query a Feedback repository.
     * @param siteId the site ID
     * @param touristId the tourist ID
     * @return a Feedback object if found, null otherwise
     */
    protected Feedback retrieveFeedback(String siteId, String touristId) {
        // Simulated feedback retrieval
        // In reality, this would be a database query.
        return new Feedback("FB_" + siteId + "_" + touristId, touristId, siteId, "Great visit!", 5);
    }

    /**
     * Method to represent the message "empty list" from the sequence diagram.
     * Returns an empty list of Site entities.
     */
    public List<Site> emptyList() {
        return new ArrayList<>();
    }
}