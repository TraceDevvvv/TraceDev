package com.example.repository;

import com.example.entities.Comment;
import com.example.entities.Feedback;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Feedback entities.
 */
public interface FeedbackRepository {
    List<Feedback> findBySiteId(int siteId);
    Optional<Feedback> findById(int feedbackId);
    Optional<Comment> findCommentById(int commentId);
}