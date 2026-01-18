package com.etoour.controller;

import com.etoour.model.Tourist;
import com.etoour.service.FeedbackService;
import com.etoour.utility.ErrorHandler;

/**
 * Controller for editing feedback.
 * Orchestrates between UI and service layer.
 */
public class FeedbackEditController {
    private FeedbackService feedbackService;
    private ErrorHandler errorHandler;

    public FeedbackEditController(FeedbackService feedbackService, ErrorHandler errorHandler) {
        this.feedbackService = feedbackService;
        this.errorHandler = errorHandler;
    }

    /**
     * Edit feedback with given ID and new comment.
     * Follows the sequence diagram flow.
     */
    public boolean editFeedback(String feedbackId, String newComment) {
        // Step: validate comment format
        if (!validateComment(newComment)) {
            errorHandler.handleInvalidData("Invalid comment format");
            // Return false to indicate failure
            return false;
        }

        // In the sequence diagram, confirmation is done via UI before calling controller.
        // We assume UI already got confirmation; just call service.
        // The service will handle further validation and persistence.
        try {
            feedbackService.editFeedback(feedbackId, newComment);
            return true;
        } catch (Exception e) {
            errorHandler.logError(e);
            return false;
        }
    }

    /**
     * Validate comment format.
     * Simple check: non‑null and non‑empty.
     */
    public boolean validateComment(String comment) {
        return comment != null && !comment.trim().isEmpty();
    }

    /**
     * Confirm edit after UI confirmation.
     */
    public boolean confirmEdit(String feedbackId) {
        // In our flow, this is merged with editFeedback.
        // Return true for success; in real scenario would call service.
        return true;
    }

    /**
     * Sequence diagram message: display confirmation dialog (m14) to Tourist.
     */
    public void displayConfirmationDialog(Tourist tourist, String feedbackId) {
        // In the sequence, Controller sends this message to Tourist.
        // Typically, this would be via UI.
        // We'll just log.
        System.out.println("Controller: Display confirmation dialog for feedback " + feedbackId + " to Tourist.");
    }

    /**
     * Sequence diagram message: show error message (m27) to Tourist.
     */
    public void showErrorMessage(Tourist tourist, String message) {
        System.out.println("Controller: Show error message to Tourist: " + message);
    }

    /**
     * Sequence diagram message: show validation error (m31) to Tourist.
     */
    public void showValidationError(Tourist tourist, String message) {
        System.out.println("Controller: Show validation error to Tourist: " + message);
    }
}