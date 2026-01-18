package com.example.usecases;

import com.example.entities.Comment;
import com.example.dtos.EditCommentRequestDTO;
import com.example.dtos.EditCommentResponseDTO;
import com.example.repositories.CommentRepository;
import com.example.validators.CommentValidator;
import java.util.List;
import java.util.Optional;

/**
 * Interactor (use case implementation) for editing a comment.
 * Implements the input boundary and orchestrates the use case steps.
 */
public class EditCommentInteractor implements EditCommentInputBoundary {
    private CommentRepository commentRepository;
    private CommentValidator validator;
    private EditCommentOutputBoundary presenter;

    /**
     * Constructs the interactor with its dependencies.
     *
     * @param repo the comment repository
     * @param validator the validator for comment data
     * @param presenter the output boundary (presenter)
     */
    public EditCommentInteractor(CommentRepository repo, CommentValidator validator, EditCommentOutputBoundary presenter) {
        this.commentRepository = repo;
        this.validator = validator;
        this.presenter = presenter;
    }

    /**
     * Executes the edit comment use case as per the sequence diagram.
     * Steps: validation, fetch comment, edit content, save, and present result.
     *
     * @param request the request containing edit details
     * @return the response DTO (though the presenter is used for output)
     */
    @Override
    public EditCommentResponseDTO execute(EditCommentRequestDTO request) {
        // Step 1: Data Validation
        List<String> validationErrors = validator.validate(
                request.getCommentId(),
                request.getTouristId(),
                request.getSiteId(),
                request.getNewContent()
        );
        if (!validationErrors.isEmpty()) {
            presenter.presentValidationError(validationErrors);
            // Return a failure response DTO (though presenter already handled error)
            return new EditCommentResponseDTO(
                    request.getCommentId(),
                    request.getTouristId(),
                    request.getSiteId(),
                    null,
                    false,
                    "Validation failed"
            );
        }

        // Step 2: Fetch Comment
        Optional<Comment> optionalComment = commentRepository.findById(request.getCommentId());
        if (optionalComment.isEmpty()) {
            presenter.presentValidationError(List.of("Comment not found"));
            return new EditCommentResponseDTO(
                    request.getCommentId(),
                    request.getTouristId(),
                    request.getSiteId(),
                    null,
                    false,
                    "Comment not found"
            );
        }
        Comment comment = optionalComment.get();

        // Step 3: Edit Content (and Step 4: Tourist confirmation is assumed to have happened before this call)
        comment.editContent(request.getNewContent());

        // Step 5: Save the updated comment
        try {
            commentRepository.save(comment);
        } catch (RuntimeException e) {
            // Assume this is a server connection error as per sequence diagram
            presenter.presentServerConnectionError();
            return new EditCommentResponseDTO(
                    request.getCommentId(),
                    request.getTouristId(),
                    request.getSiteId(),
                    null,
                    false,
                    "Server connection error"
            );
        }

        // Step 6: Confirmation & Response
        EditCommentResponseDTO response = new EditCommentResponseDTO(
                comment.getId(),
                comment.getTouristId(),
                comment.getSiteId(),
                comment.getContent(),
                true,
                "Comment updated successfully"
        );
        presenter.present(response);
        return response;
    }
}