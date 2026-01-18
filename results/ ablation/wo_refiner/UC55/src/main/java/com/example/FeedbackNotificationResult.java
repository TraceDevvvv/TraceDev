package com.example;

/**
 * Value object representing the result of checking for existing feedback.
 */
public class FeedbackNotificationResult {
    private boolean hasExistingFeedback;
    private Feedback existingFeedback;

    /**
     * Constructs a result object.
     * @param hasExistingFeedback true if feedback exists for the site
     * @param existingFeedback the first feedback found (can be null if none)
     */
    public FeedbackNotificationResult(boolean hasExistingFeedback, Feedback existingFeedback) {
        this.hasExistingFeedback = hasExistingFeedback;
        this.existingFeedback = existingFeedback;
    }

    public boolean hasExistingFeedback() {
        return hasExistingFeedback;
    }

    public Feedback getExistingFeedback() {
        return existingFeedback;
    }
}