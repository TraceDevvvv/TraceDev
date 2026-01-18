package com.agency.modifycomment.service;

import com.agency.modifycomment.model.Comment;
import com.agency.modifycomment.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing business logic related to Comment objects.
 * It acts as an intermediary between the controller and the repository,
 * providing methods for comment-related operations, especially for modification.
 */
public class CommentService {
    private final CommentRepository commentRepository;

    /**
     * Constructs a new CommentService with the given CommentRepository.
     *
     * @param commentRepository The repository for accessing comment data.
     */
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Retrieves a list of all available comments.
     *
     * @return A List of Comment objects.
     */
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    /**
     * Finds a comment by its unique identifier.
     *
     * @param commentId The ID of the comment to find.
     * @return An Optional containing the Comment if found, or an empty Optional if not found.
     */
    public Optional<Comment> getCommentById(String commentId) {
        return commentRepository.findById(commentId);
    }

    /**
     * Retrieves a list of comments associated with a specific feedback ID.
     * In this use case, each feedback typically has one comment.
     *
     * @param feedbackId The ID of the feedback to retrieve comments for.
     * @return A List of Comment objects associated with the given feedback ID.
     */
    public List<Comment> getCommentsByFeedbackId(String feedbackId) {
        return commentRepository.findByFeedbackId(feedbackId);
    }

    /**
     * Adds a new comment to the system.
     *
     * @param comment The Comment object to add.
     * @return The added Comment object.
     * @throws IllegalArgumentException if the comment or comment ID is null.
     */
    public Comment addComment(Comment comment) {
        if (comment == null || comment.getId() == null) {
            throw new IllegalArgumentException("Comment and Comment ID cannot be null.");
        }
        return commentRepository.save(comment);
    }

    /**
     * Updates an existing comment. This is the core functionality for the ModifyComment use case.
     *
     * @param updatedComment The Comment object with updated information (specifically, the new text).
     * @return The updated Comment object.
     * @throws IllegalArgumentException if the comment or comment ID is null, or if the comment does not exist.
     */
    public Comment updateComment(Comment updatedComment) {
        if (updatedComment == null || updatedComment.getId() == null) {
            throw new IllegalArgumentException("Updated comment and comment ID cannot be null.");
        }
        
        // Retrieve the existing comment to ensure it exists
        Optional<Comment> existingCommentOptional = commentRepository.findById(updatedComment.getId());
        if (existingCommentOptional.isEmpty()) {
            throw new IllegalArgumentException("Comment with ID " + updatedComment.getId() + " does not exist.");
        }

        // Update the text of the existing comment
        Comment existingComment = existingCommentOptional.get();
        existingComment.setText(updatedComment.getText());
        
        // Save the updated comment
        return commentRepository.save(existingComment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param commentId The ID of the comment to delete.
     * @return true if the comment was successfully deleted, false otherwise.
     */
    public boolean deleteComment(String commentId) {
        return commentRepository.deleteById(commentId);
    }

    /**
     * Validates the provided comment text.
     *
     * @param commentText The text to validate.
     * @return true if the comment text is valid (not null or empty), false otherwise.
     */
    public boolean isValidCommentText(String commentText) {
        return commentText != null && !commentText.trim().isEmpty();
    }
}