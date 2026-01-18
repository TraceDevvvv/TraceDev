package com.duplicatefeedback;

import com.duplicatefeedback.adapter.persistence.FeedbackRepositoryImpl;
import com.duplicatefeedback.adapter.notification.NotificationServiceImpl;
import com.duplicatefeedback.adapter.web.UserSession;
import com.duplicatefeedback.application.strategy.ExistingFeedbackStrategy;
import com.duplicatefeedback.application.strategy.ImmediateNotificationStrategy;
import com.duplicatefeedback.application.controller.FeedbackUseCaseController;
import com.duplicatefeedback.framework.ui.FeedbackUI;
import com.duplicatefeedback.application.ports.out.FeedbackRepository;
import com.duplicatefeedback.application.ports.out.NotificationService;
import com.duplicatefeedback.domain.model.Feedback;
import java.time.LocalDateTime;

/**
 * Main class to simulate the duplicate feedback prevention system.
 * This demonstrates the flow from the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        FeedbackRepository feedbackRepository = new FeedbackRepositoryImpl();
        NotificationService notificationService = new NotificationServiceImpl();
        ExistingFeedbackStrategy detectionStrategy = new ExistingFeedbackStrategy(feedbackRepository);
        ImmediateNotificationStrategy notificationStrategy = new ImmediateNotificationStrategy(notificationService);
        FeedbackUseCaseController controller = new FeedbackUseCaseController(
                detectionStrategy,
                notificationStrategy,
                feedbackRepository
        );
        UserSession session = new UserSession("user123", true);
        session.setSelectedSiteId("site456");
        FeedbackUI ui = new FeedbackUI(controller, session);

        // Simulate existing feedback in repository
        Feedback existingFeedback = new Feedback(
                "existing-id",
                "site456",
                "user123",
                "Great site!",
                LocalDateTime.now().minusDays(1)
        );
        feedbackRepository.save(existingFeedback);

        System.out.println("=== Simulating Duplicate Feedback Prevention ===");
        System.out.println("Existing feedback present for user123 on site456.");
        System.out.println("User attempts to submit another feedback for same site...\n");

        // This should trigger duplicate detection and alternative flow
        ui.submitFeedback("user123", "site456", "Another feedback");

        System.out.println("\n=== Simulating New Feedback Submission ===");
        // This should succeed
        ui.submitFeedback("user123", "site789", "First feedback for new site");
    }
}