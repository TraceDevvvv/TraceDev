package com.example.controller;

import com.example.repository.SiteRepository;
import com.example.repository.FeedbackRepository;
import com.example.model.Site;
import com.example.model.Feedback;
import com.example.exception.DatabaseException;
import java.util.List;

/**
 * Controller for handling feedback viewing operations.
 * Handles connection interruption per UC exit condition.
 */
public class ViewFeedbackController {
    private SiteRepository siteRepository;
    private FeedbackRepository feedbackRepository;

    public ViewFeedbackController(SiteRepository siteRepo, FeedbackRepository feedbackRepo) {
        this.siteRepository = siteRepo;
        this.feedbackRepository = feedbackRepo;
    }

    /**
     * Retrieves all sites for the operator.
     * @return List of Site objects
     */
    public List<Site> getSitesForOperator() {
        try {
            return siteRepository.findAllSites();
        } catch (DatabaseException e) {
            // Log the exception and return empty list or rethrow - assuming empty list for simplicity
            System.err.println("Error fetching sites: " + e.getMessage());
            return List.of();
        }
    }

    /**
     * Retrieves feedback for a specific site.
     * @param siteId the site ID
     * @return List of Feedback objects
     */
    public List<Feedback> getFeedbackForSite(int siteId) throws DatabaseException {
        // Delegate to repository; any DatabaseException will be propagated
        return feedbackRepository.findFeedbackBySiteId(siteId);
    }

    /**
     * Handles connection errors and returns a user-friendly error message.
     * @param ex the exception that occurred
     * @return error message string
     */
    public String handleConnectionError(Exception ex) {
        // In a real application, we might log the exception and return a localized message.
        return "Unable to load feedback due to a database connection issue. Please try again later.";
    }

    /**
     * Validates the session (simplified for this example).
     * @return true if session is valid
     */
    public boolean validateSession() {
        // Simulate session validation
        return true;
    }

    // New method to return empty List<Feedback> (for m21)
    public List<Feedback> getEmptyFeedbackList() {
        return List.of();
    }

    // New method to return error message (for m26)
    public String getErrorMessage() {
        return "Unable to load feedback";
    }
}