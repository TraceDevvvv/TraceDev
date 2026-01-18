package com.example.ui;

import com.example.model.Feedback;
import com.example.service.FeedbackService;
import java.util.Scanner;

/**
 * Simulates the user interface for feedback operations.
 */
public class FeedbackUI {
    private FeedbackService feedbackService = new FeedbackService();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Main interaction loop.
     */
    public void startInteraction() {
        while (true) {
            System.out.println("\n--- Feedback Management ---");
            System.out.println("1. Submit feedback for a site");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            if ("1".equals(choice)) {
                handleFeedbackSubmission();
            } else if ("2".equals(choice)) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Handles the feedback submission flow as described in the use case.
     */
    private void handleFeedbackSubmission() {
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter the site ID: ");
        String siteId = scanner.nextLine();

        // Entry condition: feedback already exists?
        if (feedbackService.hasFeedback(userId, siteId)) {
            // 1. System notifies the user
            System.out.println("Notification: You have already issued a feedback for the selected site.");
            Feedback existing = feedbackService.getFeedback(userId, siteId);
            System.out.println("Existing feedback content: " + existing.getContent());
            System.out.println("Submitted on: " + existing.getTimestamp());

            // 2. System cancels the operation to insert a new feedback.
            System.out.println("Operation cancelled: cannot insert a new feedback.");

            // 3. User confirms the reading of the notification.
            System.out.print("Please type 'OK' to acknowledge the notification: ");
            String confirmation = scanner.nextLine();
            if ("OK".equalsIgnoreCase(confirmation)) {
                System.out.println("Notification acknowledged.");
            } else {
                System.out.println("Confirmation not recognized, but proceeding...");
            }

            // 4. System recovers the previous state.
            // In this simulation, the previous state is simply the unchanged feedback map.
            System.out.println("System state recovered to previous state (no new feedback inserted).");

            // Exit condition: return control to user interaction.
            // This is done by returning to the main loop.
            return;
        }

        // If no feedback exists, proceed with new feedback.
        System.out.print("Enter your feedback content: ");
        String content = scanner.nextLine();

        boolean success = feedbackService.insertFeedback(userId, siteId, content);
        if (success) {
            System.out.println("Feedback submitted successfully.");
        } else {
            // This case should not happen because we already checked, but handle for safety.
            System.out.println("Unexpected error: Feedback already exists.");
        }
    }
}