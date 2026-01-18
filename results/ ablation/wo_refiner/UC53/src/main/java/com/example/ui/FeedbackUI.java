package com.example.ui;

import com.example.adapters.*;

/**
 * UI layer class that interacts with the tourist and controller.
 */
public class FeedbackUI {
    private FeedbackController controller;
    private String currentTouristId;
    private String currentSiteId;

    public void setController(FeedbackController controller) {
        this.controller = controller;
    }

    /**
     * Activates the feedback feature for a site (step 1 in sequence diagram).
     * Updated signature to include siteId as per class diagram (requirement 5 & 6).
     */
    public void activateFeedbackFeature(String siteId) {
        this.currentSiteId = siteId;
        // In a real UI, this would show the feedback button or trigger form display.
        System.out.println("Feedback feature activated for site: " + siteId);
    }

    /**
     * Displays the feedback form (called by controller in sequence diagram).
     */
    public void displayForm() {
        System.out.println("Displaying feedback form for site: " + currentSiteId);
    }

    /**
     * Submits the filled form (step 3 in sequence diagram).
     */
    public void submitForm(FeedbackForm feedbackForm, String touristId) {
        if (controller == null) {
            System.out.println("Error: Controller not set.");
            return;
        }
        FeedbackResponse response = controller.submitFeedback(feedbackForm, touristId);
        if ("SUCCESS".equals(response.getStatus())) {
            System.out.println("Success: " + response.getMessage());
        } else {
            System.out.println("Error: " + response.getMessage());
        }
    }

    /**
     * Cancels the operation (requirement 13).
     */
    public void cancel() {
        if (controller != null && currentTouristId != null && currentSiteId != null) {
            controller.cancelFeedback(currentTouristId, currentSiteId);
        }
        System.out.println("Feedback operation cancelled.");
        displayCancelledMessage();
    }

    /**
     * Display cancelled message (for sequence diagram message m42).
     */
    public void displayCancelledMessage() {
        System.out.println("Operation cancelled");
    }

    // Simulate setting tourist (e.g., after login)
    public void setCurrentTouristId(String touristId) {
        this.currentTouristId = touristId;
    }
}