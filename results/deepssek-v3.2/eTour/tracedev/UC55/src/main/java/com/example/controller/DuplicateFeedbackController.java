package com.example.controller;

import com.example.dto.*;
import com.example.model.Feedback;
import com.example.repository.FeedbackRepository;
import com.example.service.NotificationService;
import com.example.service.StateRecoveryService;
import java.util.Optional;

/**
 * Use case controller (interactor) for handling duplicate feedback notifications.
 */
public class DuplicateFeedbackController {
    private FeedbackRepository feedbackRepository;
    private NotificationService notificationService;
    private StateRecoveryService stateRecoveryService;

    public DuplicateFeedbackController(FeedbackRepository feedbackRepository,
                                       NotificationService notificationService,
                                       StateRecoveryService stateRecoveryService) {
        this.feedbackRepository = feedbackRepository;
        this.notificationService = notificationService;
        this.stateRecoveryService = stateRecoveryService;
    }

    /**
     * Handles a feedback request, checking for duplicates and notifying if found.
     * @param request the feedback request data.
     * @return a response indicating success or duplicate.
     */
    public FeedbackResponse handleFeedbackRequest(FeedbackRequest request) {
        Optional<Feedback> duplicate = checkForDuplicateFeedback(request.getSiteId(), request.getUserId());
        if (duplicate.isPresent()) {
            // Duplicate feedback exists.
            NotificationDto notification = new NotificationDto(
                request.getUserId(),
                "You have already submitted feedback for this site",
                "Please confirm you have read this notification.",
                true
            );
            // Validate notification clarity as per quality requirement.
            if (!notification.validateClarity()) {
                throw new IllegalStateException("Notification is not clear and actionable.");
            }
            notifyUser(notification);
            // Cancel feedback insertion.
            FeedbackDto existingDto = FeedbackDto.fromFeedback(duplicate.get());
            return new FeedbackResponse(false, "Duplicate feedback found.", existingDto);
        } else {
            // No duplicate, proceed.
            return new FeedbackResponse(true, "No duplicate feedback. Proceed.", null);
        }
    }

    /**
     * Checks for existing feedback for the same site and user.
     * @param siteId the site identifier.
     * @param userId the user identifier.
     * @return an Optional containing the duplicate feedback if found.
     */
    private Optional<Feedback> checkForDuplicateFeedback(String siteId, String userId) {
        return feedbackRepository.findBySiteAndUser(siteId, userId);
    }

    /**
     * Notifies the user via the notification service.
     * @param notification the notification DTO.
     */
    private void notifyUser(NotificationDto notification) {
        notificationService.sendNotification(notification.getUserId(), notification.getMessage());
    }

    /**
     * Recovers the previous state using the existing feedback.
     * @param feedback the existing feedback.
     */
    private void recoverPreviousState(Feedback feedback) {
        stateRecoveryService.restoreState(feedback);
    }

    /**
     * Confirms that the user has read the notification and triggers state recovery.
     * @param userId the user identifier.
     */
    public void confirmNotificationRead(String userId) {
        // In a full implementation, we would retrieve the existing feedback for this user.
        // For simplicity, we assume we have a method to get the last duplicate feedback.
        Optional<Feedback> duplicate = feedbackRepository.findLatestByUser(userId);
        duplicate.ifPresent(this::recoverPreviousState);
        System.out.println("State recovered for user: " + userId);
    }

    /**
     * Creates NotificationDto.
     * Corresponds to sequence diagram message m8.
     */
    public NotificationDto createNotificationDto(String userId, String message, String actionMessage, boolean isActionable) {
        return new NotificationDto(userId, message, actionMessage, isActionable);
    }

    /**
     * Return FeedbackResponse(success=false, duplicateMessage)
     * Corresponds to sequence diagram message m12.
     */
    public FeedbackResponse returnFeedbackResponse(boolean success, String message, FeedbackDto existingFeedback) {
        return new FeedbackResponse(success, message, existingFeedback);
    }

    /**
     * Return state recovery confirmation.
     * Corresponds to sequence diagram message m19.
     */
    public String returnStateRecoveryConfirmation(String userId) {
        return "State recovery confirmed for user: " + userId;
    }

    /**
     * Return FeedbackResponse(success=true, proceedMessage)
     * Corresponds to sequence diagram message m21.
     */
    public FeedbackResponse returnFeedbackResponseSuccess(String proceedMessage) {
        return new FeedbackResponse(true, proceedMessage, null);
    }
}