package com.example;

import java.util.List;

/**
 * Service layer component responsible for checking existing feedback.
 * Stereotype: Service
 */
public class FeedbackNotificationService {
    private FeedbackRepository feedbackRepository;

    public FeedbackNotificationService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Checks if feedback already exists for the given site ID.
     * @param siteId the site identifier
     * @return FeedbackNotificationResult indicating existence and the first feedback if any
     */
    public FeedbackNotificationResult checkForExistingFeedback(String siteId) {
        List<Feedback> feedbackList = feedbackRepository.findBySiteId(siteId);
        if (feedbackList != null && !feedbackList.isEmpty()) {
            // Main path: feedback exists, return the first one
            return new FeedbackNotificationResult(true, feedbackList.get(0));
        } else {
            // No existing feedback
            return new FeedbackNotificationResult(false, null);
        }
    }
}