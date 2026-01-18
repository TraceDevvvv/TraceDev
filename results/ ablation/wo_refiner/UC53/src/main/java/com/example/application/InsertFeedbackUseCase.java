package com.example.application;

import com.example.domain.Feedback;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Use case for inserting feedback.
 * Implements the main flow and handles validation, persistence, and notification.
 */
public class InsertFeedbackUseCase {
    private final FeedbackRepository feedbackRepository;
    private final VisitedSiteRepository visitedSiteRepository;
    private final NotificationService notificationService;

    public InsertFeedbackUseCase(FeedbackRepository feedbackRepository,
                                 VisitedSiteRepository visitedSiteRepository,
                                 NotificationService notificationService) {
        this.feedbackRepository = feedbackRepository;
        this.visitedSiteRepository = visitedSiteRepository;
        this.notificationService = notificationService;
    }

    /**
     * Executes the insert feedback use case.
     * Sequence diagram steps: validate, check existence, save feedback, add visited site, notify.
     */
    public InsertFeedbackResult execute(FeedbackData feedbackData, TouristData touristData) {
        String touristId = touristData.getTouristId();
        String siteId = feedbackData.getSiteId();

        // Step: Check if feedback already exists (requirement 7)
        if (feedbackRepository.exists(touristId, siteId)) {
            return InsertFeedbackResult.FEEDBACK_ALREADY_EXISTS;
        }

        // Step: Create Feedback domain object with timestamp
        Feedback feedback = new Feedback(
                UUID.randomUUID().toString(),
                touristId,
                siteId,
                feedbackData.getVote(),
                feedbackData.getComment(),
                LocalDateTime.now()
        );

        // Step: Validate vote and comment using domain validation
        if (!feedback.validateData()) {
            return InsertFeedbackResult.ERROR_INVALID_DATA;
        }

        // Step: Confirm feedback processing
        confirmFeedbackProcessing();

        // Step: Save feedback (simulate possible server interruption)
        try {
            feedbackRepository.save(feedback);
        } catch (Exception e) {
            // Simulate server interruption (requirement 14)
            return InsertFeedbackResult.SERVER_INTERRUPTION;
        }

        // Step: Add to visited sites list (requirement 11)
        visitedSiteRepository.add(touristId, siteId);

        // Step: Notify success (requirement 12)
        notificationService.notifySuccess(touristId, siteId);

        return InsertFeedbackResult.SUCCESS;
    }

    private void confirmFeedbackProcessing() {
        // Step: Confirm feedback processing
        System.out.println("Confirm feedback processing");
    }
}