package com.example.repositories;

import com.example.entities.Comment;
import com.example.external.ETOURServerDataSource;
import java.util.Optional;

/**
 * Implementation of the CommentRepository that uses an external data source.
 */
public class CommentRepositoryImpl implements CommentRepository {
    private ETOURServerDataSource dataSource;

    /**
     * Constructs the repository with a data source.
     *
     * @param dataSource the external server data source
     */
    public CommentRepositoryImpl(ETOURServerDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Finds a comment by ID by delegating to the data source.
     * If the data source throws an exception, it is propagated.
     *
     * @param commentId the comment ID
     * @return an Optional containing the comment if found, empty otherwise
     */
    @Override
    public Optional<Comment> findById(String commentId) {
        try {
            Comment comment = dataSource.fetchComment(commentId);
            return Optional.ofNullable(comment);
        } catch (RuntimeException e) {
            // If connection fails, the exception is thrown and will be caught by the interactor
            throw e;
        }
    }

    /**
     * Saves a comment by delegating to the data source.
     * Throws a RuntimeException if the update fails (e.g., connection error).
     *
     * @param comment the comment to save
     */
    @Override
    public void save(Comment comment) {
        boolean success = dataSource.updateComment(comment);
        if (!success) {
            throw new RuntimeException("Server update failed");
        }
    }
}