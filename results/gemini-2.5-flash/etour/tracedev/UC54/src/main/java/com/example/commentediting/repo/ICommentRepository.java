package com.example.commentediting.repo;

import com.example.commentediting.domain.Comment;
import com.example.commentediting.exception.TechnicalServiceException;

import java.util.Optional;

/**
 * Interface for the Comment Repository.
 * Defines the contract for data access operations related to comments.
 */
public interface ICommentRepository {
    /**
     * Finds a comment by its unique identifier.
     * Modified for R12 and to return Optional for better null handling.
     *
     * @param commentId The ID of the comment to find.
     * @return An Optional containing the Comment object if found, or an empty Optional if not found.
     * @throws TechnicalServiceException if a technical issue occurs during data access.
     */
    Optional<Comment> findById(String commentId) throws TechnicalServiceException;

    /**
     * Saves or updates a comment in the repository.
     * Modified for R12.
     *
     * @param comment The Comment object to save.
     * @throws TechnicalServiceException if a technical issue occurs during data access.
     */
    void save(Comment comment) throws TechnicalServiceException;
}