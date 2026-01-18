package com.example.commentediting.usecase;

import com.example.commentediting.domain.Comment;
import com.example.commentediting.output.IEditCommentOutputPort;
import com.example.commentediting.repo.ICommentRepository;
import com.example.commentediting.request.EditCommentRequest;
import com.example.commentediting.exception.TechnicalServiceException;

import java.util.Optional;

/**
 * Concrete implementation of the IEditCommentUseCase.
 * This class orchestrates the logic for editing comments, including validation,
 * confirmation, and persistence.
 */
public class EditCommentUseCase implements IEditCommentUseCase {
    private final ICommentRepository commentRepository;
    private final IEditCommentOutputPort outputPort;

    /**
     * Constructs an EditCommentUseCase with necessary dependencies.
     *
     * @param repo The repository for accessing comment data.
     * @param out The output port for presenting results and errors.
     */
    public EditCommentUseCase(ICommentRepository repo, IEditCommentOutputPort out) {
        this.commentRepository = repo;
        this.outputPort = out;
    }

    @Override
    public void execute(EditCommentRequest request) {
        // 3. System verifies the data entered.
        if (!validateCommentData(request)) {
            outputPort.presentValidationError("Invalid or insufficient data provided for editing.");
            return;
        }

        if (!request.isConfirmed()) {
            // 4. System asks for confirmation of the change.
            outputPort.presentConfirmationPrompt("Are you sure you want to change the comment to: \"" + request.getNewText() + "\"?");
        } else {
            // 7. System edits commentary on selected feedback.
            try {
                Optional<Comment> commentOptional = commentRepository.findById(request.getCommentId());

                if (commentOptional.isEmpty()) {
                    outputPort.presentError("Comment with ID " + request.getCommentId() + " not found.");
                    return;
                }

                Comment comment = commentOptional.get();

                // Check if the Tourist making the request is the owner of the comment (part of R10)
                boolean updated = comment.updateText(request.getNewText(), request.getTouristId()); // R13
                if (updated) {
                    commentRepository.save(comment);
                    outputPort.presentSuccess("Comment updated successfully.");
                } else {
                    // Tourist is not the owner (or updateText failed due to permission)
                    outputPort.presentError("You do not have permission to edit this comment (ID: " + request.getCommentId() + ")."); // R10
                }

            } catch (TechnicalServiceException e) {
                // Connection to repository interrupted (R12)
                outputPort.presentError("Connection to server interrupted: " + e.getMessage());
            } catch (Exception e) {
                // Catch any other unexpected errors
                outputPort.presentError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Validates the data in the EditCommentRequest.
     * (R13: Ensures data integrity and consistency during validation.)
     *
     * @param request The request object.
     * @return true if the data is valid, false otherwise.
     */
    private boolean validateCommentData(EditCommentRequest request) {
        // Basic validation: new text should not be empty or null
        if (request.getNewText() == null || request.getNewText().trim().isEmpty()) {
            return false;
        }
        // Ensure commentId, touristId, siteId are also present
        if (request.getCommentId() == null || request.getCommentId().trim().isEmpty() ||
            request.getTouristId() == null || request.getTouristId().trim().isEmpty() ||
            request.getSiteId() == null || request.getSiteId().trim().isEmpty()) {
            return false;
        }
        // Additional validation logic can be added here as per business rules
        return true;
    }
}