package com.example.repository;

import com.example.model.Feedback;
import java.util.List;

/**
 * Repository interface for Feedback entities.
 */
public interface FeedbackRepository {
    List<Feedback> findBySiteId(String siteId);
    Feedback findById(String feedbackId);
    void save(Feedback feedback);
}