package com.example.presentation;

import com.example.application.FeedbackController;
import com.example.application.ValidationService; // New import for direct call as per SD
import com.example.domain.FeedbackFormDTO;
import com.example.domain.FeedbackResponseDTO;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Presentation Layer component responsible for interacting with the user
 * to gather feedback and display messages.
 * This class simulates a UI for a console application.
 */
public class FeedbackUI {
    private final FeedbackController feedbackController;
    private final ValidationService validationService; // New dependency as per SD message to ValidationService
    private final Scanner scanner;

    // A simple state to indicate if a form is currently being displayed
    private boolean formActive = false;

    /**
     * Constructor for FeedbackUI, injecting the FeedbackController and ValidationService dependencies.
     * @param feedbackController The application layer controller.
     * @param validationService The application layer validation service.
     * @param scanner Shared scanner for user input.
     */
    public FeedbackUI(FeedbackController feedbackController, ValidationService validationService, Scanner scanner) {
        this.feedbackController = feedbackController;
        this.validationService = validationService; // Initialize new dependency
        this.scanner = scanner;
    }

    /**
     * Activates the feedback feature for a given site.
     * This is the entry point for the feedback submission use case from the UI perspective.
     * It orchestrates the calls to the controller and handles UI specific logic.
     *
     * @param touristId The ID of the current tourist.
     * @param siteId The ID of the site for which feedback is to be activated.
     */
    public void activateFeedbackFeature(String touristId, String siteId) {
        System.out.println("\n[FeedbackUI] Activating feedback feature for site: " + siteId + " (Tourist: " + touristId + ")");

        // 1. Call initiateFeedbackSubmission to check for existing feedback
        FeedbackResponseDTO initiationResponse = feedbackController.initiateFeedbackSubmission(touristId, siteId);

        if (!initiationResponse.isSuccess()) {
            // Flow: FeedbackGiàRilasciato
            showAlreadySubmittedFeedbackError();
            System.out.println("[FeedbackUI] " + initiationResponse.getMessage()); // Display specific message from controller
            return; // Exit the flow
        }

        // If initiation was successful, proceed to display the form and collect input.
        System.out.println("[FeedbackUI] Initiating form display for site: " + siteId);
        // This method combines displaying the form and getting input, handling cancellation.
        FeedbackFormDTO formData = displayAndGetFeedbackFormInput(siteId);

        if (formData == null) {
            // Exit Condition: Tourist cancels operation (REQ-EC1)
            cancelForm();
            return; // Exit the flow
        }

        // 2. If form was filled and not cancelled, submit the feedback
        // Now calling the new fillAndSubmitForm method, aligning with SD message.
        fillAndSubmitForm(touristId, formData.getSiteId(), formData.getVote(), formData.getComment());
    }

