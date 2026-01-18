package com.example.ui;

import com.example.dto.FeedbackDTO;
import com.example.dto.OperationResult;
import com.example.dto.UpdateCommentCommand;
import com.example.controller.EditFeedbackCommentController;
import com.example.model.Site;
import com.example.service.ErrorHandler;
import java.util.List;

/**
 * User interface for feedback operations.
 */
public class FeedbackUI {
    private EditFeedbackCommentController controller;
    private ErrorHandler errorHandler;

    // Constructor with dependency injection
    public FeedbackUI(EditFeedbackCommentController controller, ErrorHandler errorHandler) {
        this.controller = controller;
        this.errorHandler = errorHandler;
    }

    /**
     * Displays the feedback form.
     * @param feedback the feedback to display
     */
    public void displayFeedbackForm(FeedbackDTO feedback) {
        System.out.println("Displaying feedback form for feedback: " + feedback.getId());
        System.out.println("Comment: " + feedback.getComment());
    }

    /**
     * Displays the edit comment form.
     * @param feedback the feedback to edit
     */
    public void displayEditCommentForm(FeedbackDTO feedback) {
        System.out.println("Displaying edit comment form for feedback: " + feedback.getId());
        System.out.println("Current comment: " + feedback.getComment());
    }

    /**
     * Displays a confirmation message.
     * @param result the operation result
     */
    public void displayConfirmation(OperationResult result) {
        if (result.isSuccess()) {
            System.out.println("Success: " + result.getMessage());
        } else {
            System.out.println("Failure: " + result.getMessage());
        }
    }

    /**
     * Displays an error message.
     * @param errorMessage the error message
     */
    public void displayError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Displays a list of sites.
     * @param sites the list of sites
     */
    public void displaySiteList(List<Site> sites) {
        System.out.println("Available Sites:");
        for (Site site : sites) {
            System.out.println(" - " + site.getName() + " (ID: " + site.getId() + ")");
        }
    }

    /**
     * Displays feedback list.
     */
    public void displayFeedbackList(List<FeedbackDTO> feedbacks) {
        System.out.println("Feedback List:");
        for (FeedbackDTO feedback : feedbacks) {
            System.out.println(" - " + feedback.getId() + ": " + feedback.getComment());
        }
    }

    /**
     * Displays feedback in form (mapping to displayFeedbackForm).
     * @param feedback the feedback to display
     */
    public void displayFeedbackInForm(FeedbackDTO feedback) {
        System.out.println("Displaying feedback in form for feedback: " + feedback.getId());
        System.out.println("Comment: " + feedback.getComment());
    }

    /**
     * Displays edit comment form (mapping to displayEditCommentForm).
     * @param feedback the feedback to edit
     */
    public void displayEditCommentFormMsg(FeedbackDTO feedback) {
        displayEditCommentForm(feedback);
    }

    /**
     * Displays confirmation message (mapping to displayConfirmation).
     * @param result the operation result
     */
    public void displayConfirmationMessage(OperationResult result) {
        displayConfirmation(result);
    }

    /**
     * Displays error (activates Errored use case).
     * @param errorMessage the error message
     */
    public void displaysErrorActivatesErroredUseCase(String errorMessage) {
        System.out.println("Errored Use Case Activated: " + errorMessage);
        displayError(errorMessage);
    }

    // Methods to simulate user interactions from sequence diagram

    /**
     * Simulates user viewing list of sites.
     * @param userId the user id
     */
    public void viewSites(String userId) {
        List<Site> sites = controller.getSitesForUser(userId);
        displaySiteList(sites);
    }

    /**
     * Simulates user selecting feedback from list.
     * @param siteId the site id
     */
    public void selectFeedbackFromSite(String siteId) {
        List<FeedbackDTO> feedbacks = controller.loadSiteFeedback(siteId);
        System.out.println("Feedback for site " + siteId + ":");
        displayFeedbackList(feedbacks);
    }

    /**
     * Simulates user loading a specific feedback.
     * @param feedbackId the feedback id
     */
    public void loadAndDisplayFeedback(String feedbackId) {
        FeedbackDTO feedback = controller.loadFeedback(feedbackId);
        displayFeedbackForm(feedback);
    }

    /**
     * Simulates user editing a comment.
     * @param feedbackId the feedback id
     * @param newComment the new comment
     */
    public void editComment(String feedbackId, String newComment) {
        UpdateCommentCommand command = new UpdateCommentCommand(feedbackId, newComment);
        OperationResult result = controller.updateFeedbackComment(command);
        displayConfirmation(result);
        if (!result.isSuccess()) {
            errorHandler.handleValidationError(result.getMessage());
            displaysErrorActivatesErroredUseCase(result.getMessage());
        }
    }

    /**
     * Simulates cancellation.
     */
    public void cancel() {
        System.out.println("Operation cancelled. Returning to site list.");
    }

    public void ViewsListOfSitesFromSearchSite() {
        System.out.println("UI: Views list of sites (from SearchSite)");
        // Implementation would call appropriate controller method, but for traceability we have this method.
    }

    public void SelectsASite() {
        System.out.println("UI: User selects a site");
    }

    public void DisplaysFeedbackList() {
        System.out.println("UI: Displays feedback list");
    }

    public void SelectsFeedbackFromList() {
        System.out.println("UI: User selects feedback from list");
    }

    public void ActivatesChangeCommentFunction() {
        System.out.println("UI: Activates 'change comment' function");
    }

    public void DisplaysFeedbackInForm() {
        System.out.println("UI: Displays feedback in form");
    }

    public void SubmitsFormToEdit() {
        System.out.println("UI: Submits form (to edit)");
    }

    public void DisplaysEditCommentForm() {
        System.out.println("UI: Displays edit comment form");
    }

    public void EditsComment() {
        System.out.println("UI: User edits comment");
    }

    public void SubmitsEditedForm() {
        System.out.println("UI: Submits edited form");
    }

    public void DisplaysConfirmationMessage() {
        System.out.println("UI: Displays confirmation message");
    }

    public void DisplaysErrorActivatesErroredUseCase() {
        System.out.println("UI: Displays error (activates Errored use case)");
    }
}