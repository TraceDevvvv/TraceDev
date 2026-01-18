package com.example.presentation;

import com.example.business.FeedbackService;
import com.example.business.SiteService;
import com.example.business.AccessDeniedException;
import com.example.business.ServiceUnavailableException;
import com.example.domain.Site;
import com.example.domain.Feedback;
import java.util.List;

/**
 * Boundary class that handles interactions for viewing feedback.
 * Part of the Presentation Layer in the Class Diagram.
 */
public class FeedbackViewController {
    private Site selectedSite;
    private SiteService siteService;
    private FeedbackService feedbackService;
    private AgencyOperator currentOperator;

    // The sequence diagram assumes the controller knows the operator.
    public FeedbackViewController(SiteService siteService, FeedbackService feedbackService, AgencyOperator operator) {
        this.siteService = siteService;
        this.feedbackService = feedbackService;
        this.currentOperator = operator;
    }

    /**
     * Entry point called by the operator to activate the site view.
     * Corresponds to the first step in the Sequence Diagram.
     */
    public void activateSiteView() {
        // Step 1: Retrieve and display all sites.
        List<Site> sites = siteService.getAllSites();
        displaySites(sites);
    }

    /**
     * Displays the list of sites (output to console for simplicity).
     */
    public void displaySites(List<Site> sites) {
        System.out.println("=== Available Sites ===");
        for (Site site : sites) {
            System.out.println(site.getSiteId() + ": " + site.getName() + " - " + site.getAddress());
        }
        System.out.println("=======================");
    }

    /**
     * Handles site selection by the operator (Step 2 in Sequence Diagram).
     * Stores the selected site and triggers feedback retrieval.
     */
    public void handleSiteSelection(int siteId) {
        storeSelectedSite(siteId);
        requestFeedbackForSite(siteId);
    }

    // Internal method to store the selected site.
    private void storeSelectedSite(int siteId) {
        selectedSite = siteService.getSiteById(siteId);
        if (selectedSite != null) {
            System.out.println("Selected site: " + selectedSite.getName());
        } else {
            System.out.println("Site with ID " + siteId + " not found.");
        }
    }

    /**
     * Requests feedback for the chosen site (Step 3 in Sequence Diagram).
     * Delegates to FeedbackService and handles success/error flows.
     */
    public void requestFeedbackForSite(int siteId) {
        try {
            List<Feedback> feedbacks = feedbackService.getFeedbackForSite(siteId, currentOperator);
            displayFeedback(feedbacks);
        } catch (AccessDeniedException e) {
            showAccessDeniedMessage();
        } catch (ServiceUnavailableException e) {
            showErrorMessage("Server connection lost");
        }
    }

    /**
     * Displays the list of feedback (success flow).
     */
    public void displayFeedback(List<Feedback> feedbacks) {
        if (feedbacks.isEmpty()) {
            System.out.println("No feedback for this site.");
            return;
        }
        System.out.println("=== Feedback for Site ===");
        for (Feedback fb : feedbacks) {
            System.out.println("Rating: " + fb.getRating() + " | Comment: " + fb.getComment() +
                    " | Date: " + fb.getDateSubmitted());
        }
        System.out.println("==========================");
    }

    public void showAccessDeniedMessage() {
        System.out.println("ERROR: You do not have permission to view feedback for this site.");
    }

    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    // Getters for testing or further use.
    public Site getSelectedSite() {
        return selectedSite;
    }
}