    /**
     * Displays the feedback form for a specific site and collects user input.
     * This method handles the actual console interaction.
     *
     * @param siteId The ID of the site for which to display the form.
     * @return A FeedbackFormDTO with user input, or null if the user cancels.
     */
    private FeedbackFormDTO displayAndGetFeedbackFormInput(String siteId) {
        formActive = true;
        System.out.println("\n--- Feedback Form for Site: " + siteId + " ---");
        System.out.println("Please provide your feedback (Type 'cancel' at any prompt to abort).");

        int vote = -1;
        String comment = "";
        String inputLine;

        // Get Vote
        while (vote < 1 || vote > 5) {
            System.out.print("Your vote (1-5 stars): ");
            try {
                inputLine = scanner.nextLine().trim();
                if (inputLine.equalsIgnoreCase("cancel")) {
                    return null;
                }
                vote = Integer.parseInt(inputLine);
                if (vote < 1 || vote > 5) {
                    System.out.println("Invalid vote. Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Get Comment
        System.out.print("Your comment: ");
        inputLine = scanner.nextLine().trim();
        if (inputLine.equalsIgnoreCase("cancel")) {
            return null;
        }
        comment = inputLine;

        System.out.println("--- End of Form ---");
        formActive = false; // Form is no longer active after input
        return new FeedbackFormDTO(siteId, vote, comment);
    }

    /**
     * Processes the collected feedback data, performing validation and submission.
     * This method represents the 'fillAndSubmitForm' message from Tourist to FeedbackUI in the sequence diagram.
     * It also orchestrates calls to ValidationService and FeedbackController as per the sequence diagram.
     *
     * @param touristId The ID of the tourist.
     * @param siteId The ID of the site.
     * @param vote The vote/rating provided.
     * @param comment The comment provided.
     */
    public void fillAndSubmitForm(String touristId, String siteId, int vote, String comment) {
        System.out.println("\n[FeedbackUI] Received fillAndSubmitForm request for site '" + siteId + "' by tourist '" + touristId + "'.");

        // SD message: FeedbackUI -> ValidationService: validateFeedbackForm(siteId, vote, comment)
        if (!validationService.validateFeedbackForm(siteId, vote, comment)) {
            showInvalidDataError(); // Flow: Errored
            return;
        }

        // SD message: FeedbackUI -> FeedbackController: submitFeedback(touristId, siteId, vote, comment)
        FeedbackResponseDTO submissionResponse = feedbackController.submitFeedback(touristId, siteId, vote, comment);

        if (submissionResponse.isSuccess()) {
            showSuccessMessage();
            System.out.println("[FeedbackUI] " + submissionResponse.getMessage());
            System.out.println("[FeedbackUI] New Feedback ID: " + submissionResponse.getFeedbackId());
        } else {
            // Handle various error scenarios
            if (submissionResponse.getMessage().contains("Invalid")) { // Though validation is done above, keep for robustness
                showInvalidDataError(); // Flow: Errored
            } else if (submissionResponse.getMessage().contains("Connection")) {
                showErrorMessage(submissionResponse.getMessage()); // Exit Condition: Connection Interruption
            } else {
                showErrorMessage(submissionResponse.getMessage()); // General error
            }
        }
    }

    /**
     * Displays a generic feedback form to the tourist (simulated).
     * The sequence diagram implies this method just displays, and `getFeedbackFormInput`
     * would be a separate call or user action. In console UI, they are often combined or sequential.
     * For this simulation, the `displayAndGetFeedbackFormInput` method handles both.
     * @param siteId The ID of the site.
     */
    public void displayFeedbackForm(String siteId) {
        // This method is primarily used internally by `activateFeedbackFeature` in this simulation,
        // but included for completeness as per the Class Diagram.
        System.out.println("[FeedbackUI] Displaying form for site: " + siteId);
        // Actual input gathering is done by `displayAndGetFeedbackFormInput`.\
    }

    /**
     * Placeholder for getting feedback form input.
     * In this console implementation, it's integrated into `displayAndGetFeedbackFormInput`.
     * @return An empty FeedbackFormDTO, or null (actual data gathered in another method).
     */
    public FeedbackFormDTO getFeedbackFormInput() {
        // This method exists to fulfill the Class Diagram, but its primary logic
        // for collecting interactive input is handled by `displayAndGetFeedbackFormInput`
        // which returns the actual DTO or null for cancellation.
        System.out.println("[FeedbackUI] (getFeedbackFormInput called - input logic handled elsewhere)");
        return null;
    }

    /**
     * Displays a success message to the user.
     */
    public void showSuccessMessage() {
        System.out.println("\n*** SUCCESS! ***");
    }

    /**
     * Displays a generic error message to the user.
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n!!! ERROR: " + message + " !!!");
    }

    /**
     * Displays an error message specifically for already submitted feedback.
     */
    public void showAlreadySubmittedFeedbackError() {
        showErrorMessage("You have already submitted feedback for this site.");
    }

    /**
     * Displays an error message specifically for invalid or insufficient data.
     */
    public void showInvalidDataError() {
        showErrorMessage("Invalid or insufficient data provided in the feedback form.");
    }

    /**
     * Handles the user cancelling the form submission.
     * Implements REQ-EC1 (Exit Conditions: Tourist cancels).
     */
    public void cancelForm() {
        System.out.println("[FeedbackUI] Operation cancelled by tourist.");
        showErrorMessage("Operation cancelled.");
    }
}