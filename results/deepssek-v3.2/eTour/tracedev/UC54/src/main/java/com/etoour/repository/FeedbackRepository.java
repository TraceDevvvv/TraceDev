package com.etoour.repository;

import com.etoour.model.Feedback;

/**
 * Repository interface for Feedback persistence.
 */
public interface FeedbackRepository {
    Feedback findById(String feedbackId);
    Feedback save(Feedback feedback);
    boolean existsById(String feedbackId);
}