package com.example.repository;

import com.example.model.Feedback;
import java.util.List;

/**
 * Repository interface for Feedback entities.
 */
public interface IFeedbackRepository {
    void save(Feedback feedback);
    List<Feedback> findByTouristAndSite(String touristId, String siteId);
}