package com.example;

import java.util.List;

/**
 * Represents the view responsible for displaying a list of feedback entries for a selected site.
 * It provides methods to show feedback data and simulate user selection.
 */
public class FeedbackListView {
    private CommentModificationController controller;

    /**
     * Sets the controller that this view will interact with.
     * @param controller The CommentModificationController instance.
     */
    public void setController(CommentModificationController controller) {
        this.controller = controller;
    }

    /**
     * Displays a list of feedback entries to the user.
     * @param feedbackList A list of FeedbackDTO objects to display.
     */
    public void displayFeedback(List<FeedbackDTO> feedbackList) {
        System.out.println("\n--- Feedback for Selected Site ---");
        if (feedbackList == null || feedbackList.isEmpty()) {
            System.out.println("No feedback found for this site.");
            return;
        }
        for (FeedbackDTO feedback : feedbackList) {
            System.out.println("ID: " + feedback.id + ", Site: " + feedback.siteName +
                    ", Comment: '" + feedback.comment + "', Status: " + feedback.status);
        }
        System.out.println("----------------------------------");
    }

    /**
     * Simulates showing the feedback list to the operator.
     * In a real UI, this would render a screen.
     */
    public void showFeedbackList() {
        System.out.println("Please select a Feedback ID from the list to edit its comment.");
    }

    /**
     * Simulates the operator selecting a feedback entry.
     * This method would typically be called by UI event handlers.
     * @param feedbackId The ID of the selected feedback.
     */
    public void selectFeedback(String feedbackId) {
        System.out.println("FeedbackListView: User selected feedback ID: " + feedbackId);
        // Delegate to the controller to handle the selection
        if (controller != null) {
            controller.selectFeedbackForEdit(feedbackId);
        } else {
            System.err.println("Error: Controller not set for FeedbackListView.");
        }
    }

    /**
     * Displays an error message to the operator.
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.err.println("FeedbackListView Error: " + message);
    }

    /**
     * Placeholder for displaying a general error.
     * In a real UI, this would show an error dialog.
     */
    public void displayError() {
        System.err.println("FeedbackListView: An error occurred while displaying feedback.");
    }
}