package com.example.application;

import com.example.domain.Feedback;
import com.example.domain.FeedbackFormDTO; // Keep DTO for other potential uses or for local conversion if needed.
import com.example.domain.FeedbackResponseDTO;
import com.example.infrastructure.FeedbackRepository;
import com.example.infrastructure.SiteRepository;
import java.util.Optional;

/**
 * Application Layer controller for managing feedback submission.
 * It orchestrates interactions between the presentation layer, domain layer,
 * and infrastructure layer (repositories, serv).
 */
public class FeedbackController {
    private final FeedbackRepository feedbackRepository;
    private final SiteRepository siteRepository;
    private final ValidationService validationService;

    /**
     * Constructor for FeedbackController, injecting necessary dependencies.
     * This follows the Dependency Injection principle.
     * @param feedbackRepository Repository for Feedback entities.
     * @param siteRepository Repository for Site entities (specifically for tracking visited sites).
     * @param validationService Service for validating feedback data.
     */
    public FeedbackController(FeedbackRepository feedbackRepository, SiteRepository siteRepository, ValidationService validationService) {
        this.feedbackRepository = feedbackRepository;
        this.siteRepository = siteRepository;
        this.validationService = validationService;
    }

    /**
     * Initiates the feedback submission process.
     * This method checks if feedback has already been submitted for the given site by the tourist.
     *
     * @param touristId The ID of the tourist.
     * @param siteId    The ID of the site.
     * @return A FeedbackResponseDTO indicating whether feedback was already submitted,
     *         or if the process can proceed.
     */
    public FeedbackResponseDTO initiateFeedbackSubmission(String touristId, String siteId) {
        System.out.println("\n[Controller] Initiating feedback submission for tourist '" + touristId + "' at site '" + siteId + "'.");

        // REQ-FE1 (Flow of Events: 1) and 'alt existingFeedback.isPresent()'
        // Check if the tourist has already submitted feedback for this site
        if (hasAlreadyIssuedFeedback(touristId, siteId)) {
            System.out.println("[Controller] Feedback already submitted by tourist '" + touristId + "' for site '" + siteId + "'.");
            return new FeedbackResponseDTO(false, "You have already submitted feedback for this site.");
        } else {
            System.out.println("[Controller] No existing feedback found. Proceeding to display form.");
            // In a real application, this might trigger a UI event to display the form.
            // For this console simulation, we indicate that the form can now be displayed.
            return new FeedbackResponseDTO(true, "Ready to submit feedback.");
        }
    }

    /**
     * Submits the feedback provided by the tourist for a specific site.
     * This method handles validation, persistence, and updating visited sites.
     * Aligned with sequence diagram message: submitFeedback(touristId, siteId, vote, comment).
     *
     * @param touristId    The ID of the tourist submitting the feedback.
     * @param siteId       The ID of the site.
     * @param vote         The vote/rating.
     * @param comment      The comment text.
     * @return A FeedbackResponseDTO indicating the success or failure of the submission.
     */
    public FeedbackResponseDTO submitFeedback(String touristId, String siteId, int vote, String comment) {
        System.out.println("\n[Controller] Attempting to submit feedback for tourist '" + touristId + "' for site '" + siteId + "' with vote " + vote + " and comment '" + comment + "'.");

        // Validate feedback data using ValidationService (alt !isValid)
        // Note: The sequence diagram implies FeedbackUI directly calls ValidationService.
        // However, the controller's orchestration role often involves re-validating or initiating validation.
        // For strict SD adherence, FeedbackUI will call ValidationService, and then FeedbackUI will call this method.
        // So, this controller method assumes validation *might* have already occurred, but could also initiate it.
        // To strictly match the SD and ensure consistency, we rely on the ValidationService call in FeedbackUI.
        // If validation is needed here for robustness, it would be:
        // if (!validationService.validateFeedbackForm(siteId, vote, comment)) {
        //     System.out.println("[Controller] Feedback data validation failed during re-validation.");
        //     return new FeedbackResponseDTO(false, "Invalid or insufficient data provided.");
        // }
        // For now, we trust the UI/Presentation layer to have performed the SD-specified validation call.

        Feedback newFeedback;
        try {
            // Create and persist the Feedback entity
            newFeedback = createAndPersistFeedback(touristId, siteId, vote, comment);
        } catch (RuntimeException e) {
            // REQ-EC2 (Exit Condition: Interruption of connection to server ETOUR)
            System.err.println("[Controller] Error during feedback persistence: " + e.getMessage());
            return new FeedbackResponseDTO(false, "Connection to server lost. Please try again.");
        }

        // Mark the site as visited by the tourist
        markSiteAsVisited(touristId, siteId);

        System.out.println("[Controller] Feedback submitted successfully! Feedback ID: " + newFeedback.getFeedbackId());
        // Exit Condition: Successful combination of feedback to the site
        return new FeedbackResponseDTO(true, "Feedback submitted successfully!", newFeedback.getFeedbackId());
    }

    /**
     * Checks if a tourist has already submitted feedback for a given site.
     * @param touristId The ID of the tourist.
     * @param siteId The ID of the site.
     * @return true if feedback already exists, false otherwise.
     */
    private boolean hasAlreadyIssuedFeedback(String touristId, String siteId) {
        Optional<Feedback> existingFeedback = feedbackRepository.findByTouristAndSite(touristId, siteId);
        return existingFeedback.isPresent();
    }

    /**
     * Creates a new Feedback domain object and persists it using the repository.
     * @param touristId The ID of the tourist.
     * @param siteId The ID of the site.
     * @param vote The vote/rating.
     * @param comment The comment text.
     * @return The newly created and persisted Feedback object.
     * @throws RuntimeException if the save operation fails (e.g., simulated connection error).
     */
    private Feedback createAndPersistFeedback(String touristId, String siteId, int vote, String comment) {
        // Create the Feedback domain object (using its static factory method)
        Feedback newFeedback = Feedback.create(
                touristId,
                siteId,
                vote,
                comment
        );
        System.out.println("[Controller] Created new Feedback object: " + newFeedback.getFeedbackId());

        // Persist the feedback
        Feedback savedFeedback = feedbackRepository.save(newFeedback);
        return savedFeedback;
    }

    /**
     * Records that the tourist has visited the site.
     * @param touristId The ID of the tourist.
     * @param siteId The ID of the site.
     */
    private void markSiteAsVisited(String touristId, String siteId) {
        siteRepository.addVisitedSite(touristId, siteId);
        System.out.println("[Controller] Site '" + siteId + "' marked as visited for tourist '" + touristId + "'.");
    }
}