package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class to demonstrate the interaction between different components
 * as defined by the Class and Sequence Diagrams.
 * This acts as the "Agency Operator" (AO) and the application runner.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Initialize dependencies
        TagRepository tagRepository = new TagRepository();
        TagService tagService = new TagService(tagRepository);
        AuthService authService = new AuthService();
        UserNotificationService notificationService = new UserNotificationService();
        TagController tagController = new TagController(tagService, authService, notificationService);

        System.out.println("--- Scenario 1: Successful Tag Deletion ---");
        runSuccessfulDeletionScenario(tagController, tagRepository, authService);

        System.out.println("\n--- Scenario 2: Unauthenticated User ---");
        runUnauthenticatedScenario(tagController, tagRepository, authService);

        System.out.println("\n--- Scenario 3: Connection Error during getExistingTags ---");
        runConnectionErrorScenario(tagController, tagRepository, authService, "getExistingTags");

        System.out.println("\n--- Scenario 4: Connection Error during deleteSelectedTags ---");
        runConnectionErrorScenario(tagController, tagRepository, authService, "deleteSelectedTags");
    }

    /**
     * Simulates the successful flow of requesting and deleting tags.
     */
    private static void runSuccessfulDeletionScenario(TagController controller, TagRepository repository, AuthService authService) {
        // Ensure serv are in a "success" state
        repository.setSimulateConnectionError(false);
        authService.setAuthenticated(true);
        authService.setUserRole("ADMIN"); // Assuming ADMIN role can delete

        try {
            // AO -> Controller : showDeleteTagsForm()
            System.out.println("\nAO: Requesting delete tags form...");
            List<Tag> initialTags = controller.showDeleteTagsForm();
            System.out.println("AO: Received tags: " + initialTags);

            // Simulate user selection: select "Java" (ID: 1) and "UML" (ID: 4)
            List<String> tagsToDeleteIds = initialTags.stream()
                                                    .filter(tag -> tag.getName().equals("Java") || tag.getName().equals("UML"))
                                                    .map(Tag::getId)
                                                    .collect(Collectors.toList());

            if (!tagsToDeleteIds.isEmpty()) {
                // AO -> Controller : deleteSelectedTags(tagIds)
                System.out.println("\nAO: Deleting selected tags with IDs: " + tagsToDeleteIds);
                String result = controller.deleteSelectedTags(tagsToDeleteIds);
                System.out.println("AO: Deletion result: " + result);
            } else {
                System.out.println("AO: No specific tags found for deletion in this run.");
            }

            // Verify remaining tags
            System.out.println("\nAO: Re-requesting tags to confirm deletion.");
            List<Tag> remainingTags = controller.showDeleteTagsForm();
            System.out.println("AO: Remaining tags: " + remainingTags);

        } catch (ConnectionException e) {
            System.err.println("AO: Unexpected ConnectionException in successful scenario: " + e.getMessage());
        }
    }

    /**
     * Simulates an unauthenticated user attempting to access tag deletion functionality.
     */
    private static void runUnauthenticatedScenario(TagController controller, TagRepository repository, AuthService authService) {
        // Simulate unauthenticated user
        authService.setAuthenticated(false);
        repository.setSimulateConnectionError(false); // No connection error, just auth failure

        try {
            System.out.println("\nAO: Attempting to request delete tags form as unauthenticated user...");
            controller.showDeleteTagsForm(); // This should trigger the authentication check
            System.out.println("AO: showDeleteTagsForm unexpectedly succeeded.");
        } catch (ConnectionException e) {
            System.err.println("AO: showDeleteTagsForm unexpectedly threw ConnectionException in unauthenticated scenario: " + e.getMessage());
        }

        try {
            System.out.println("\nAO: Attempting to delete tags as unauthenticated user...");
            controller.deleteSelectedTags(Arrays.asList("1", "2")); // This should also trigger auth check
            System.out.println("AO: DeleteSelectedTags unexpectedly succeeded.");
        } catch (ConnectionException e) {
            System.err.println("AO: DeleteSelectedTags unexpectedly threw ConnectionException in unauthenticated scenario: " + e.getMessage());
        }
    }

    /**
     * Simulates a connection error during either getExistingTags or deleteSelectedTags.
     * @param operation "getExistingTags" or "deleteSelectedTags" to specify which operation should fail.
     */
    private static void runConnectionErrorScenario(TagController controller, TagRepository repository, AuthService authService, String operation) {
        // Ensure user is authenticated for this test
        authService.setAuthenticated(true);
        authService.setUserRole("ADMIN");

        // Set up the connection error for the repository
        repository.setSimulateConnectionError(true);

        try {
            // First, try to request the form (if the error is for getExistingTags)
            if ("getExistingTags".equalsIgnoreCase(operation)) {
                System.out.println("\nAO: Attempting to request delete tags form, expecting connection error...");
                controller.showDeleteTagsForm(); // This should throw ConnectionException
                System.out.println("AO: showDeleteTagsForm unexpectedly succeeded despite connection error simulation.");
            } else {
                // If error is for delete, first get tags successfully (turn off error briefly)
                repository.setSimulateConnectionError(false);
                List<Tag> tags = controller.showDeleteTagsForm();
                repository.setSimulateConnectionError(true); // Turn error back on for deletion
                System.out.println("AO: Received tags for deletion: " + tags);

                if (!tags.isEmpty()) {
                    List<String> tagIdsToDelete = Arrays.asList(tags.get(0).getId()); // Just pick one
                    System.out.println("\nAO: Attempting to delete tag IDs " + tagIdsToDelete + ", expecting connection error...");
                    controller.deleteSelectedTags(tagIdsToDelete); // This should throw ConnectionException
                    System.out.println("AO: DeleteSelectedTags unexpectedly succeeded despite connection error simulation.");
                } else {
                    System.out.println("AO: No tags available to attempt deletion with connection error.");
                }
            }
        } catch (ConnectionException e) {
            System.out.println("AO: Caught expected ConnectionException: " + e.getMessage());
            // The notification service would have already been called by the controller
        } finally {
            // Reset connection error simulation for next scenarios
            repository.setSimulateConnectionError(false);
        }
    }
}