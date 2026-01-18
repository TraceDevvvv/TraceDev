package com.example.infrastructure;

import com.example.application.FeedbackRepository;
import com.example.domain.Feedback;

/**
 * Implementation of FeedbackRepository (outermost layer).
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    private final DatabaseService databaseService;

    public FeedbackRepositoryImpl(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public boolean exists(String touristId, String siteId) {
        // Simulate database query
        return databaseService.executeQuery(
                "SELECT COUNT(*) FROM feedback WHERE tourist_id='" + touristId + "' AND site_id='" + siteId + "'"
        ) > 0;
    }

    @Override
    public Feedback save(Feedback feedback) {
        // Simulate save operation
        databaseService.save(feedback);
        return feedback;
    }
}