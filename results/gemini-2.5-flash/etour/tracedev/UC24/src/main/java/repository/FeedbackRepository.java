package com.example.repository;

import com.example.model.Feedback;
import com.example.service.NetworkConnectionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IFeedbackRepository.
 * This class simulates data access for Feedback.
 */

public class FeedbackRepository implements IFeedbackRepository {

    // Represents a database connection or ORM context. For this example, it's a dummy object.
    private Object dataSource;

    // Dummy data for simulation
    private final List<Feedback> dummyFeedback;

    // Flag to simulate network connection issues
    private boolean simulateNetworkError = false;

    /**
     * Constructs a FeedbackRepository with a given data source.
     * @param dataSource The data source object (e.g., database connection pool).
     */
    public FeedbackRepository(Object dataSource) {
        this.dataSource = dataSource;
        this.dummyFeedback = new ArrayList<>(Arrays.asList(
            new Feedback("F001", "S001", "Alice", 5, "Amazing experience!", new Date()),
            new Feedback("F002", "S001", "Bob", 4, "A bit crowded, but beautiful.", new Date(System.currentTimeMillis() - 86400000L)), // 1 day ago
            new Feedback("F003", "S002", "Charlie", 3, "Interesting, but nothing special.", new Date(System.currentTimeMillis() - 172800000L)), // 2 days ago
            new Feedback("F004", "S001", "David", 5, "Must visit!", new Date(System.currentTimeMillis() - 259200000L)), // 3 days ago
            new Feedback("F005", "S003", "Eve", 4, "Great history.", new Date()),
            new Feedback("F006", "S003", "Frank", 3, "Very long walk.", new Date(System.currentTimeMillis() - 86400000L))
        ));
        System.out.println("FeedbackRepository: Initialized with dummy data.");
    }

    @Override
    public List<Feedback> getFeedbackBySiteId(String siteId) throws NetworkConnectionException {
        System.out.println("FeedbackRepository: Retrieving feedback for site ID: " + siteId + " from data source.");
        if (simulateNetworkError) {
            System.out.println("FeedbackRepository: Simulating NetworkConnectionException for getFeedbackBySiteId().");
            throw new NetworkConnectionException("Failed to connect to feedback data source.");
        }
        // Simulate network delay
        try {
            Thread.sleep(150); // Simulate some processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // Filter dummy data by siteId
        return dummyFeedback.stream()
                           .filter(f -> f.getSiteId().equals(siteId))
                           .collect(Collectors.toList());
    }

    /**
     * Sets a flag to simulate a network connection error for testing purposes.
     * @param simulateNetworkError If true, getFeedbackBySiteId() will throw NetworkConnectionException.
     */
    public void setSimulateNetworkError(boolean simulateNetworkError) {
        this.simulateNetworkError = simulateNetworkError;
    }
}