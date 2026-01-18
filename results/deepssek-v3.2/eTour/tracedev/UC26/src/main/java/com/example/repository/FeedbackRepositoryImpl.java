package com.example.repository;

import com.example.model.Feedback;
import com.example.service.ConnectionManager;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of FeedbackRepository.
 */
public class FeedbackRepositoryImpl implements FeedbackRepository {
    private DataSource dataSource;
    private List<Feedback> feedbackList = new ArrayList<>();
    private ConnectionManager connectionManager;

    // Constructor with dependency injection
    public FeedbackRepositoryImpl(DataSource dataSource, ConnectionManager connectionManager) {
        this.dataSource = dataSource;
        this.connectionManager = connectionManager;
        // Initialize with some sample data
        feedbackList.add(new Feedback("F1", "S1", "Initial comment"));
        feedbackList.add(new Feedback("F2", "S1", "Another comment"));
        feedbackList.add(new Feedback("F3", "S2", "Feedback for site 2"));
    }

    @Override
    public List<Feedback> findBySiteId(String siteId) {
        // Check connection as per sequence diagram
        if (!connectionManager.checkConnection()) {
            throw new RuntimeException("Connection lost");
        }
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            if (feedback.getSiteId().equals(siteId)) {
                result.add(feedback);
            }
        }
        return result;
    }

    @Override
    public Feedback findById(String feedbackId) {
        // Check connection as per sequence diagram
        if (!connectionManager.checkConnection()) {
            throw new RuntimeException("Connection lost");
        }
        for (Feedback feedback : feedbackList) {
            if (feedback.getId().equals(feedbackId)) {
                return feedback;
            }
        }
        return null;
    }

    @Override
    public void save(Feedback feedback) {
        // Check connection as per sequence diagram
        if (!connectionManager.checkConnection()) {
            throw new RuntimeException("Connection lost");
        }
        // In a real implementation, this would persist the feedback via DataSource
        // Here we just update the existing feedback in the list
        for (int i = 0; i < feedbackList.size(); i++) {
            if (feedbackList.get(i).getId().equals(feedback.getId())) {
                feedbackList.set(i, feedback);
                break;
            }
        }
    }

    // Getter for dataSource (if needed)
    public DataSource getDataSource() {
        return dataSource;
    }
}