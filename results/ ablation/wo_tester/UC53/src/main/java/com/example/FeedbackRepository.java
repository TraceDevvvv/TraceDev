package com.example;

import java.util.List;

/**
 * Repository interface for feedback persistence operations.
 */
public interface FeedbackRepository {
    boolean save(FeedbackDTO feedback);
    boolean existsByTouristAndSite(String touristId, String siteId);
    List<FeedbackDTO> findByTouristId(String touristId);
}