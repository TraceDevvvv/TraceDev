package com.example;

import com.example.adapters.*;
import com.example.application.*;
import com.example.infrastructure.*;
import com.example.ui.FeedbackUI;

/**
 * Main class to demonstrate the runnable system.
 * Sets up all components and simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Insert Feedback Demo ===");

        // Setup infrastructure
        DatabaseService db = new DatabaseService();
        FeedbackRepository feedbackRepo = new FeedbackRepositoryImpl(db);
        VisitedSiteRepository visitedRepo = new VisitedSiteRepositoryImpl(db);
        NotificationService notificationService = new NotificationServiceImpl();

        // Setup use case
        InsertFeedbackUseCase useCase = new InsertFeedbackUseCase(
                feedbackRepo,
                visitedRepo,
                notificationService
        );

        // Setup controller and presenter
        FeedbackPresenter presenter = new FeedbackPresenter();
        FeedbackController controller = new FeedbackController(useCase, presenter);

        // Setup UI
        FeedbackUI ui = new FeedbackUI();
        ui.setController(controller);
        ui.setCurrentTouristId("T001");

        // Simulate sequence diagram flow
        System.out.println("\n--- Step 1: Tourist activates feedback feature ---");
        ui.activateFeedbackFeature("S001");

        System.out.println("\n--- Step 2: UI calls controller.insertFeedback ---");
        FeedbackForm form = new WebFeedbackForm("S001", 4, "Great experience!");
        FeedbackResponse response = controller.insertFeedback(form, "T001");
        System.out.println("Response: " + response.getStatus() + " - " + response.getMessage());

        System.out.println("\n--- Step 3: Tourist fills and submits form ---");
        ui.submitForm(form, "T001");

        System.out.println("\n--- Step 4: Simulate cancellation ---");
        ui.cancel();

        System.out.println("\n=== Demo Completed ===");
    }
}