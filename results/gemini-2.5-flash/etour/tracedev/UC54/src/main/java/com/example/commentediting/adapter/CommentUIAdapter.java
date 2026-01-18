package com.example.commentediting.adapter;

import com.example.commentediting.usecase.IEditCommentUseCase;
import com.example.commentediting.output.IEditCommentOutputPort;
import com.example.commentediting.request.EditCommentRequest;
import com.example.commentediting.view.ICommentView;
import com.example.commentediting.repo.ICommentRepository;
import com.example.commentediting.domain.Comment;
import com.example.commentediting.exception.TechnicalServiceException;

import java.util.Optional;

/**
 * Adapter that connects the UI (Tourist) to the Edit Comment Use Case.
 * It also implements the IEditCommentOutputPort to receive feedback from the use case
 * and delegates to the ICommentView for displaying messages.
 */
public class CommentUIAdapter implements IEditCommentOutputPort {
    private final IEditCommentUseCase editCommentUseCase;
    private final ICommentView commentView; // Added for delegation (R4, R7, R8, R10, R11)
    private final ICommentRepository commentRepository; // Needed for selectCommentToEdit to fetch comment (R4)

    /**
     * Constructs a CommentUIAdapter.
     * Modified constructor to inject ICommentView.
     *
     * @param useCase The use case for editing comments.
     * @param view The view for displaying UI feedback.
     * @param commentRepository The repository to fetch comments for display.
     */
    public CommentUIAdapter(IEditCommentUseCase useCase, ICommentView view, ICommentRepository commentRepository) {
        this.editCommentUseCase = useCase;
        this.commentView = view;
        this.commentRepository = commentRepository;
    }

    /**
     * Handles the initial selection of a comment for editing.
     * This method fetches the comment's current text and prepares the edit form. (R4)
     *
     * @param commentId The ID of the comment to be edited.
     */
    public void selectCommentToEdit(String commentId) {
        try {
            Optional<Comment> foundCommentOptional = commentRepository.findById(commentId);
            if (foundCommentOptional.isPresent()) {
                Comment foundComment = foundCommentOptional.get();
                // Calls output port method on itself (delegated to view later)
                presentCommentEditForm(commentId, foundComment.getText());
            } else {
                presentError("Comment not found for ID: " + commentId);
            }
        } catch (TechnicalServiceException e) {
            presentError("Failed to load comment: " + e.getMessage());
        }
    }

    /**
     * Handles the tourist's request to edit a comment.
     * This typically triggers the initial validation and confirmation prompt.
     * Modified parameters to satisfy R3, R5.
     *
     * @param commentId The ID of the comment.
     * @param newText The proposed new text for the comment.
     * @param touristId The ID of the tourist making the request.
     * @param siteId The ID of the site associated with the comment.
     */
    public void handleEditRequest(String commentId, String newText, String touristId, String siteId) {
        // Create an initial request, not yet confirmed
        EditCommentRequest request = new EditCommentRequest(commentId, newText, touristId, siteId, false);
        editCommentUseCase.execute(request);
    }

    /**
     * Handles the tourist's confirmation of the comment change.
     * This typically triggers the final update logic in the use case.
     * Modified parameters to satisfy R3.
     *
     * @param commentId The ID of the comment.
     * @param newText The new text for the comment.
     * @param touristId The ID of the tourist confirming the change.
     * @param siteId The ID of the site associated with the comment.
     */
    public void confirmChange(String commentId, String newText, String touristId, String siteId) {
        // Create a confirmed request
        EditCommentRequest request = new EditCommentRequest(commentId, newText, touristId, siteId, true);
        editCommentUseCase.execute(request);
    }

    // --- IEditCommentOutputPort implementations (delegating to ICommentView) ---

    @Override
    public void presentSuccess(String message) {
        commentView.showSuccess(message); // Delegates to View (R11)
    }

    @Override
    public void presentValidationError(String errorMessage) {
        commentView.showError(errorMessage); // Delegates to View (R8)
    }

    @Override
    public void presentConfirmationPrompt(String message) {
        commentView.showConfirmationPrompt(message); // Delegates to View (R7)
    }

    @Override
    public void presentError(String errorMessage) {
        commentView.showError(errorMessage); // Delegates to View (R10)
    }

    @Override
    public void presentCommentEditForm(String commentId, String currentText) {
        commentView.showCommentEditForm(commentId, currentText); // Delegates to View (R4)
    }
}