package com.example.view;

import com.example.controller.ViewFeedbackController;
import com.example.model.Site;
import com.example.model.Feedback;
import com.example.exception.DatabaseException;
import java.util.List;

/**
 * UI class for the Agency Operator.
 * Displays sites and feedback, handles user interactions.
 */
public class AgencyOperatorUI {
    private ViewFeedbackController controller;
    private int selectedSiteId;

    public AgencyOperatorUI(ViewFeedbackController controller) {
        this.controller = controller;
    }

    /**
     * Renders the list of sites to the operator.
     * @param sites list of Site objects
     */
    public void renderSites(List<Site> sites) {
        System.out.println("=== List of Sites ===");
        for (Site site : sites) {
            System.out.println("ID: " + site.getSiteId() + ", Name: " + site.getName() + ", Location: " + site.getLocation());
        }
        System.out.println("=====================");
    }

    /**
     * Displays feedback for a site.
     * @param feedbackList list of Feedback objects
     */
    public void displayFeedback(List<Feedback> feedbackList) {
        if (feedbackList.isEmpty()) {
            System.out.println("No feedback found for this site.");
        } else {
            System.out.println("=== Feedback for Site ID " + selectedSiteId + " ===");
            for (Feedback feedback : feedbackList) {
                System.out.println("Comment: " + feedback.getComment() + ", Rating: " + feedback.getRating() + ", Date: " + feedback.getDateSubmitted());
            }
            System.out.println("==================================");
        }
    }

    /**
     * Stores the selected site ID.
     * @param siteId the site ID selected by the operator
     */
    public void selectSite(int siteId) {
        this.selectedSiteId = siteId;
        System.out.println("Site with ID " + siteId + " selected.");
    }

    /**
     * Requests feedback for the currently selected site.
     * Handles connection errors as per sequence diagram.
     */
    public void requestFeedbackForSite() {
        try {
            List<Feedback> feedbackList = controller.getFeedbackForSite(selectedSiteId);
            displayFeedback(feedbackList);
        } catch (DatabaseException e) {
            // Handle connection failure exit condition
            String errorMessage = controller.handleConnectionError(e);
            displayError(errorMessage);
        }
    }

    /**
     * Displays an error message to the operator.
     * @param errorMessage the error message
     */
    public void displayError(String errorMessage) {
        System.err.println("ERROR: " + errorMessage);
    }

    /**
     * Validates session by delegating to controller.
     */
    public void validateSession() {
        boolean valid = controller.validateSession();
        if (valid) {
            System.out.println("Session validated successfully.");
        } else {
            System.err.println("Session invalid. Please log in again.");
        }
    }

    // New method to store selectedSiteId (for m11)
    public void storeSelectedSiteId(int siteId) {
        this.selectedSiteId = siteId;
    }

    // New method to display error: "Unable to load feedback" (for m27)
    public void displayErrorUnableToLoadFeedback() {
        displayError("Unable to load feedback");
    }

    // Main method to simulate the sequence diagram flow
    public static void main(String[] args) {
        // Setup repositories and controller
        com.example.repository.SiteRepositoryImpl siteRepo = new com.example.repository.SiteRepositoryImpl();
        com.example.repository.FeedbackRepositoryImpl feedbackRepo = new com.example.repository.FeedbackRepositoryImpl();
        ViewFeedbackController controller = new ViewFeedbackController(siteRepo, feedbackRepo);
        AgencyOperatorUI ui = new AgencyOperatorUI(controller);

        // Simulate entry condition: Agency Operator is logged in (session validation)
        ui.validateSession();

        // Operator views list of sites (from SearchSite UC result)
        List<Site> sites = controller.getSitesForOperator();
        ui.renderSites(sites);

        // Operator selects a site (e.g., site ID 1)
        ui.selectSite(1);

        // Operator requests feedback for the selected site
        ui.requestFeedbackForSite();

        // Simulate connection failure scenario (optional)
        System.out.println("\n--- Simulating connection failure ---");
        feedbackRepo.setConnected(false);
        ui.requestFeedbackForSite();
    }
}