package com.example.exception;

import java.util.Map;

/**
 * Controller class that orchestrates the error recovery flow as per the sequence diagram.
 * Implements recovery within 2 seconds as per quality requirement.
 */
public class ErrorRecoveryController {
    private ErrorDetector errorDetector;
    private StateManager stateManager;
    private NotificationService notificationService;

    /**
     * Constructor with dependencies injected.
     * @param errorDetector The error detector.
     * @param stateManager The state manager.
     * @param notificationService The notification service.
     */
    public ErrorRecoveryController(ErrorDetector errorDetector,
                                   StateManager stateManager,
                                   NotificationService notificationService) {
        this.errorDetector = errorDetector;
        this.stateManager = stateManager;
        this.notificationService = notificationService;
    }

    /**
     * Entry point for recovery triggered by user. Called from main or test.
     * @param searchTags The search tags string.
     */
    public void triggerRecoveryForTag(String searchTags) {
        System.out.println("User triggers recovery for tag: " + searchTags);
        executeRecoveryFlow(searchTags);
    }

    /**
     * Main recovery flow as per sequence diagram.
     * @param searchTags The search tags string.
     */
    public void executeRecoveryFlow(String searchTags) {
        System.out.println("Executing recovery flow...");
        boolean isError = errorDetector.isErrorCondition(searchTags);
        if (isError) {
            // Error Condition Detected branch
            notificationService.showErrorMessage("Tag already exists");
            boolean userConfirmed = notificationService.requestConfirmation("Read error?");
            if (userConfirmed) {
                notificationService.confirmNotificationRead();
                State previousState = findPreviousState(searchTags);
                if (previousState != null) {
                    stateManager.restorePreviousState(previousState);
                } else {
                    System.err.println("Previous state not found for tags: " + searchTags);
                }
            }
        } else {
            // No Error Condition branch
            System.out.println("No recovery needed");
        }
        finalizeAndReturnControl();
    }

    /**
     * Helper method to find previous state via repository (simulated here).
     * In a real scenario, this would be done via TagRepository.
     * @param searchTags The search tags.
     * @return The previous State, or null if not found.
     */
    private State findPreviousState(String searchTags) {
        // In a full implementation, we would inject TagRepository and call findPreviousState
        // For simplicity, we create a mock state.
        System.out.println("Finding previous state for tags: " + searchTags);
        State state = new State();
        state.setData(Map.of("previousTag", searchTags, "timestamp", System.currentTimeMillis()));
        return state;
    }

    /**
     * Finalize recovery and return control to user interaction.
     */
    public void finalizeAndReturnControl() {
        System.out.println("Recovery finalized. Returning control to user.");
        // Perform any cleanup or finalization if needed.
    }
}