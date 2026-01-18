package com.example.model;

import java.util.Map;

/**
 * Data Transfer Object for Statistical Report
 */
public class StatisticalReportDTO {
    private String locationName;
    private int totalFeedback;
    private double averageRating;
    private Map<String, Integer> feedbackByCategory;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getTotalFeedback() {
        return totalFeedback;
    }

    public void setTotalFeedback(int totalFeedback) {
        this.totalFeedback = totalFeedback;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Map<String, Integer> getFeedbackByCategory() {
        return feedbackByCategory;
    }

    public void setFeedbackByCategory(Map<String, Integer> feedbackByCategory) {
        this.feedbackByCategory = feedbackByCategory;
    }
}