package com.feedback.management;

import java.util.List;

public class CheckDuplicateFeedbackUseCase {
    private FeedbackRepository feedbackRepository;

    public CheckDuplicateFeedbackUseCase(FeedbackRepository repository) {
        this.feedbackRepository = repository;
    }

    public CheckDuplicateResponse execute(CheckDuplicateRequest request) {
        String siteId = request.getSiteId();
        String userId = request.getUserId();
        List<Feedback> feedbackList = feedbackRepository.findBySiteAndUser(siteId, userId);

        if (feedbackList != null && !feedbackList.isEmpty()) {
            Feedback existingFeedback = feedbackList.get(0);
            return new CheckDuplicateResponse(true, existingFeedback);
        } else {
            return new CheckDuplicateResponse(false, null);
        }
    }
}