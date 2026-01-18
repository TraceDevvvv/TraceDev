// Main class to run the feedback management system and demonstrate the sequence.
package com.feedback.management;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Setup components
        FeedbackRepository repository = new FeedbackRepositoryImpl();
        // Pre-populate with a duplicate entry for testing
        Feedback existing = new Feedback("F1", "S1", "U1", "Great site!", new Date());
        repository.save(existing);

        CheckDuplicateFeedbackUseCase useCase = new CheckDuplicateFeedbackUseCase(repository);
        NotificationService notificationService = new NotificationService();
        StateManager stateManager = new StateManager();
        FeedbackController controller = new FeedbackController(useCase, notificationService, stateManager);

        // Simulate the sequence diagram flow for duplicate case
        System.out.println("=== Duplicate Feedback Scenario ===");
        controller.handleFeedbackSubmission("S1", "U1"); // Duplicate exists
        controller.confirmNotificationRead();
        controller.recoverPreviousState();

        System.out.println("\n=== Non-Duplicate Feedback Scenario ===");
        controller.handleFeedbackSubmission("S2", "U1"); // No duplicate
    }
}