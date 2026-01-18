package com.example.agency.service;

import com.example.agency.model.Location;
import com.example.agency.model.SiteFeedback;
import com.example.agency.model.StatisticalReport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service to generate statistical reports from site feedback.
 */
public class ReportService {
    /**
     * Generates a statistical report for a location based on its feedback.
     *
     * @param location  The selected location.
     * @param feedbacks List of feedback for the location.
     * @return StatisticalReport object.
     */
    public StatisticalReport generateReport(Location location, List<SiteFeedback> feedbacks) {
        int total = feedbacks.size();
        if (total == 0) {
            throw new IllegalArgumentException("No feedback provided for report generation.");
        }

        double sumRating = 0;
        Map<Integer, Integer> distribution = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            distribution.put(i, 0);
        }

        for (SiteFeedback fb : feedbacks) {
            sumRating += fb.getRating();
            distribution.put(fb.getRating(), distribution.get(fb.getRating()) + 1);
        }

        double averageRating = sumRating / total;

        // Generate a report ID based on location and timestamp.
        String reportId = "RPT-" + location.getId() + "-" + System.currentTimeMillis();

        // Simulate report generation time.
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Report generation interrupted.");
        }

        String timestamp = java.time.LocalDateTime.now().toString();
        return new StatisticalReport(reportId, location, total, averageRating, distribution, timestamp);
    }
}