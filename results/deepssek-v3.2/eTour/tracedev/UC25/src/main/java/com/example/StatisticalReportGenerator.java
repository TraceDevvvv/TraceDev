package com.example;

import java.util.List;

/**
 * Generates a statistical report from a list of feedback.
 */
public class StatisticalReportGenerator {
    /**
     * Prepares a statistical report based on feedback.
     * @param feedbackList List of SiteFeedback objects.
     * @return A StatisticalReport object.
     * @throws ConnectionException if there is an interruption during generation.
     */
    public StatisticalReport prepareReport(List<SiteFeedback> feedbackList) throws ConnectionException {
        String locationId = feedbackList.isEmpty() ? "Unknown" : feedbackList.get(0).getLocationId();
        String locationName = "Location " + locationId;
        int count = feedbackList.size();
        if (Math.random() < 0.1) {
            throw new ConnectionException("Connection lost during report generation.");
        }
        String stats = "Total feedback entries: " + count;
        return new StatisticalReport(locationName, stats);
    }
}