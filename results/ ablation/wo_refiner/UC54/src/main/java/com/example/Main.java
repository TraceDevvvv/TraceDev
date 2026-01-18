package com.example;

/**
 * Main class to demonstrate the runnable system.
 * Simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Create dependencies
        CommentRepositoryImpl repository = new CommentRepositoryImpl();
        NotificationService notifier = new NotificationService();
        ETOURService etourService = new ETOURService();
        ErroredUseCaseController erroredUC = new ErroredUseCaseController();
        ErrorHandler errorHandler = new ErrorHandler(etourService, erroredUC);

        // Create controller
        ModifyCommentController controller = new ModifyCommentController(
                repository, notifier, errorHandler, etourService, erroredUC);

        // Create a sample comment and save it
        Comment comment = new Comment("comment-123", "tourist-456", "site-789", "Initial comment");
        repository.save(comment);

        // Create tourist
        Tourist tourist = new Tourist("tourist-456", "John Doe");

        // Simulate the sequence diagram flow

        // Precondition: tourist viewing site details (REQ-004)
        System.out.println("\n=== Precondition: Tourist viewing Site Details ===");

        // Tourist chooses to modify comment (REQ-005)
        tourist.chooseToModifyComment("comment-123");
        controller.displayCommentEditForm();

        // Tourist provides updated data (simulate via DTO)
        CommentDTO updatedData = new CommentDTO("comment-123", "Updated comment text");

        // Controller handles modification
        System.out.println("\n=== Main Flow ===");
        controller.handleModifyComment("comment-123", updatedData);

        // Simulate interruption of connection to ETOUR Server (alternative flow)
        System.out.println("\n=== Alternative Flow: Server Interruption ===");
        etourService.setConnected(false);
        controller.requestDataFromServer();

        System.out.println("\n=== End of simulation ===");
    }
}