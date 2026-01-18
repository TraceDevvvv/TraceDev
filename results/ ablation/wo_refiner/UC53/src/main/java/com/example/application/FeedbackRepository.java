package com.example.application;

import com.example.domain.Feedback;

/**
 * Repository interface for feedback (use case / inner layer).
 */
public interface FeedbackRepository {
    boolean exists(String touristId, String siteId);
    Feedback save(Feedback feedback);
}