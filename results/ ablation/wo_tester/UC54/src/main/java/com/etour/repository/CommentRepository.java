package com.etour.repository;

import com.etour.domain.Comment;
import java.util.Optional;

/**
 * Repository interface for Comment entities.
 */
public interface CommentRepository {
    /**
     * Finds a comment by its ID.
     * @param id the comment ID.
     * @return Optional containing the comment if found.
     */
    Optional<Comment> findById(String id);

    /**
     * Saves a new comment.
     * @param comment the comment to save.
     * @return the saved comment.
     */
    Comment save(Comment comment);

    /**
     * Updates an existing comment.
     * @param comment the comment to update.
     * @return the updated comment.
     */
    Comment update(Comment comment);
}