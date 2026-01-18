package com.example.repository;

import com.example.model.Feedback;
import com.example.model.Site;
import java.util.List;

/**
 * Interface for Feedback data access operations.
 */
public interface FeedbackRepository {
    Feedback findById(int id);
    void save(Feedback feedback);
    List<Feedback> findBySite(Site site);
}