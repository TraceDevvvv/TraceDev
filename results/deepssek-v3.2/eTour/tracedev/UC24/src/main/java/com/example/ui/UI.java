package com.example.ui;

import com.example.dto.FeedbackDTO;
import com.example.dto.SiteDTO;
import java.util.List;
import com.example.controller.ViewFeedbackController;

/**
 * Boundary class representing the User Interface.
 * Interacts with the Agency Operator and calls the ViewFeedbackController.
 */
public class UI {
    private ViewFeedbackController viewFeedbackController;

    public UI(ViewFeedbackController viewFeedbackController) {
        this.viewFeedbackController = viewFeedbackController;
    }

    /**
     * Displays the list of sites (from SearchSite use case).
     * Step 1 of Flow of Events.
     */
    public void displaySiteList(List<SiteDTO> sites) {
        System.out.println("Displaying site list:");
        for (SiteDTO site : sites) {
            System.out.println("Site ID: " + site.getId() + ", Name: " + site.getName() + ", Location: " + site.getLocation());
        }
    }

    /**
     * Called when the Agency Operator selects a site.
     * Step 2 of Flow of Events.
     */
    public void selectSite(String siteId) {
        System.out.println("Site selected: " + siteId);
    }

    /**
     * Called when the Agency Operator activates the view feedback action.
     * Step 3 of Flow of Events.
     */
    public void activateViewFeedback() {
        System.out.println("View Feedback action activated.");
    }

    /**
     * Displays the list of feedback for a site.
     * Called by the controller with the feedback DTO list.
     */
    public void displayFeedback(List<FeedbackDTO> feedbackList) {
        System.out.println("Displaying feedback list:");
        for (FeedbackDTO feedback : feedbackList) {
            System.out.println("Site: " + feedback.getSiteId() + ", Content: " + feedback.getContent() +
                    ", Rating: " + feedback.getRating() + ", Time: " + feedback.getFormattedTimestamp());
        }
    }

    /**
     * Displays an error message to the Agency Operator.
     */
    public void displayError(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Simulates the Agency Operator viewing the site list and selecting a site,
     * then activating view feedback.
     * This method orchestrates the interaction as per the sequence diagram.
     */
    public void simulateViewFeedbackFlow(String siteId) {
        // Step 1: Site list is displayed (assumed from SearchSite use case)
        // In a real scenario, we would have a list of sites; here we simulate.
        System.out.println("Agency Operator views site list.");

        // Step 2: Agency Operator selects a site
        selectSite(siteId);

        // Step 3: Agency Operator activates view feedback
        activateViewFeedback();

        // Step 4: UI calls controller to get feedback
        List<FeedbackDTO> feedback = viewFeedbackController.viewFeedback(siteId);
        if (feedback != null) {
            displayFeedback(feedback);
        }
    }
}