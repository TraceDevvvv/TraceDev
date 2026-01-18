package com.example.adapters;

import com.example.application.*;

/**
 * Controller that handles feedback requests from UI.
 * Manages the flow and error handling as per sequence diagram.
 */
public class FeedbackController {
    private final InsertFeedbackUseCase insertFeedbackUseCase;
    private final FeedbackPresenter presenter;

    public FeedbackController(InsertFeedbackUseCase insertFeedbackUseCase, FeedbackPresenter presenter) {
        this.insertFeedbackUseCase = insertFeedbackUseCase;
        this.presenter = presenter;
    }

    /**
     * Called when UI activates feedback feature (step 2 in sequence diagram).
     */
    public FeedbackResponse insertFeedback(FeedbackForm feedbackForm, String touristId) {
        // Step: Create FeedbackData and TouristData
        FeedbackData feedbackData = createFeedbackDataFromForm(feedbackForm);
        TouristData touristData = createTouristDataFromTouristId(touristId);

        // Step: Execute use case
        InsertFeedbackResult result = insertFeedbackUseCase.execute(feedbackData, touristData);

        // Step: Handle results
        switch (result) {
            case SUCCESS:
                return presenter.presentSuccess();
            case FEEDBACK_ALREADY_EXISTS:
                handleFeedbackAlreadyReleased(); // requirement 7
                return presenter.presentError(result);
            case ERROR_INVALID_DATA:
            case SERVER_INTERRUPTION:
                handleErrored(); // requirement 9
                return presenter.presentError(result);
            default:
                return presenter.presentError(result);
        }
    }

    /**
     * Called when UI submits the form (step 3 in sequence diagram).
     * This method is similar to insertFeedback but may be called after form display.
     */
    public FeedbackResponse submitFeedback(FeedbackForm feedbackForm, String touristId) {
        // In this implementation, submission logic is same as initial insertion.
        return insertFeedback(feedbackForm, touristId);
    }

    /**
     * Called when tourist cancels the operation (requirement 13).
     */
    public void cancelFeedback(String touristId, String siteId) {
        // Logic to cancel operation - could clean up any pending state.
        handleOperationCancelled();
    }

    /**
     * Handles case when feedback already released (requirement 7).
     */
    public void handleFeedbackAlreadyReleased() {
        // Log or perform additional actions for already released feedback.
        System.out.println("Feedback already released for this site.");
    }

    /**
     * Handles error scenarios (requirement 9).
     */
    public void handleErrored() {
        // Log or perform additional error handling.
        System.out.println("An error occurred during feedback submission.");
    }

    /**
     * Handles operation cancelled.
     */
    public void handleOperationCancelled() {
        System.out.println("Operation cancelled");
    }

    /**
     * Create FeedbackData from form (for sequence diagram message m15).
     */
    private FeedbackData createFeedbackDataFromForm(FeedbackForm feedbackForm) {
        return new FeedbackData(
                feedbackForm.getSiteId(),
                feedbackForm.getVote(),
                feedbackForm.getComment()
        );
    }

    /**
     * Create TouristData from touristId (for sequence diagram message m16).
     */
    private TouristData createTouristDataFromTouristId(String touristId) {
        // Assuming tourist card id is retrieved elsewhere; using placeholder
        return new TouristData(touristId, "CARD_" + touristId);
    }
}