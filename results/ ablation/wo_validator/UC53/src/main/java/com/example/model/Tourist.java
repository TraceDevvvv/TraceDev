
package com.example.model;

import com.example.controller.FeedbackSubmissionUseCaseController;
import com.example.dto.FeedbackForm;

/**
 * Represents a tourist who can submit feedback for a visited site.
 * Contains tourist's ID and the current site ID they are at.
 */
public class Tourist {
    private String touristId;
    private String currentSiteId;
    private FeedbackSubmissionUseCaseController controller;

    /**
     * Constructor for Tourist.
     * @param touristId Unique identifier for the tourist.
     * @param currentSiteId ID of the site the tourist is currently at.
     * @param controller The controller used to submit feedback.
     */
    public Tourist(String touristId, String currentSiteId, FeedbackSubmissionUseCaseController controller) {
        this.touristId = touristId;
        this.currentSiteId = currentSiteId;
        this.controller = controller;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getCurrentSiteId() {
        return currentSiteId;
    }

    public void setCurrentSiteId(String currentSiteId) {
        this.currentSiteId = currentSiteId;
    }

    /**
     * Submits feedback for the current site.
     * @param feedbackData The feedback form data filled by the tourist.
     * @return FeedbackResult indicating success or failure.
     */
    public Object submitFeedback(FeedbackForm feedbackData) {
        // Calls the controller to execute feedback submission
        return controller.executeFeedbackSubmission(touristId, currentSiteId, feedbackData);
    }

    /**
     * Activates the feedback feature (triggers the feedback submission process).
     * From sequence diagram: Tourist calls activateFeedbackFeature().
     */
    public void activateFeedbackFeature() {
        // The activation triggers the controller to get current site and check for duplicates.
        // In the sequence diagram, this method call leads to controller interactions.
        // Since the controller will handle the flow, we call a method that initiates the process.
        // Alternatively, this could trigger UI to show form; here we simulate by calling controller's internal method.
        // Assumption: This method will internally call controller's duplicate check and later display form.
        // We'll simulate by having the controller start the process via a separate method.
        // However, according to sequence diagram, Tourist -> Controller : activateFeedbackFeature()
        // So we will call a method on controller to start feedback activation.
        controller.startFeedbackActivation(touristId);
    }

    /**
     * Fills a feedback form with rating and comment.
     * @param rating Rating value (e.g., 1-5).
     * @param comment Text comment.
     * @return A FeedbackForm object with the provided data.
     */
    public FeedbackForm fillForm(int rating, String comment) {
        // Create a feedback form with tourist's current site and ID.
        FeedbackForm form = new FeedbackForm();
        form.setTouristId(touristId);
        form.setSiteId(currentSiteId);
        form.setRating(rating);
        form.setComment(comment);
        return form;
    }
}
