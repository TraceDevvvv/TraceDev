package com.example;

/**
 * Controller for handling feedback operations.
 */
public class FeedbackController {
    private SubmitFeedbackService submitFeedbackService;

    public FeedbackController(SubmitFeedbackService submitFeedbackService) {
        this.submitFeedbackService = submitFeedbackService;
    }

    public void activateFeedback() {
        System.out.println("FeedbackController: Feedback activation triggered.");
        // In a real scenario, this might prepare the controller state.
    }

    public ResultDTO submitFeedback(FeedbackForm formData) {
        // Directly delegate to service; validation happens there.
        return submitFeedbackService.submitFeedback(formData);
    }

    public void displayFeedbackForm() {
        System.out.println("FeedbackController: Displaying feedback form.");
    }

    public void handleFeedbackAlreadyReleased() {
        System.out.println("FeedbackController: Handling duplicate feedback.");
    }

    public void handleErrored() {
        System.out.println("FeedbackController: Handling validation error.");
    }

    public void cancelFeedback() {
        System.out.println("FeedbackController: Feedback cancelled by tourist.");
    }

    // Sequence diagram messages
    public void showFeedbackAlreadyReleased() {
        System.out.println("Controller: Showing 'FeedbackAlreadyReleased'.");
    }

    public void showFeedbackForm() {
        System.out.println("Controller: Showing feedback form.");
    }

    public void showErrored() {
        System.out.println("Controller: Showing 'Errored'.");
    }

    public void showSuccessNotification() {
        System.out.println("Controller: Showing success notification.");
    }
}