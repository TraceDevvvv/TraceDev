package com.feedback.management;

public class FeedbackController {
    private CheckDuplicateFeedbackUseCase checkDuplicateUseCase;
    private NotificationService notificationService;
    private StateManager stateManager;

    public FeedbackController(CheckDuplicateFeedbackUseCase useCase, NotificationService notificationService, StateManager stateManager) {
        this.checkDuplicateUseCase = useCase;
        this.notificationService = notificationService;
        this.stateManager = stateManager;
    }

    public void handleFeedbackSubmission(String siteId, String userId) {
        CheckDuplicateRequest request = new CheckDuplicateRequest(siteId, userId);
        CheckDuplicateResponse response = checkDuplicateUseCase.execute(request);

        if (response.isDuplicate()) {
            Feedback existingFeedback = response.getExistingFeedback();
            notificationService.showMessage("You have already issued feedback for this site");
            cancelFeedbackSubmission();
            confirmNotificationRead();
            recoverPreviousState();
        } else {
            System.out.println("**Proceed to normal**\nfeedback creation flow");
        }
        controlReturned();
    }

    public void confirmNotificationRead() {
        boolean acknowledged = notificationService.confirmRead();
        if (acknowledged) {
            System.out.println("Notification acknowledged.");
        }
    }

    public void recoverPreviousState() {
        stateManager.restorePreviousState();
    }

    public void cancelFeedbackSubmission() {
        System.out.println("**Operation Cancelled**\nNo new feedback inserted");
    }

    public void controlReturned() {
        System.out.println("Control returned.");
    }
}