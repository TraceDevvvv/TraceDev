package com.example.feedbackapp.controller;

import com.example.feedbackapp.service.FeedbackApplicationService;
import com.example.feedbackapp.view.FeedbackView;
import com.example.feedbackapp.model.Feedback; // Required for the 'createNewFeedback' call demonstration.

/**
 * Controller class responsible for handling user interactions related to feedback.
 * It orchestrates calls between the view and the application service.
 */
public class FeedbackController {

    private final FeedbackApplicationService feedbackApplicationService;
    private final FeedbackView feedbackView;

    /**
     * Constructor for FeedbackController.
     * Injects dependencies for the application service and the view.
     *
     * @param feedbackApplicationService The service handling business logic.
     * @param feedbackView The view handling user interface interactions.
     */
    public FeedbackController(FeedbackApplicationService feedbackApplicationService, FeedbackView feedbackView) {
        this.feedbackApplicationService = feedbackApplicationService;
        this.feedbackView = feedbackView;
    }

    /**
     * Handles an attempt by the user to submit feedback for a specific site.
     * This method implements the main flow of the sequence diagram.
     *
     * @param siteId The ID of the site for which feedback is being submitted.
     */
    public void submitFeedbackAttempt(String siteId) {
        System.out.println("\n[FeedbackController] User attempts to submit feedback for site: " + siteId);

        // Sequence Diagram: FeedbackController -> FeedbackApplicationService: checkExistingFeedbackAndPreventSubmission(siteId: String)
        boolean feedbackExists = feedbackApplicationService.checkExistingFeedbackAndPreventSubmission(siteId);

        // Sequence Diagram: alt existingFeedback is not null (Feedback already issued for site)
        if (feedbackExists) {
            System.out.println("[FeedbackController] Operation to insert new feedback is cancelled due to existing feedback.");
            // Sequence Diagram: FeedbackController -> FeedbackView: displayNotification(...)
            feedbackView.displayNotification("Feedback already exists for the selected site. New submission prevented.");

            // Sequence Diagram: User -> FeedbackView: confirmsReading()
            // This is simulated by a call from the Controller, assuming the View handles user input.
            // FeedbackView -> FeedbackController: handleNotificationConfirmation() is also triggered.
            if (feedbackView.confirmsReading()) { // User confirms reading the notification
                handleNotificationConfirmation(); // Notification confirmation handled by controller
            }

            // Sequence Diagram: FeedbackController -> FeedbackApplicationService: recoverPreviousState()
            feedbackApplicationService.recoverPreviousState();

            // Sequence Diagram: FeedbackController -> FeedbackView: displayOperationCancelledConfirmation()
            feedbackView.displayOperationCancelledConfirmation();
        }
        // Sequence Diagram: else existingFeedback is null (No feedback found, proceed with submission)
        else {
            System.out.println("[FeedbackController] No existing feedback found. Proceeding with new feedback submission.");
            // This path is not the focus of the specific sequence diagram, but it's defined.
            // Sequence Diagram: FeedbackController -> FeedbackApplicationService: createNewFeedback(feedbackData)
            // Assuming some dummy feedback data for demonstration.
            Object feedbackData = "Site: " + siteId + ", Content: This is new feedback.";
            feedbackApplicationService.createNewFeedback(feedbackData);
            System.out.println("[FeedbackController] (Normal flow) New feedback submitted successfully for site: " + siteId);
            // In a real app, typically a success notification would follow.
        }
        System.out.println("[FeedbackController] Finished processing feedback attempt for site: " + siteId);
    }

    /**
     * Handles the confirmation that a notification has been read by the user.
     * This method is called by the view after user interaction.
     */
    public void handleNotificationConfirmation() {
        System.out.println("[FeedbackController] User has confirmed reading the notification.");
        // Any specific actions after notification confirmation can go here.
        // For example, logging, clearing temporary UI elements, etc.
    }
}