
package com.example.commentediting.repo;

import com.example.commentediting.domain.Comment;
import com.example.commentediting.exception.TechnicalServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Concrete implementation of the ICommentRepository interface.
 * This implementation uses an in-memory map for demonstration purposes.
 */
public class CommentRepositoryImpl implements ICommentRepository {
    // In-memory store for comments, simulating a database
    private final Map<String, Comment> comments = new HashMap<>();
    private boolean simulateTechnicalError = false; // Flag to simulate R12 errors

    /**
     * Constructs a CommentRepositoryImpl.
     * Initializes with some dummy data for testing.
     */
    public CommentRepositoryImpl() {
        // Add some dummy data
        comments.put("c1", new Comment("c1", "Great site!", "t1", "s1"));
        comments.put("c2", new Comment("c2", "Needs more info.", "t2", "s1"));
        comments.put("c3", new Comment("c3", "Loved the pictures.", "t1", "s2"));
    }

    /**
     * Sets a flag to simulate a TechnicalServiceException during repository operations.
     * This is useful for testing R12 scenarios.
     *
     * @param simulateTechnicalError true to simulate errors, false otherwise.
     */
    public void setSimulateTechnicalError(boolean simulateTechnicalError) {
        this.simulateTechnicalError = simulateTechnicalError;
    }

    @Override
    public Optional<Comment> findById(String commentId) throws TechnicalServiceException {
        if (simulateTechnicalError) {
            throw new TechnicalServiceException("Simulated database connection error during findById.");
        }
        // Simulate a delay or complex lookup
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return Optional.ofNullable(comments.get(commentId));
    }

    @Override
    public void save(Comment comment) throws TechnicalServiceException {
        if (simulateTechnicalError) {
            throw new TechnicalServiceException("Simulated database write error during save.");
        }
        if (comment == null || comment.getId() == null) {
            throw new IllegalArgumentException("Comment or comment ID cannot be null for saving.");
        }
        comments.put(comment.getId(), comment);
        // Simulate a delay
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
