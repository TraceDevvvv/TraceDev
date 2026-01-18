package com.example.persistence;

import com.example.repository.FeedbackRepository;
import com.example.model.Feedback;
import java.util.Optional;

/**
 * Database participant for the sequence diagram.
 * Corresponds to DB participant with ref "Persistence".
 */
public class Persistence {
    private FeedbackRepository repository;

    public Persistence(FeedbackRepository repository) {
        this.repository = repository;
    }

    /**
     * Query existing feedback.
     * Corresponds to sequence diagram message m5.
     */
    public Optional<Feedback> queryExistingFeedback(String siteId, String userId) {
        return repository.queryExistingFeedback(siteId, userId);
    }

    /**
     * Return feedback record.
     * Corresponds to sequence diagram message m6.
     */
    public Optional<Feedback> returnFeedbackRecord(String siteId, String userId) {
        return repository.queryExistingFeedback(siteId, userId);
    }
}