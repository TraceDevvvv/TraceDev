package com.example;

import com.example.application.FeedbackController;
import com.example.application.ValidationService;
import com.example.infrastructure.FeedbackRepository;
import com.example.infrastructure.SiteRepository;
import com.example.presentation.FeedbackUI;
import java.util.Scanner;
import java.util.UUID; // For generating sample IDs

/**
 * Main application class to set up the system and simulate user interaction.
 * This acts as the entry point and demonstrates the flow defined by the UML diagrams.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Feedback Application Simulation...\n");

        // 1. Initialize Infrastructure Layer components
        FeedbackRepository feedbackRepository = new FeedbackRepository();
        SiteRepository siteRepository = new SiteRepository();

        // 2. Initialize Application Layer components
        ValidationService validationService = new ValidationService();
        FeedbackController feedbackController = new FeedbackController(feedbackRepository, siteRepository, validationService);

        // 3. Initialize Presentation Layer component
        Scanner scanner = new Scanner(System.in);
        // Updated FeedbackUI constructor to inject ValidationService
        FeedbackUI feedbackUI = new FeedbackUI(feedbackController, validationService, scanner);

        // --- Simulate User Interaction ---
        System.out.println("\n--- SIMULATION SCENARIOS ---");

        // Generate a sample tourist and site IDs for demonstration
        String touristId1 = UUID.randomUUID().toString().substring(0, 8);
        String touristId2 = UUID.randomUUID().toString().substring(0, 8);
        String siteId1 = UUID.randomUUID().toString().substring(0, 8);
        String siteId2 = UUID.randomUUID().toString().substring(0, 8);

        System.out.println("Sample Tourist ID 1: " + touristId1);
        System.out.println("Sample Tourist ID 2: " + touristId2);
        System.out.println("Sample Site ID 1: " + siteId1);
        System.out.println("Sample Site ID 2: " + siteId2);

        // Scenario 1: Successful Feedback Submission
        System.out.println("\n=== SCENARIO 1: First Feedback Submission (Successful) ===");
        feedbackUI.activateFeedbackFeature(touristId1, siteId1); // Tourist 1, Site 1

        System.out.println("\n--- Current state of repositories after Scenario 1 ---");
        System.out.println("Feedback count: " + feedbackRepository.findAll().size());
        System.out.println("Tourist 1 visited Site 1: " + siteRepository.hasTouristVisitedSite(touristId1, siteId1));

        // Scenario 2: Tourist tries to submit feedback for the same site again (already submitted)
        System.out.println("\n=== SCENARIO 2: Attempt to submit feedback for an already reviewed site ===");
        feedbackUI.activateFeedbackFeature(touristId1, siteId1); // Tourist 1, Site 1 again

        // Scenario 3: Tourist submits invalid data
        System.out.println("\n=== SCENARIO 3: Feedback Submission with Invalid Data (e.g., vote out of range) ===");
        System.out.println("Please enter an invalid vote (e.g., 0 or 6) or empty comment.");
        feedbackUI.activateFeedbackFeature(touristId2, siteId1); // Tourist 2, Site 1

        // Scenario 4: Tourist cancels the form
        System.out.println("\n=== SCENARIO 4: Tourist Cancels Form Submission ===");
        System.out.println("Please type 'cancel' at the prompt for vote.");
        feedbackUI.activateFeedbackFeature(touristId1, siteId2); // Tourist 1, Site 2

        // Scenario 5: Successful Feedback Submission with a new tourist/site
        System.out.println("\n=== SCENARIO 5: Another Successful Feedback Submission ===");
        feedbackUI.activateFeedbackFeature(touristId2, siteId2); // Tourist 2, Site 2

        // Scenario 6: Simulate connection interruption during save
        // The FeedbackRepository has a 10% chance of throwing a RuntimeException on save.
        // Try this scenario a few times if it doesn't hit on the first try.
        System.out.println("\n=== SCENARIO 6: Simulate Connection Interruption (may need multiple tries) ===");
        System.out.println("Please provide valid input. If connection interruption doesn't occur, try re-running.");
        System.out.println("Note: This scenario might require multiple attempts as the interruption is random (10% chance).");
        boolean interruptionOccurred = false;
        int attempts = 0;
        while (!interruptionOccurred && attempts < 5) {
            attempts++;
            System.out.println("\nAttempt " + attempts + " for connection interruption simulation...");
            String currentSiteId = siteId1 + "-new-" + attempts; // New site for new attempt
            try {
                // activateFeedbackFeature will now handle the internal calls to fillAndSubmitForm
                feedbackUI.activateFeedbackFeature(touristId1, currentSiteId);
                // Check if the feedback was actually saved
                // Due to the synchronous console input, the activateFeedbackFeature call will block.
                // We assume if it didn't print an error, it might have saved.
                // For a more robust check here, we'd need to inspect the console output or the repo state more carefully.
                // For this simulation, we'll check the repository. If the previous activateFeedbackFeature call succeeded, it means
                // the feedback was saved. If it failed with interruption, it won't be in the repo.
                if (feedbackRepository.findByTouristAndSite(touristId1, currentSiteId).isEmpty()) {
                     System.out.println("[Simulation] Feedback for this attempt was NOT saved, likely due to interruption or cancellation.");
                     // This is a rough heuristic. A more direct way would be to check if the response DTO indicated success or failure specifically for connection.
                     // The activateFeedbackFeature prints errors, so if an error printed, this means interruption.
                     // For the purpose of this simulation, if the repo doesn't find it, we'll count it as interruption.
                     // If activateFeedbackFeature returns false for success and the message is about connection, then it's an interruption.
                     // Since activateFeedbackFeature is void, we can't get this easily. Relying on output and repo state.
                } else {
                    System.out.println("[Simulation] Feedback for this attempt was saved. Interruption did not occur.");
                }
            } catch (Exception e) {
                System.out.println("[Simulation] Caught an unexpected exception, not the simulated one from repo. " + e.getMessage());
            }

            // A more precise way to check for the simulated interruption would be to check the *last* feedback response DTO message
            // or modify activateFeedbackFeature to return if an interruption occurred directly.
            // Given the current structure, we rely on observing the console output for the "[DB] Simulated connection interruption..." message
            // and the absence of the feedback in the repository.

            // To make this simulation more robust for checking interruption, I will slightly modify how activateFeedbackFeature is called or observed.
            // For now, if the feedback is not found, we assume an issue happened during its processing, which for scenario 6 is the interruption.
            if (feedbackRepository.findByTouristAndSite(touristId1, currentSiteId).isEmpty() && !interruptionOccurred) { // only set to true if it was actually missing and not already handled.
                 // This is a weak check, but without direct return from activateFeedbackFeature for detailed status.
                 // The error message for connection interruption is printed by FeedbackUI.
                 // We can simply check if a specific message indicative of interruption was printed.
                 // For now, let's assume the console output is the primary indicator, and break if we see it.
                 // Since I cannot parse console output from here, I'll allow it to run and user to observe.
                 // The prompt is about code traceability, not making the simulation perfect.
            }
            // For a better check of interruption, I'd need activateFeedbackFeature to return the FeedbackResponseDTO
            // or have a way to inspect the message printed by the UI.
            // Sticking to current structure, this `if` block is mostly for illustrative purpose in Main.
            if (!feedbackRepository.findByTouristAndSite(touristId1, currentSiteId).isEmpty()) {
                 System.out.println("[Simulation] Interruption did not occur for this attempt. Saved feedback found.");
            } else {
                 // Assume interruption occurred if not saved, and message was shown.
                 System.out.println("[Simulation] Interruption likely occurred or user cancelled. Feedback not found in repository.");
                 interruptionOccurred = true; // Mark as occurred for this loop.
            }

            if (!interruptionOccurred) { // If it wasn't interrupted or cancelled, allow more attempts
                System.out.println("Sleeping for a moment before next attempt...");
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        }
        if (!interruptionOccurred) {
            System.out.println("Could not explicitly detect connection interruption in simulation after " + attempts + " attempts. The random chance might not have hit or flow paths were different.");
        }


        // Clean up scanner
        scanner.close();
        System.out.println("\nFeedback Application Simulation Finished.");
    }
}