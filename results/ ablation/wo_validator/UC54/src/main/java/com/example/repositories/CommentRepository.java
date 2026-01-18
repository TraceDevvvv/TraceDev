package com.example.repositories;

import com.example.entities.Comment;
import java.util.Optional;

/**
 * Repository interface for Comment entities.
 * Part of the interface adapters layer.
 */
public interface CommentRepository {
    /**
     * Finds a comment by its ID.
     *
     * @param commentId the comment ID
     * @return an Optional containing the comment if found, empty otherwise
     */
    Optional<Comment> findById(String commentId);

    /**
     * Saves (updates) a comment.
     *
     * @param comment the comment to save
     */
    void save(Comment comment);
}