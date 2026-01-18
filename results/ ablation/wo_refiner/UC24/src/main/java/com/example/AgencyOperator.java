package com.example;

import com.example.controller.ViewFeedbackController;
import com.example.repository.SiteRepository;
import com.example.repository.FeedbackRepository;
import com.example.service.AuthenticationService;
import com.example.model.Site;
import com.example.model.Feedback;
import java.util.List;

/**
 * Agency Operator actor that uses the ViewFeedbackController.
 * This class simulates the operator's interactions as per the sequence diagram.
 */
public class AgencyOperator {
    private ViewFeedbackController controller;
    private String operatorId;

    public AgencyOperator(String operatorId) {
        this.operatorId = operatorId;
        // Initialize dependencies
        SiteRepository siteRepo = new SiteRepository();
        FeedbackRepository feedbackRepo = new FeedbackRepository();
        AuthenticationService authService = new AuthenticationService();
        controller = new ViewFeedbackController(siteRepo, feedbackRepo, authService);
    }

    /**
     * Simulates the full use case flow as per sequence diagram.
     */
    public void runUseCase() {
        System.out.println("=== Agency Operator Use Case Started ===");

        // Step 1: Check login (requirement R4)
        boolean loggedIn = true; // Assume logged in for this simulation.
        if (!loggedIn) {
            System.out.println("Error: Not logged in");
            System.out.println("Precondition failed - requirement R4");
            // Sequence diagram message m33: show error "Not logged in"
            // Sequence diagram note m34: Precondition failed - requirement R4
            return;
        }

        // Step 2: Search sites (R5/R6)
        System.out.println("\n--- Step 2: Searching sites ---");
        List<Site> sites = controller.searchSites("Park");
        // Sequence diagram return message m6: display site list
        System.out.println("Display site list: Found " + sites.size() + " sites.");
        sites.forEach(site -> System.out.println("  - " + site));

        // Step 3: Select a site (R5/R6)
        System.out.println("\n--- Step 3: Selecting site ---");
        String selectedSiteId = "S1"; // Assume selection of Central Park
        Site selectedSite = controller.selectSite(selectedSiteId);
        if (selectedSite != null) {
            System.out.println("Selected site: " + selectedSite);
        } else {
            System.out.println("Site not found with ID: " + selectedSiteId);
            // Handle error case as per sequence diagram
            return;
        }

        // Step 4: View feedback (R6)
        System.out.println("\n--- Step 4: Viewing feedback ---");
        // Using handleViewFeedback method as per sequence diagram
        List<Feedback> feedbackList = controller.handleViewFeedback(selectedSiteId);
        // Sequence diagram return message m17: display feedback list
        System.out.println("Display feedback list for site " + selectedSiteId + ":");
        if (feedbackList.isEmpty()) {
            // Could be either no feedback or error
            System.out.println("  No feedback found or error occurred");
        } else {
            feedbackList.forEach(fb -> System.out.println("  - " + fb));
        }

        // Step 5: Upload feedback (R7)
        System.out.println("\n--- Step 5: Uploading feedback ---");
        boolean uploadSuccess = controller.uploadFeedback(selectedSiteId, "Excellent facilities!");
        if (uploadSuccess) {
            // Sequence diagram return message m25: upload success
            System.out.println("Upload success - feedback saved successfully");
        } else {
            // Could be various errors
            System.out.println("Upload failed - check error messages above");
            // Sequence diagram message m26: show error "Invalid content" (if content invalid)
            // Sequence diagram message m33: show error "Not logged in" (if not logged in)
            System.out.println("Display error message to user");
        }

        // Step 6: Test invalid content case
        System.out.println("\n--- Step 6: Testing invalid content ---");
        boolean invalidUpload = controller.uploadFeedback(selectedSiteId, "");
        if (!invalidUpload) {
            // Sequence diagram message m26: show error "Invalid content"
            System.out.println("Error: Invalid content - upload rejected");
            // Sequence diagram message m28: display error message
            System.out.println("Display error message to user");
        }

        // Step 7: Simulate connection interruption (R9)
        System.out.println("\n--- Step 7: Connection interruption handling ---");
        controller.connectionLost();
        controller.handleConnectionInterruption();
        // Manually trigger retry (simulating auto-retry)
    }
}