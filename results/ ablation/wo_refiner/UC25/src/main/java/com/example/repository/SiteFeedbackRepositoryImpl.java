package com.example.repository;

import com.example.model.SiteFeedback;
import com.example.exception.ConnectionException;
import javax.sql.DataSource;
import java.util.*;

/**
 * Implementation of SiteFeedbackRepository.
 * Throws ConnectionException as per REQ-011.
 */
public class SiteFeedbackRepositoryImpl implements SiteFeedbackRepository {
    private DataSource dataSource;

    public SiteFeedbackRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<SiteFeedback> findByLocationId(String locationId) throws ConnectionException {
        // Simulating a connection failure for demonstration of REQ-011.
        // For locationId "error2", we simulate an interruption.
        if ("error2".equals(locationId)) {
            throw new ConnectionException("Database connection lost while fetching feedback.");
        }
        // Dummy feedback data.
        List<SiteFeedback> feedbacks = new ArrayList<>();
        Map<String, Object> data1 = new HashMap<>();
        data1.put("rating", 4.5);
        data1.put("comment", "Great place");
        feedbacks.add(new SiteFeedback(locationId, data1, new Date()));

        Map<String, Object> data2 = new HashMap<>();
        data2.put("rating", 3.0);
        data2.put("comment", "Average");
        feedbacks.add(new SiteFeedback(locationId, data2, new Date()));
        return feedbacks;
    }
}