package com.etour.feedback;

import java.util.Scanner;

/**
 * Console application simulating the feedback use case.
 * This is a runnable program that demonstrates the flow.
 */
public class Main {
    private static final FeedbackRepository repository = new FeedbackRepository();
    private static final FeedbackService service = new FeedbackService(repository);
    private static final Scanner scanner = new Scanner(System.in);
    private static String currentTouristId = null;
    private static String currentSiteId = null;

    public static void main(String[] args) {
        System.out.println("=== ETOUR Feedback System ===\n");

        // Simulate tourist at a site
        setupTouristAndSite();

        // Flow of Events
        System.out.println("\n--- Feedback Process ---");
        System.out.println("1. Tourist activates the feature for the issue of feedback.");
        activateFeedbackFeature();

        scanner.close();
    }

    private static void setupTouristAndSite() {
        System.out.print("Enter Tourist ID: ");
        currentTouristId = scanner.nextLine().trim();
        System.out.print("Enter Site ID: ");
        currentSiteId = scanner.nextLine().trim();
        System.out.println("Tourist " + currentTouristId + " is at site " + currentSiteId + ".");
    }

    private static void activateFeedbackFeature() {
        // Step 2: System verifies that the tourist has not already issued feedback for the site.
        if (!service.canIssueFeedback(currentTouristId, currentSiteId)) {
            System.out.println("2. Verification: You have already submitted feedback for this site.");
            System.out.println("Exit: Operation cancelled.");
            return;
        }
        System.out.println("2. Verification: You can submit feedback for this site.");

        // Step 3: System displays a form.
        System.out.println("3. Displaying feedback form...");

        // Steps 4-6: Tourist fills the form.
        int vote = getVoteFromUser();
        String comment = getCommentFromUser();

        // Step 7: Tourist submits the form.
        System.out.println("7. Submitting feedback...");

        // Steps 8-12: System verifies, confirms, and remembers.
        FeedbackService.SubmitResult result = service.submitFeedback(currentTouristId, currentSiteId, vote, comment);

        // Exit Conditions
        if (result.isSuccess()) {
            System.out.println("Success: " + result.getMessage());
            System.out.println("Exit: The system has notified the successful combination of feedback to the site.");
        } else {
            System.out.println("Error: " + result.getMessage());
            if (result.getMessage().contains("already submitted")) {
                System.out.println("Exit: The Tourist has canceled the operation.");
            } else if (result.getMessage().contains("interrupted")) {
                System.out.println("Exit: An interruption of the connection to the server ETOUR has occurred.");
            } else {
                System.out.println("Exit: Operation failed due to invalid data.");
            }
        }
    }

    private static int getVoteFromUser() {
        int vote = 0;
        while (vote < 1 || vote > 5) {
            System.out.print("4-5. Enter your vote (1 to 5): ");
            try {
                vote = Integer.parseInt(scanner.nextLine().trim());
                if (vote < 1 || vote > 5) {
                    System.out.println("Vote must be between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter an integer between 1 and 5.");
            }
        }
        return vote;
    }

    private static String getCommentFromUser() {
        System.out.print("6. Enter your comment: ");
        return scanner.nextLine().trim();
    }
}