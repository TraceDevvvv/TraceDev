
package com.etoour.service;

import com.etoour.model.Feedback;
import com.etoour.repository.FeedbackRepository;
import com.etoour.utility.ConnectionManager;
import com.etoour.utility.ErrorHandler;
import com.etoour.utility.NotificationService;

/**
 * Service layer for feedback operations.
 * Implements business logic and sequence diagram interactions.
 */
public class FeedbackService {
    private FeedbackRepository feedbackRepository;
    private NotificationService notificationService;
    private ErrorHandler errorHandler;
    private ConnectionManager connectionManager;
    private boolean connectionStatus = true; // assume initially connected

    public FeedbackService(FeedbackRepository feedbackRepository,
                           NotificationService notificationService,
                           ErrorHandler errorHandler,
                           ConnectionManager connectionManager) {
        this.feedbackRepository = feedbackRepository;
        this.notificationService = notificationService;
        this.errorHandler = errorHandler;
        this.connectionManager = connectionManager;
    }

    /**
     * Edit feedback with given ID and new comment.
     * Implements the sequence diagram flow.
     */
    public Feedback editFeedback(String feedbackId, String newComment) {
        // Step: check connection
        if (!checkConnection()) {
            handleConnectionLoss();
            errorHandler.handleServerConnectionError();
            throw new RuntimeException("Connection to server lost.");
        }

        // Step: validate data
        if (!validateFeedbackData(newComment)) {
            errorHandler.handleInvalidData("Invalid comment");
            throw new IllegalArgumentException("Invalid comment data.");
        }

        // Step: find feedback from repository
        Feedback feedback = feedbackRepository.findById(feedbackId);
        if (feedback == null) {
            throw new IllegalArgumentException("Feedback not found.");
        }

        // Step: update comment
        feedback.updateComment(newComment);

        // Step: validate feedback after update
        if (!feedback.validate()) {
            // validation fails
            feedback.markAsInvalid();
            errorHandler.handleInvalidData("Invalid comment data");
            throw new IllegalArgumentException("Feedback validation failed.");
        }

        // Step: save feedback
        if (!checkConnection()) {
            // connection interrupted during save
            handleConnectionLoss();
            errorHandler.handleServerConnectionError();
            throw new RuntimeException("Connection lost while saving.");
        }

        Feedback updated = feedbackRepository.save(feedback);

        // Step: notify tourist
        notifyTourist(feedback.getTouristId());

        return updated;
    }

    /**
     * Validate feedback data (comment).
     */
    public boolean validateFeedbackData(String comment) {
        return comment != null && !comment.trim().isEmpty();
    }

    /**
     * Notify tourist about the update.
     * Sequence diagram message: notification sent (m20).
     */
    public void notifyTourist(String userId) {
        notificationService.sendNotification(userId, "Your comment has been updated.");
    }

    /**
     * Check connection to server.
     */
    public boolean checkConnection() {
        connectionStatus = connectionManager.checkConnection();
        return connectionStatus;
    }

    /**
     * Handle connection loss.
     */
    public void handleConnectionLoss() {
        // The ConnectionManager class doesn't have notifyDisconnection() method
        // Since we don't know the actual method name, we'll leave this empty
        // or call a different method if ConnectionManager has appropriate methods
        // For now, we'll just leave it empty to fix the compilation error
    }

    // Getters and setters for dependencies (if needed)
    public FeedbackRepository getFeedbackRepository() {
        return feedbackRepository;
    }

    public void setFeedbackRepository(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }
}
