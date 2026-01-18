package com.example;

import java.util.List;
import java.util.Scanner;

/**
 * Controller for managing the comment modification workflow.
 * Orchestrates interactions between views and the feedback service.
 * Assumes Agency Operator is authenticated. (REQ-001)
 * Assumes underlying serv handle network resilience. (REQ-002)
 */
public class CommentModificationController {
    public FeedbackService feedbackService;
    public SiteListView siteListView;
    public FeedbackListView feedbackListView;
    public EditCommentView editCommentView;

    // Temporary storage for the DTO during the confirmation flow
    private CommentEditDTO proposedEditDto;

    /**
     * Constructor for CommentModificationController, injecting its dependencies.
     * @param feedbackService The service for feedback operations.
     * @param siteListView The view for displaying sites.
     * @param feedbackListView The view for displaying feedback.
     * @param editCommentView The view for editing comments.
     */
    public CommentModificationController(FeedbackService feedbackService, SiteListView siteListView,
                                         FeedbackListView feedbackListView, EditCommentView editCommentView) {
        this.feedbackService = feedbackService;
        this.siteListView = siteListView;
        this.feedbackListView = feedbackListView;
        this.editCommentView = editCommentView;

        // Set controller references in views for callbacks
        this.siteListView.setController(this);
        this.feedbackListView.setController(this);
        this.editCommentView.setController(this);
    }

    /**
     * Initiates the workflow to request and display a list of sites.
     * This is the entry point for the "request site list" interaction.
     */
    public void requestSiteList() {
        System.out.println("\nController: Requesting site list...");
        List<SiteDTO> siteDTOs = feedbackService.getSites();

        if (siteDTOs == null) { // Service returned null, indicating a simulated DB error (REQ-002)
            siteListView.showError("Failed to retrieve sites. Please try again.");
            siteListView.displayError();
            return;
        }

        siteListView.displaySites(siteDTOs);
        siteListView.showSiteList();
    }

    /**
     * Handles the selection of a site by the operator.
     * @param siteId The ID of the selected site.
     */
    public void selectSite(String siteId) {
        System.out.println("Controller: Site selected: " + siteId + ". Fetching feedback...");
        List<FeedbackDTO> feedbackDTOs = feedbackService.getFeedbackForSite(siteId);

        if (feedbackDTOs == null) { // Service returned null, indicating a simulated DB error (REQ-002)
            feedbackListView.showError("Failed to retrieve feedback. Please try again.");
            feedbackListView.displayError();
            return;
        }

        feedbackListView.displayFeedback(feedbackDTOs);
        feedbackListView.showFeedbackList();
    }

    /**
     * Handles the selection of a specific feedback entry for editing.
     * @param feedbackId The ID of the selected feedback.
     */
    public void selectFeedbackForEdit(String feedbackId) {
        System.out.println("Controller: Feedback selected for edit: " + feedbackId + ". Getting details...");
        FeedbackDTO feedbackDTO = feedbackService.getFeedbackById(feedbackId);

        if (feedbackDTO == null) { // Service returned null, indicating not found or simulated DB error (REQ-002)
            editCommentView.showError("Failed to retrieve feedback details. Please try again.");
            editCommentView.displayError();
            return;
        }

        editCommentView.displayEditForm(feedbackDTO);
        editCommentView.showEditCommentForm();
    }

    /**
     * Processes the submission of an edited comment, initiating the confirmation flow.
     * @param feedbackId The ID of the feedback being edited.
     * @param newCommentText The new comment text.
     */
    public void submitEditedComment(String feedbackId, String newCommentText) {
        System.out.println("Controller: Received edited comment for feedback " + feedbackId + ": '" + newCommentText + "'");
        // Create the DTO and store it for potential confirmation
        this.proposedEditDto = new CommentEditDTO(feedbackId, newCommentText);

        editCommentView.showConfirmationPrompt(); // (REQ-FE: Flow of Events 12)
    }

    /**
     * Confirms the comment change and proceeds with the update.
     * @param editDto The CommentEditDTO containing the feedback ID and the new comment.
     * @return A status string indicating success or failure.
     */
    public String confirmCommentChange(CommentEditDTO editDto) {
        System.out.println("Controller: Operator confirmed change for Feedback ID: " + editDto.feedbackId);
        // Use the DTO from the confirmation step
        boolean success = feedbackService.updateFeedbackComment(editDto);

        if (success) {
            editCommentView.showSuccessMessage("Comment updated successfully.");
            editCommentView.displaySuccess();
            return "SUCCESS";
        } else {
            // Distinguish between validation failure and other service failures
            // For this simulation, any `false` from service indicates an issue.
            // The service already prints detailed error messages.
            editCommentView.showError("Failed to update comment. Please check inputs and try again.");
            editCommentView.displayError();
            // System activates the use case Errored (REQ-FE13)
            System.err.println("System: Use case 'Modify Comment of Feedback' resulted in an error.");
            return "FAILED";
        }
    }

    /**
     * Handles the cancellation of the comment change operation.
     * @return A status string indicating cancellation.
     */
    public String cancelCommentChange() {
        System.out.println("Controller: Operator cancelled comment modification.");
        editCommentView.showError("Comment modification cancelled."); // (Exit Condition: Operator cancels)
        editCommentView.displayError();
        this.proposedEditDto = null; // Clear the proposed DTO
        return "CANCELLED";
    }

    /**
     * Provides the currently proposed edit DTO for confirmation.
     * @return The CommentEditDTO waiting for confirmation, or null if none.
     */
    public CommentEditDTO getProposedEditDto() {
        return proposedEditDto;
    }
}