package com.example.business;

import com.example.data.FeedbackRepository;
import com.example.domain.Feedback;
import com.example.presentation.AgencyOperator;
import java.util.List;

/**
 * Service for feedback‑related operations (Business Logic Layer).
 * Acts as a control class in the Class Diagram.
 */
public class FeedbackService {
    private FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Retrieves all feedback for a given site, after validating operator access.
     * Implements the main flow from the Sequence Diagram.
     */
    public List<Feedback> getFeedbackForSite(int siteId, AgencyOperator operator) throws AccessDeniedException, ServiceUnavailableException {
        // Validate operator access as per sequence diagram.
        if (!validateSiteAccess(siteId, operator)) {
            throw new AccessDeniedException("Operator does not have access to site " + siteId);
        }

        try {
            return feedbackRepository.findBySiteId(siteId);
        } catch (com.example.data.ConnectionException e) {
            // Translate data‑layer exception to business‑layer exception (server interruption).
            throw new ServiceUnavailableException("Server connection lost", e);
        }
    }

    /**
     * Validates whether the given operator can access the specified site.
     * In a real system, this would involve complex authorization logic.
     */
    public boolean validateSiteAccess(int siteId, AgencyOperator operator) {
        // Simplified: assume operator is authenticated and has access to all sites.
        return operator.isAuthenticated();
    }
}