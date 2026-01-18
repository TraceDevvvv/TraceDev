package com.example;

/**
 * Interactor implementing the business logic for editing a feedback comment.
 * Follows the Clean Architecture pattern.
 */
public class EditFeedbackCommentInteractor implements EditFeedbackCommentInputPort {
    private FeedbackRepository feedbackRepository;
    private NotificationService notificationService;
    private ValidationService validationService;
    private ErrorHandler errorHandler;

    public EditFeedbackCommentInteractor(FeedbackRepository feedbackRepository,
                                         NotificationService notificationService,
                                         ValidationService validationService,
                                         ErrorHandler errorHandler) {
        this.feedbackRepository = feedbackRepository;
        this.notificationService = notificationService;
        this.validationService = validationService;
        this.errorHandler = errorHandler;
    }

    @Override
    public void execute(EditFeedbackCommentRequestDTO request) {
        // Validate the new comment
        if (!validateData(request)) {
            errorHandler.handleValidationError("Invalid data in request");
            activateErroredUseCase();
            return;
        }

        // Validate feedback exists
        if (!validationService.validateFeedbackExists(request.getFeedbackId())) {
            errorHandler.handleValidationError("Feedback does not exist");
            activateErroredUseCase();
            return;
        }

        // Ask for confirmation (simulated)
        askForConfirmation();
        // In a real scenario, confirmation would come from the controller after UI interaction.
        // For simplicity, we assume confirmation is received.
        boolean confirmationReceived = true; // Simulated confirmation

        if (!confirmationReceived) {
            errorHandler.logError("Confirmation not received");
            return;
        }

        // Retrieve feedback entity
        Feedback feedback = feedbackRepository.findById(request.getFeedbackId());
        if (feedback == null) {
            errorHandler.handleValidationError("Feedback not found during update");
            return;
        }

        // Update comment
        feedback.updateComment(request.getEditedComment());

        // Persist update
        boolean updated = feedbackRepository.update(feedback);
        if (!updated) {
            errorHandler.logError("Failed to update feedback in repository");
            return;
        }

        // Update notification system
        notificationService.updateForFeedback(request.getFeedbackId(), request.getEditedComment());

        // Remember the change (placeholder for any state tracking)
        rememberCommentChanged();

        System.out.println("Operation successful - notification system modified.");
    }

    /**
     * Validates the request data.
     * @param request the request DTO
     * @return true if data is valid
     */
    private boolean validateData(EditFeedbackCommentRequestDTO request) {
        return validationService.validateComment(request.getEditedComment());
    }

    /**
     * Updates the notification system about the comment change.
     * @param feedbackId the feedback ID
     * @param newComment the new comment
     */
    private void updateNotificationSystem(String feedbackId, String newComment) {
        // This is now delegated to NotificationService; kept for compatibility with diagram.
        notificationService.updateForFeedback(feedbackId, newComment);
    }

    /**
     * Handles errors by delegating to ErrorHandler.
     * @param error the error message
     */
    protected void handleError(String error) {
        errorHandler.logError(error);
    }

    /**
     * Simulates asking for user confirmation.
     */
    private void askForConfirmation() {
        System.out.println("Asking user for confirmation...");
    }

    /**
     * Activates errored use case flow (placeholder).
     */
    private void activateErroredUseCase() {
        System.out.println("Errored use case activated.");
    }

    /**
     * Remembers that a comment was changed (placeholder for state tracking).
     */
    private void rememberCommentChanged() {
        System.out.println("Comment change recorded.");
    }
}