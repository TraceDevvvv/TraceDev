package com.example.controller;

import com.example.model.Site;
import com.example.model.Feedback;
import com.example.repository.SiteRepository;
import com.example.repository.FeedbackRepository;
import com.example.service.AuthenticationService;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controller for viewing and managing feedback.
 * Marked as responsive with response time < 2s (requirement R10).
 */
public class ViewFeedbackController {
    private SiteRepository siteRepository;
    private FeedbackRepository feedbackRepository;
    private AuthenticationService authService;

    // Connection interruption simulation
    private boolean connectionInterrupted = false;
    private static final int MAX_RETRIES = 3;
    private int retryCount = 0;

    public ViewFeedbackController(SiteRepository siteRepository, FeedbackRepository feedbackRepository,
                                  AuthenticationService authService) {
        this.siteRepository = siteRepository;
        this.feedbackRepository = feedbackRepository;
        this.authService = authService;
    }

    /**
     * Searches sites based on criteria (implements requirement R5/R6).
     * Returns list of sites matching the criteria.
     */
    public List<Site> searchSites(String criteria) {
        System.out.println("Searching sites with criteria: " + criteria);
        // Simulating database query - sequence diagram messages m3, m4, m5
        List<Site> siteList = siteRepository.searchSites(criteria);
        System.out.println("Returning site list to AO: " + siteList.size() + " sites");
        return siteList;
    }

    /**
     * Selects a site by its ID (implements requirement R5/R6).
     * Returns the selected site or null if not found.
     */
    public Site selectSite(String siteId) {
        System.out.println("Selecting site with ID: " + siteId);
        // Simulating database query - sequence diagram messages m10, m11
        Site site = siteRepository.getSiteById(siteId);
        return site;
    }

    /**
     * Views feedback for a given site ID (implements requirement R6).
     * Returns list of feedback or empty list if site not found.
     */
    public List<Feedback> viewFeedback(String siteId) {
        System.out.println("Viewing feedback for site ID: " + siteId);
        Site site = siteRepository.getSiteById(siteId);
        if (site == null) {
            System.out.println("Error: Site not found for ID " + siteId);
            // Sequence diagram message m18: show error "Site not found"
            return List.of(); // Return empty list as per sequence diagram's error handling.
        }
        // Simulating database query - sequence diagram messages m14, m15, m16
        List<Feedback> feedbackList = feedbackRepository.findBySiteId(siteId);
        System.out.println("Returning feedback list to AO: " + feedbackList.size() + " feedback items");
        return feedbackList;
    }

    /**
     * Handles request to view feedback - called by AO (sequence diagram message)
     * This method corresponds to sequence diagram message from AO to Controller
     */
    public List<Feedback> handleViewFeedback(String siteId) {
        System.out.println("AO requests to view feedback for site: " + siteId);
        // Check authentication (sequence diagram precondition R4)
        boolean loggedIn = authService.isLoggedIn("currentUser");
        if (!loggedIn) {
            System.out.println("Error: Not logged in - precondition failed (R4)");
            // Sequence diagram messages m33, m34
            return List.of();
        }
        return viewFeedback(siteId);
    }

    /**
     * Creates a feedback object (implements requirement R7).
     */
    public Feedback createFeedbackObject(String siteId, String content) {
        System.out.println("Creating feedback object for site ID: " + siteId);
        String feedbackId = UUID.randomUUID().toString();
        Date timestamp = new Date();
        return new Feedback(feedbackId, siteId, content, timestamp);
    }

    /**
     * Uploads feedback for a site (implements requirement R7).
     * Returns true if upload succeeds, false otherwise.
     */
    public boolean uploadFeedback(String siteId, String content) {
        System.out.println("Uploading feedback for site ID: " + siteId);
        // Check authentication (sequence diagram precondition R4)
        boolean loggedIn = authService.isLoggedIn("currentUser");
        if (!loggedIn) {
            System.out.println("Error: Not logged in - precondition failed (R4)");
            // Sequence diagram messages m33, m34
            return false;
        }
        
        // Validate content (simplified validation)
        if (content == null || content.trim().isEmpty()) {
            System.out.println("Error: Invalid content");
            return false;
        }
        
        // Check if site exists
        Site site = siteRepository.getSiteById(siteId);
        if (site == null) {
            System.out.println("Error: Site not found");
            return false;
        }
        
        Feedback feedback = createFeedbackObject(siteId, content);
        try {
            // Sequence diagram message m22: store feedback
            feedbackRepository.save(feedback);
            System.out.println("Upload success - returning success to AO");
            return true;
        } catch (Exception e) {
            System.out.println("Error saving feedback: " + e.getMessage());
            return false;
        }
    }

    /**
     * Notifies about connection loss (implements requirement R9).
     */
    public void connectionLost() {
        System.out.println("Connection lost notification sent.");
        connectionInterrupted = true;
    }

    /**
     * Handles connection interruption (implements requirement R9).
     */
    public void handleConnectionInterruption() {
        // Added for performance requirement R10 (fast)
        System.out.println("Handling connection interruption (fast)...");
        if (connectionInterrupted) {
            System.out.println("Connection is interrupted, attempting recovery.");
            // Simulate auto-retry logic
            if (retryCount < MAX_RETRIES) {
                System.out.println("Auto-retry attempt " + (retryCount + 1));
                retryOperation();
            } else {
                System.out.println("Max retries reached, suggest manual retry.");
                // Sequence diagram message m32: suggest manual retry
                // This is handled by returning from the method
            }
        }
    }

    /**
     * Retries the last operation (implements requirement R9).
     * Returns true if retry succeeds, false otherwise.
     */
    public boolean retryOperation() {
        // Simulate a fast retry (response time < 2s per requirement R10).
        System.out.println("Retrying operation (fast)...");
        try {
            Thread.sleep(500); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        retryCount++;
        boolean success = !connectionInterrupted; // Assume success if connection is restored.
        if (success) {
            System.out.println("Retry succeeded.");
            connectionInterrupted = false;
            retryCount = 0;
        } else {
            System.out.println("Retry failed.");
        }
        return success;
    }

    // Getters for testing
    public boolean isConnectionInterrupted() {
        return connectionInterrupted;
    }

    public int getRetryCount() {
        return retryCount;
    }
}