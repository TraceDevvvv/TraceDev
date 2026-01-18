
package com.etour.service;

import com.etour.entity.Comment;
import com.etour.exception.InvalidDataException;
import com.etour.exception.ServerConnectionException;
import com.etour.repository.CommentRepository;

/**
 * Service layer for comment operations.
 * Handles business logic for editing a comment.
 */
public class CommentService {
    private final CommentRepository commentRepository;
    private final ValidationService validationService;
    private final NotificationService notificationService;

    public CommentService(CommentRepository commentRepository,
                          ValidationService validationService,
                          NotificationService notificationService) {
        this.commentRepository = commentRepository;
        this.validationService = validationService;
        this.notificationService = notificationService;
    }

    /**
     * Edits an existing comment after verification and confirmation.
     * Follows the flow of events from the use case.
     *
     * @param commentId the ID of the comment to edit
     * @param newText   the new comment text
     * @param confirm   whether the tourist confirmed the change
     * @return the updated Comment entity
     * @throws InvalidDataException if data is invalid or insufficient
     * @throws ServerConnectionException if connection to server is interrupted
     */
    public Comment editComment(Long commentId, String newText, boolean confirm)
            throws InvalidDataException, ServerConnectionException {
        // Step 3: System verifies the data entered.
        if (!validationService.isValidCommentText(newText)) {
            // Quality Requirement: activate use case Errored for invalid data.
            throw new InvalidDataException("Comment text is invalid or insufficient.");
        }

        // Step 4: System asks for confirmation of the change.
        // (In this method, confirmation is passed as a parameter from the UI layer.)
        if (!confirm) {
            throw new InvalidDataException("Change not confirmed by tourist.");
        }

        // Step 6: System edits commentary on selected feedback.
        Comment comment = commentRepository.findById(commentId);
        if (comment == null) {
            throw new InvalidDataException("Comment not found.");
        }

        comment.setText(newText);
        comment.setUpdatedAt(java.time.LocalDateTime.now());

        // Simulate saving to a database; in a real app, this would be a persistent call.
        boolean success = commentRepository.update(comment);
        if (!success) {
            // Exit condition: connection to server interrupted.
            throw new ServerConnectionException("Connection to server ETOUR interrupted.");
        }

        // Exit condition: system has notified the alterations.
        notificationService.notifyCommentUpdated(comment);

        return comment;
    }
}
