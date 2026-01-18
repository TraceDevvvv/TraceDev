package com.example;

/**
 * Controller handling user requests for feedback already released scenario.
 * Stereotype: Controller
 */
public class FeedbackAlreadyReleasedController {
    private FeedbackNotificationService service;

    // Constructor with dependency injection
    public FeedbackAlreadyReleasedController(FeedbackNotificationService service) {
        this.service = service;
    }

    /**
     * Handles user request for a given site ID.
     * Implements the flow from the sequence diagram.
     * @param siteId the site identifier
     * @return FeedbackNotificationDTO containing notification details
     */
    public FeedbackNotificationDTO handleRequest(String siteId) {
        // Step from sequence: Controller -> Service: checkForExistingFeedback(siteId)
        FeedbackNotificationResult result = service.checkForExistingFeedback(siteId);

        // Notification that the user has already issued feedback (REQ-006)
        String message = "Feedback already exists";
        String operationStatus = "CANCELLED";

        // Additional logic based on whether feedback exists
        if (result.hasExistingFeedback()) {
            Feedback existing = result.getExistingFeedback();
            // Could log or use existing feedback details, but DTO message remains as per diagram
            System.out.println("Existing feedback found for site " + siteId + " with id " + existing.getId());
        } else {
            // This path not shown in sequence diagram, but we keep default message.
            message = "No existing feedback";
            operationStatus = "PROCEED";
        }

        // Step: Controller -> DTO: __construct__("Feedback already exists", "CANCELLED")
        FeedbackNotificationDTO notificationDTO = new FeedbackNotificationDTO(message, operationStatus);

        // Step: Controller --> User: notificationDTO
        return notificationDTO;
    }
}