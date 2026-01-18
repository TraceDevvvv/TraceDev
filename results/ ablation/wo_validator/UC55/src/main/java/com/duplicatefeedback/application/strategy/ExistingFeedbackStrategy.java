package com.duplicatefeedback.application.strategy;

import com.duplicatefeedback.domain.model.Feedback;
import com.duplicatefeedback.application.ports.out.FeedbackRepository;

/**
 * Concrete strategy that checks for existing feedback in the repository.
 */
public class ExistingFeedbackStrategy implements DuplicateDetectionStrategy {
    private final FeedbackRepository feedbackRepository;

    public ExistingFeedbackStrategy(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public boolean isDuplicate(Feedback feedback) {
        // Delegates to checkForDuplicate using feedback's siteId and userId
        return checkForDuplicate(feedback.getSiteId(), feedback.getUserId());
    }

    @Override
    public boolean checkForDuplicate(String siteId, String userId) {
        // Calls repository to check existence
        return feedbackRepository.existsBySiteIdAndUserId(siteId, userId);
    }
}