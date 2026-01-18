package com.etour.usecase;

import com.etour.dto.EditCommentRequestDTO;
import com.etour.dto.CommentResultDTO;
import com.etour.domain.Comment;
import com.etour.repository.CommentRepository;
import com.etour.service.NotificationService;
import com.etour.exception.ETOURConnectionException;
import com.etour.error.ErroredUseCaseController;
import com.etour.error.ValidationResult;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Application service implementing the Edit Comment use case.
 */
public class EditCommentService implements EditCommentUseCase {
    private CommentRepository commentRepository;
    private NotificationService notificationService;
    private ErroredUseCaseController errorHandler;

    public EditCommentService(CommentRepository commentRepository,
                              NotificationService notificationService,
                              ErroredUseCaseController errorHandler) {
        this.commentRepository = commentRepository;
        this.notificationService = notificationService;
        this.errorHandler = errorHandler;
    }

    @Override
    public CommentResultDTO execute(EditCommentRequestDTO request) {
        // Step 1: Validate request DTO (as per sequence diagram REQ-FLOW-002)
        ValidationResult validationResult = request.validate();
        if (!validationResult.isValid()) {
            errorHandler.handleValidationError(validationResult);
            return CommentResultDTO.error("Validation failed", validationResult.getErrors());
        }

        // Step 2: Retrieve comment
        Optional<Comment> optionalComment;
        try {
            optionalComment = commentRepository.findById(request.getCommentId());
        } catch (Exception e) {
            // Handle connection errors or other system errors
            errorHandler.handleSystemError(e);
            return CommentResultDTO.error("System error while fetching comment: " + e.getMessage());
        }

        if (optionalComment.isEmpty()) {
            errorHandler.handleSystemError(new Exception("Comment not found"));
            return CommentResultDTO.error("Comment not found");
        }
        Comment comment = optionalComment.get();

        // Step 3: Update comment content
        comment.updateContent(request.getNewContent());

        // Step 4: Persist updated comment
        Comment updatedComment;
        try {
            updatedComment = commentRepository.update(comment);
        } catch (Exception e) {
            errorHandler.handleSystemError(e);
            return CommentResultDTO.error("System error while updating comment: " + e.getMessage());
        }

        // Step 5: Notify about the alteration (REQ-EXIT-001)
        notificationService.notifyCommentUpdated(updatedComment, request.getTouristId());

        // Step 6: Create success result
        return CommentResultDTO.success(
                updatedComment.getId(),
                updatedComment.getContent(),
                LocalDateTime.now(),
                "Comment updated successfully"
        );
    }
}