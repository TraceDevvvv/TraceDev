package com.example;

/**
 * Main class to simulate the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup
        FeedbackRepository feedbackRepo = new FeedbackRepositoryImpl();
        TouristSiteRepository siteRepo = new TouristSiteRepositoryImpl();
        ValidationService validationService = new ValidationService();
        SubmitFeedbackService service = new SubmitFeedbackServiceImpl(feedbackRepo, siteRepo, validationService);
        FeedbackController controller = new FeedbackController(service);

        // Simulate tourist
        System.out.println("=== Tourist activates feedback ===");
        controller.activateFeedback();

        // Simulate tourist card validation (done internally by service)
        // Simulate site validation (done internally)

        // Display form
        controller.displayFeedbackForm();

        // Simulate tourist filling and submitting form
        FeedbackForm form = new FeedbackForm("TOURIST001", "SITE001", 5, "Great experience!");
        System.out.println("=== Tourist submits form ===");
        ResultDTO result = controller.submitFeedback(form);
        System.out.println("Result: " + result.isSuccess() + " - " + result.getMessage());

        // Test duplicate submission
        System.out.println("\n=== Duplicate submission ===");
        ResultDTO duplicateResult = controller.submitFeedback(form);
        System.out.println("Duplicate result: " + duplicateResult.isSuccess() + " - " + duplicateResult.getMessage());

        // Test invalid site
        FeedbackForm invalidSiteForm = new FeedbackForm("TOURIST001", "INVALID_SITE", 3, "Not found.");
        System.out.println("\n=== Invalid site submission ===");
        ResultDTO invalidSiteResult = controller.submitFeedback(invalidSiteForm);
        System.out.println("Invalid site result: " + invalidSiteResult.isSuccess() + " - " + invalidSiteResult.getMessage());
    }
}