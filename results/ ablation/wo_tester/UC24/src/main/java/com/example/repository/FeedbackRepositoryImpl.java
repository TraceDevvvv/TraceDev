package com.example.repository;

import com.example.model.Feedback;
import com.example.exception.DatabaseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Implementation of FeedbackRepository.
 * Simulates database interaction and connection status.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    private boolean isConnected;

    public FeedbackRepositoryImpl() {
        // Assume connection is initially established
        this.isConnected = true;
    }

    @Override
    public List<Feedback> findFeedbackBySiteId(int siteId) throws DatabaseException {
        // Simulate connection check
        if (!isConnected) {
            throw new DatabaseException("Database connection lost while fetching feedback for site " + siteId, 1002);
        }

        // Simulate database query and result mapping
        List<Feedback> feedbackList = new ArrayList<>();
        if (siteId == 1) {
            feedbackList.add(new Feedback(101, 1, "Great site!", 5, new Date()));
            feedbackList.add(new Feedback(102, 1, "Could be better.", 3, new Date()));
        } else if (siteId == 2) {
            feedbackList.add(new Feedback(201, 2, "Excellent service.", 4, new Date()));
        }
        // For other site IDs, return empty list (simulating no feedback)
        return feedbackList;
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    // Method to simulate connection interruption (for testing)
    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}