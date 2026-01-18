package com.example.controller;

import com.example.dto.FeedbackDTO;
import com.example.dto.OperationResult;
import com.example.dto.UpdateCommentCommand;
import com.example.model.Feedback;
import com.example.model.Site;
import com.example.repository.FeedbackRepository;
import com.example.service.AuthenticationService;
import com.example.service.ErrorHandler;
import com.example.service.NotificationService;
import com.example.strategy.ValidationStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for editing feedback comments.
 */
public class EditFeedbackCommentController {
    private FeedbackRepository feedbackRepository;
    private ValidationStrategy validationStrategy;
    private AuthenticationService authenticationService;
    private NotificationService notificationService;
    private ErrorHandler errorHandler;

    // Constructor with dependency injection
    public EditFeedbackCommentController(FeedbackRepository feedbackRepository,
                                         ValidationStrategy validationStrategy,
                                         AuthenticationService authenticationService,
                                         NotificationService notificationService,
                                         ErrorHandler errorHandler) {
        this.feedbackRepository = feedbackRepository;
        this.validationStrategy = validationStrategy;
        this.authenticationService = authenticationService;
        this.notificationService = notificationService;
        this.errorHandler = errorHandler;
    }

    /**
     * Gets sites for a user (simplified: returns a list of sites).
     * @param userId the user id
     * @return list of sites
     */
    public List<Site> getSitesForUser(String userId) {
        // Verify user is logged in
        if (!authenticationService.isLoggedIn(userId)) {
            throw new SecurityException("User not logged in");
        }
        // Simplified: return hardcoded sites
        List<Site> sites = new ArrayList<>();
        sites.add(new Site("S1", "Site 1", new ArrayList<>()));
        sites.add(new Site("S2", "Site 2", new ArrayList<>()));
        return sites;
    }

    /**
     * Loads feedback for a site.
     * @param siteId the site id
     * @return list of feedback DTOs
     */
    public List<FeedbackDTO> loadSiteFeedback(String siteId) {
        try {
            List<Feedback> feedbacks = feedbackRepository.findBySiteId(siteId);
            return feedbacks.stream()
                    .map(FeedbackDTO::new)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            errorHandler.handleConnectionError();
            throw e;
        }
    }

    /**
     * Loads a specific feedback by id.
     * @param feedbackId the feedback id
     * @return the feedback DTO
     */
    public FeedbackDTO loadFeedback(String feedbackId) {
        try {
            Feedback feedback = feedbackRepository.findById(feedbackId);
            if (feedback == null) {
                throw new IllegalArgumentException("Feedback not found");
            }
            return new FeedbackDTO(feedback);
        } catch (RuntimeException e) {
            errorHandler.handleConnectionError();
            throw e;
        }
    }

    /**
     * Updates a feedback comment.
     * @param command the update command
     * @return operation result
     */
    public OperationResult updateFeedbackComment(UpdateCommentCommand command) {
        try {
            // Validate comment
            if (!validationStrategy.isValid(command.getNewComment())) {
                String errorMsg = validationStrategy.getErrorMessage();
                errorHandler.handleValidationError(errorMsg);
                return new OperationResult(false, errorMsg);
            }

            // Retrieve feedback
            Feedback feedback = feedbackRepository.findById(command.getFeedbackId());
            if (feedback == null) {
                return new OperationResult(false, "Feedback not found");
            }

            // Update comment
            feedback.updateComment(command.getNewComment());

            // Save feedback
            feedbackRepository.save(feedback);

            // Notify about change
            notificationService.notifyCommentChanged(feedback.getId());

            return new OperationResult(true, "Comment updated successfully");
        } catch (RuntimeException e) {
            errorHandler.handleConnectionError();
            return new OperationResult(false, "Connection lost");
        }
    }
}