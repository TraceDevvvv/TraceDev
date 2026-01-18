package com.example.model;

import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

/**
 * Statistical report for a location based on feedback.
 */
public class StatisticalReport {
    private Location location;
    private Map<String, Object> feedbackSummary;
    private Date generatedAt;

    /**
     * Constructs a StatisticalReport from location and feedback list.
     * Prepares statistical report as per sequence diagram note.
     */
    public StatisticalReport(Location location, List<SiteFeedback> feedbackList) {
        this.location = location;
        this.generatedAt = new Date();
        this.feedbackSummary = prepareSummary(feedbackList);
    }

    private Map<String, Object> prepareSummary(List<SiteFeedback> feedbackList) {
        // Assumed logic: compute simple metrics from feedback list.
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalFeedbacks", feedbackList.size());
        if (!feedbackList.isEmpty()) {
            // Example metric: average rating if exists
            double totalRating = 0;
            int count = 0;
            for (SiteFeedback fb : feedbackList) {
                Map<String, Object> data = fb.getFeedbackData();
                if (data.containsKey("rating")) {
                    Object rating = data.get("rating");
                    if (rating instanceof Number) {
                        totalRating += ((Number) rating).doubleValue();
                        count++;
                    }
                }
            }
            if (count > 0) {
                metrics.put("averageRating", totalRating / count);
            }
        }
        return metrics;
    }

    public Location getLocation() {
        return location;
    }

    public Map<String, Object> getFeedbackSummary() {
        return feedbackSummary;
    }

    public Date getGeneratedAt() {
        return generatedAt;
    }
}