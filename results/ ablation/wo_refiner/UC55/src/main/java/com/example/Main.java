package com.example;

/**
 * Main class to run the application and simulate the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Feedback Already Released Use Case Simulation ===\n");

        // Setup dependencies as per class diagram
        FeedbackRepository repository = new InMemoryFeedbackRepository();
        FeedbackNotificationService service = new FeedbackNotificationService(repository);
        FeedbackAlreadyReleasedController controller = new FeedbackAlreadyReleasedController(service);

        // Simulate User -> Controller: handleRequest(siteId)
        // Precondition: User has already submitted feedback for the site (SITE001 has sample data)
        String siteId = "SITE001";
        System.out.println("User initiates request for site: " + siteId);
        FeedbackNotificationDTO dto = controller.handleRequest(siteId);

        // Simulate User reading notification
        System.out.println("\nNotification received:");
        System.out.println("Message: " + dto.getMessage());
        System.out.println("Operation Status: " + dto.getOperationStatus());
        System.out.println("\nUser acknowledges notification.");
        System.out.println("System recovers previous state (returns control).");
        System.out.println("Exit: Control returned to user.");
        System.out.println("\n=== End Simulation ===");
    }
}