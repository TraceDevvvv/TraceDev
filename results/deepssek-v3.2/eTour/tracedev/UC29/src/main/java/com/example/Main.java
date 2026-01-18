package com.example;

import com.example.exception.*;

/**
 * Main class to demonstrate the error recovery flow.
 * Simulates the user triggering recovery and runs the sequence.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Error Recovery Demo ===");

        // Setup dependencies
        DatabaseConnection dbConn = new DatabaseConnection("jdbc:test", "user");
        TagRepository repo = new DatabaseTagRepository(dbConn);
        ErrorDetector detector = new SearchTagErrorDetector(repo);
        StateManager stateManager = new CommandStateManager();
        NotificationService notifier = new NotificationService();

        // Create controller
        ErrorRecoveryController controller = new ErrorRecoveryController(detector, stateManager, notifier);

        // Simulate user triggering recovery with a tag that contains "error" (will trigger error flow)
        String searchTags = "sample error tag";
        controller.triggerRecoveryForTag(searchTags);

        // Simulate user triggering recovery with a tag that does NOT contain "error" (no error flow)
        System.out.println("\n=== Second scenario: No error condition ===");
        controller.triggerRecoveryForTag("normal tag");

        System.out.println("\n=== Demo completed ===");
    }
}