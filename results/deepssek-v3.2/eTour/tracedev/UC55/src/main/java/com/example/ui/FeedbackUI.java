package com.example.ui;

import com.example.controller.DuplicateFeedbackController;
import com.example.dto.FeedbackRequest;
import com.example.dto.FeedbackResponse;

/**
 * Boundary class for the UI layer handling feedback interactions.
 */
public class FeedbackUI {
    private DuplicateFeedbackController controller;

    public FeedbackUI(DuplicateFeedbackController controller) {
        this.controller = controller;
    }

    /**
     * Displays the feedback form to the user.
     */
    public void displayFeedbackForm() {
        // In a real implementation, this would render a UI form.
        System.out.println("Displaying feedback form.");
    }

    /**
     * Shows a notification message to the user.
     * @param message the notification message.
     */
    public void showNotification(String message) {
        System.out.println("Notification: " + message);
    }

    /**
     * Confirms that the user has read the notification.
     * Calls the controller to handle confirmation.
     */
    public void confirmNotificationRead() {
        // Assume we have a current user ID; in practice this would come from session.
        String userId = "currentUserId";
        controller.confirmNotificationRead(userId);
        System.out.println("Notification read confirmed for user: " + userId);
    }

    /**
     * Cancels the feedback insertion process.
     */
    public void cancelFeedbackInsertion() {
        System.out.println("Feedback insertion cancelled.");
    }

    /**
     * Attempts to insert new feedback for a site.
     * This simulates the user action and triggers the duplicate check.
     * @param siteId the site identifier.
     * @param userId the user identifier.
     * @param feedbackContent the feedback content.
     */
    public void attemptInsertFeedback(String siteId, String userId, String feedbackContent) {
        FeedbackRequest request = new FeedbackRequest(siteId, userId, feedbackContent);
        FeedbackResponse response = controller.handleFeedbackRequest(request);
        if (response.isSuccess()) {
            showNotification("Proceed with normal feedback submission.");
            // Proceed with normal submission flow (not implemented in this example).
        } else {
            showNotification(response.getMessage());
            cancelFeedbackInsertion();
            // Wait for user confirmation (simulated by calling confirmNotificationRead).
            confirmNotificationRead();
        }
    }

    /**
     * Attempt to insert new feedback for site.
     * Corresponds to sequence diagram message m1.
     */
    public void attemptToInsertNewFeedbackForSite(String siteId, String userId, String content) {
        System.out.println("Attempting to insert new feedback for site " + siteId + " by user " + userId);
        attemptInsertFeedback(siteId, userId, content);
    }

    /**
     * Display duplicate feedback notification.
     * Corresponds to sequence diagram message m13.
     */
    public void displayDuplicateFeedbackNotification(String message) {
        showNotification(message);
    }

    /**
     * Confirm reading notification.
     * Corresponds to sequence diagram message m14.
     */
    public void confirmReadingNotification(String userId) {
        controller.confirmNotificationRead(userId);
    }

    /**
     * Restore previous interaction state.
     * Corresponds to sequence diagram message m20.
     */
    public void restorePreviousInteractionState(String stateInfo) {
        System.out.println("Restoring previous interaction state: " + stateInfo);
    }

    /**
     * Proceed with normal feedback submission.
     * Corresponds to sequence diagram message m22.
     */
    public void proceedWithNormalFeedbackSubmission() {
        System.out.println("Proceeding with normal feedback submission.");
    }
}