package com.agency.modifycomment.repository;

import com.agency.modifycomment.model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Repository for managing Comment objects.
 * This class simulates a data access layer for Comment entities using an in-memory map.
 * In this specific use case, comments are tightly coupled with feedback,
 * so the repository might primarily operate on comments associated with feedback IDs.
 */
public class CommentRepository {
    // Using ConcurrentHashMap to simulate a data store for thread-safe operations
    // Key: Comment ID, Value: Comment object
    private final ConcurrentHashMap<String, Comment> comments = new ConcurrentHashMap<>();

    /**
     * Constructor to pre-populate some dummy data for demonstration purposes.
     * These comments are linked to the feedback IDs created in FeedbackRepository.
     */
    public CommentRepository() {
        // Pre-populate with some sample comments
        comments.put("comm1", new Comment("comm1", "fb1", "The new search functionality is amazing!"));
        comments.put("comm2", new Comment("comm2", "fb2", "Login button is not working on mobile."));
        comments.put("comm3", new Comment("comm3", "fb3", "Add dark mode to the application."));
        comments.put("comm4", new Comment("comm4", "fb4", "Page load times are very slow."));
        comments.put("comm5", new Comment("comm5", "fb5", "The new UI is clean and intuitive."));
    }

    /**
     * Finds a comment by its ID.
     *
     * @param id The ID of the comment to find.
     * @return An Optional containing the Comment if found, or an empty Optional if not found.
     */
    public Optional<Comment> findById(String id) {
        return Optional.ofNullable(comments.get(id));
    }

    /**
     * Returns a list of all comments.
     *
     * @return A List of all Comment objects currently stored.
     */
    public List<Comment> findAll() {
        return new ArrayList<>(comments.values());
    }

    /**
     * Finds all comments associated with a specific feedback ID.
     * In this use case, each feedback has one comment, so this will return at most one comment.
     *
     * @param feedbackId The ID of the feedback to retrieve comments for.
     * @return A List of Comment objects associated with the given feedback ID.
     */
    public List<Comment> findByFeedbackId(String feedbackId) {
        return comments.values().stream()
                .filter(comment -> comment.getFeedbackId().equals(feedbackId))
                .collect(Collectors.toList());
    }

    /**
     * Saves a comment. If a comment with the same ID already exists, it will be updated.
     * Otherwise, a new comment will be added.
     *
     * @param comment The Comment object to save.
     * @return The saved Comment object.
     */
    public Comment save(Comment comment) {
        if (comment == null || comment.getId() == null) {
            throw new IllegalArgumentException("Comment and Comment ID cannot be null.");
        }
        comments.put(comment.getId(), comment);
        return comment;
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id The ID of the comment to delete.
     * @return true if the comment was deleted, false otherwise.
     */
    public boolean deleteById(String id) {
        return comments.remove(id) != null;
    }

    /**
     * Checks if a comment with the given ID exists.
     *
     * @param id The ID of the comment to check.
     * @return true if a comment with the ID exists, false otherwise.
     */
    public boolean existsById(String id) {
        return comments.containsKey(id);
    }
}