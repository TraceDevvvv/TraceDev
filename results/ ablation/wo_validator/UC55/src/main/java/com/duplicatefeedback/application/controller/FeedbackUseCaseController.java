package com.duplicatefeedback.application.controller;

import com.duplicatefeedback.application.strategy.DuplicateDetectionStrategy;
import com.duplicatefeedback.application.strategy.UserNotificationStrategy;
import com.duplicatefeedback.application.ports.out.FeedbackRepository;
import com.duplicatefeedback.domain.model.Feedback;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Use Case Controller that orchestrates the feedback submission flow.
 * Implements the business rules and coordinates strategies.
 */
public class FeedbackUseCaseController {
    private final DuplicateDetectionStrategy duplicateDetectionStrategy;
    private final UserNotificationStrategy userNotificationStrategy;
    private final FeedbackRepository feedbackRepository;

    public FeedbackUseCaseController(DuplicateDetectionStrategy duplicateDetectionStrategy,
                                     UserNotificationStrategy userNotificationStrategy,
                                     FeedbackRepository feedbackRepository) {
        this.duplicateDetectionStrategy = duplicateDetectionStrategy;
        this.userNotificationStrategy = userNotificationStrategy;
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Executes the feedback submission use case.
     * @param userId the user identifier
     * @param siteId the site identifier
     * @param feedbackContent the content of the feedback
     * @return the result of the use case execution
     */
    public UseCaseResult execute(String userId, String siteId, String feedbackContent) {
        // Step 1: Check for duplicate feedback using the strategy
        boolean duplicateExists = duplicateDetectionStrategy.checkForDuplicate(siteId, userId);

        if (duplicateExists) {
            // Duplicate feedback detected - alternative flow
            return handleDuplicateFeedback(userId, siteId);
        } else {
            // No duplicate - normal flow (process new feedback)
            return processNewFeedback(userId, siteId, feedbackContent);
        }
    }

    /**
     * Handles the scenario when duplicate feedback is detected.
     * @param userId the user identifier
     * @param siteId the site identifier
     * @return a UseCaseResult indicating failure
     */
    public UseCaseResult handleDuplicateFeedback(String userId, String siteId) {
        // Business rule: prevent duplicate submission
        // Notify the user
        userNotificationStrategy.deliverNotification(userId, "Feedback already exists");

        // Cancel the new feedback insertion operation
        cancelOperation();

        // Return failure result
        UseCaseResult result = new UseCaseResult(false, "Feedback exists", null);

        // Recover previous state (rollback any changes if any)
        recoverPreviousState();

        return result;
    }

    /**
     * Cancels the current operation (e.g., rolls back any temporary changes).
     */
    public void cancelOperation() {
        // In a real implementation, this would rollback transactions, clear caches, etc.
        // For now, we just log or do nothing as a placeholder.
        System.out.println("Operation canceled due to duplicate feedback.");
    }

    /**
     * Recovers the previous system state (e.g., after cancellation).
     */
    public void recoverPreviousState() {
        // In a real implementation, this might restore session state, etc.
        // For now, we just log or do nothing as a placeholder.
        System.out.println("Previous state recovered.");
    }

    /**
     * Processes new feedback when no duplicate is found.
     * @param userId the user identifier
     * @param siteId the site identifier
     * @param feedbackContent the content
     * @return a UseCaseResult indicating success
     */
    private UseCaseResult processNewFeedback(String userId, String siteId, String feedbackContent) {
        // Create a new Feedback entity
        Feedback newFeedback = new Feedback(
                UUID.randomUUID().toString(),
                siteId,
                userId,
                feedbackContent,
                LocalDateTime.now()
        );

        // Save to repository
        feedbackRepository.save(newFeedback);

        // Return success result
        return new UseCaseResult(true, "Feedback submitted", newFeedback);
    }
}