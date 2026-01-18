package com.example;

import java.util.Scanner;

/**
 * Main application class to run the Comment Modification Use Case.
 * This class sets up the dependencies and simulates user interaction.
 */
public class MainApplication {
    public static void main(String[] args) {
        // --- Setup Dependencies ---
        FeedbackRepository feedbackRepository = new FeedbackRepository();
        SiteRepository siteRepository = new SiteRepository();
        ValidationService validationService = new ValidationService();
        NotificationService notificationService = new NotificationService();

        FeedbackService feedbackService = new FeedbackService(
                feedbackRepository,
                siteRepository,
                validationService,
                notificationService
        );

        SiteListView siteListView = new SiteListView();
        FeedbackListView feedbackListView = new FeedbackListView();
        EditCommentView editCommentView = new EditCommentView();

        CommentModificationController controller = new CommentModificationController(
                feedbackService,
                siteListView,
                feedbackListView,
                editCommentView
        );

        Scanner scanner = new Scanner(System.in);

        // --- Simulate User Workflow ---
        System.out.println("--- Starting Comment Modification Workflow ---");
        System.out.println("Precondition: Agency Operator is authenticated. (REQ-001)");
        System.out.println("Assumes underlying serv handle network resilience. (REQ-002)");

        // Step 1: Request Site List
        controller.requestSiteList();

        // Simulate user input for selecting a site
        System.out.print("Enter site ID to view feedback (e.g., s1, s2): ");
        String selectedSiteId = scanner.nextLine();
        siteListView.selectSite(selectedSiteId); // Simulate user action

        // Step 2: Select Feedback for Edit
        System.out.print("Enter feedback ID to edit comment (e.g., f1, f3): ");
        String selectedFeedbackId = scanner.nextLine();
        feedbackListView.selectFeedback(selectedFeedbackId); // Simulate user action

        // Step 3: Enter and Submit New Comment
        System.out.print("Enter new comment text (or type 'cancel' to abort): ");
        String newComment = scanner.nextLine();
        if ("cancel".equalsIgnoreCase(newComment)) {
            editCommentView.cancelOperation(); // Simulate user cancelling at input stage
            System.out.println("--- Workflow cancelled by user ---");
            scanner.close();
            return;
        }

        editCommentView.enterNewComment(newComment);
        editCommentView.submitForm(); // Simulate user action, which triggers confirmation prompt

        // Step 4: Confirmation Prompt
        System.out.print("Confirm change? (yes/no): ");
        String confirmation = scanner.nextLine();

        if ("yes".equalsIgnoreCase(confirmation)) {
            // Retrieve the DTO that the controller prepared for confirmation
            CommentEditDTO dtoToConfirm = controller.getProposedEditDto();
            if (dtoToConfirm != null) {
                editCommentView.confirmChange(); // Simulate user action, which calls controller.confirmCommentChange
            } else {
                System.err.println("Error: No pending changes to confirm.");
            }
        } else { // "no" or anything else
            editCommentView.cancelOperation(); // Simulate user cancelling (Flow of Events 14 alt path)
        }

        System.out.println("\n--- Workflow Completed ---");

        // --- Demonstrate Error Handling (REQ-002) and Auditing (REQ-004) ---
        System.out.println("\n--- Demonstrating REQ-002 (Network Resilience/Error Handling) ---");
        System.out.println("Simulating DB connection errors...");
        feedbackRepository.setSimulateDbError(true);
        siteRepository.setSimulateDbError(true);

        System.out.println("\nAttempting to get sites with simulated DB error:");
        controller.requestSiteList(); // This should trigger the error path

        System.out.println("\nAttempting to get feedback for a site with simulated DB error:");
        siteListView.selectSite("s1"); // This should trigger error path in feedback service

        System.out.println("\nAttempting to update a comment with simulated DB error during save:");
        feedbackRepository.setSimulateDbError(false); // Can find feedback but fail on save
        siteRepository.setSimulateDbError(false);
        // First, successfully retrieve feedback
        controller.selectFeedbackForEdit("f1");
        // Then, set DB error for save
        feedbackRepository.setSimulateDbError(true);
        newComment = "Attempt to update during DB error";
        editCommentView.enterNewComment(newComment);
        editCommentView.submitForm(); // Triggers confirmation
        System.out.print("Confirm change during simulated DB error? (yes/no): ");
        confirmation = scanner.nextLine();
        if ("yes".equalsIgnoreCase(confirmation)) {
             CommentEditDTO dtoToConfirm = controller.getProposedEditDto();
             if (dtoToConfirm != null) {
                editCommentView.confirmChange(); // This will fail due to save error
             }
        } else {
            editCommentView.cancelOperation();
        }

        // Reset error simulation
        feedbackRepository.setSimulateDbError(false);
        siteRepository.setSimulateDbError(false);

        // Demonstrate REQ-004 (Auditing) with a successful update
        System.out.println("\n--- Demonstrating REQ-004 (Integrity and Persistence / Auditing) ---");
        System.out.println("Attempting a successful update and checking auditing fields.");

        // Re-request site list
        controller.requestSiteList();
        siteListView.selectSite("s1");
        feedbackListView.selectFeedback("f2"); // Select feedback f2
        newComment = "Updated comment for f2. This should show auditing.";
        editCommentView.enterNewComment(newComment);
        editCommentView.submitForm();
        System.out.print("Confirm change for auditing demo? (yes/no): ");
        confirmation = scanner.nextLine();
        if ("yes".equalsIgnoreCase(confirmation)) {
            CommentEditDTO dtoToConfirm = controller.getProposedEditDto();
            if (dtoToConfirm != null) {
                editCommentView.confirmChange(); // This will succeed
                System.out.println("\nRetrieving Feedback f2 to check auditing data:");
                Feedback updatedF2 = feedbackRepository.findById("f2");
                if (updatedF2 != null) {
                    System.out.println("Feedback ID: " + updatedF2.getId());
                    System.out.println("New Comment: " + updatedF2.getComment());
                    System.out.println("Last Modified By: " + updatedF2.getLastModifiedBy());
                    System.out.println("Last Modified Date: " + updatedF2.getLastModifiedDate());
                }
            }
        } else {
            editCommentView.cancelOperation();
        }


        scanner.close();
        System.out.println("\nApplication shutdown.");
    }
}