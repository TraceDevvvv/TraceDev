package com.etour.feedback;

import java.util.List;

/**
 * Service layer handling feedback business logic.
 */
public class FeedbackService {
    private final FeedbackRepository repository;

    public FeedbackService(FeedbackRepository repository) {
        this.repository = repository;
    }

    /**
     * Initiates feedback process. Returns false if tourist already gave feedback for this site.
     */
    public boolean canIssueFeedback(String touristId, String siteId) {
        // Quality requirement: verify tourist hasn't already issued feedback for the site
        return !repository.hasTouristFeedbackForSite(touristId, siteId);
    }

    /**
     * Submits feedback after validation.
     * Returns a result object indicating success or failure.
     */
    public SubmitResult submitFeedback(String touristId, String siteId, int vote, String comment) {
        // Validate data before creating Feedback object
        if (touristId == null || touristId.trim().isEmpty()) {
            return SubmitResult.error("Tourist ID is required.");
        }
        if (siteId == null || siteId.trim().isEmpty()) {
            return SubmitResult.error("Site ID is required.");
        }
        if (vote < 1 || vote > 5) {
            return SubmitResult.error("Vote must be between 1 and 5.");
        }
        if (comment == null || comment.trim().isEmpty()) {
            return SubmitResult.error("Comment cannot be empty.");
        }

        try {
            Feedback feedback = new Feedback(touristId, siteId, vote, comment);
            boolean saved = repository.saveFeedback(feedback);
            if (!saved) {
                return SubmitResult.error("You have already submitted feedback for this site.");
            }
            return SubmitResult.success("Feedback submitted successfully.");
        } catch (IllegalArgumentException e) {
            return SubmitResult.error("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            // Simulate server interruption (Exit Condition)
            return SubmitResult.error("Connection to server ETOUR interrupted. Please try again.");
        }
    }

    /**
     * Retrieves all feedbacks for a tourist.
     */
    public List<Feedback> getTouristFeedbacks(String touristId) {
        return repository.getFeedbacksByTourist(touristId);
    }

    /**
     * Retrieves all visited sites.
     */
    public List<String> getVisitedSites() {
        return repository.getVisitedSites();
    }

    /**
     * Result of feedback submission.
     */
    public static class SubmitResult {
        private final boolean success;
        private final String message;

        private SubmitResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public static SubmitResult success(String message) {
            return new SubmitResult(true, message);
        }

        public static SubmitResult error(String message) {
            return new SubmitResult(false, message);
        }

        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
    }
}