package com.duplicatefeedback.application.ports.out;

import com.duplicatefeedback.domain.model.Feedback;
import java.util.Optional;

/**
 * Output port for feedback persistence.
 * Part of the interface adapters layer.
 */
public interface FeedbackRepository {
    void save(Feedback feedback);
    Optional<Feedback> findBySiteIdAndUserId(String siteId, String userId);
    boolean existsBySiteIdAndUserId(String siteId, String userId);
}