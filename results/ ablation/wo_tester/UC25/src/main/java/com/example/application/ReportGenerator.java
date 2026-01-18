package com.example.application;

import com.example.domain.SiteFeedback;
import com.example.domain.StatisticalReport;
import com.example.domain.Location;
import java.util.List;

/**
 * Service for generating statistical reports.
 * generateReport() internally calls calculateStats() to compute total feedback count and average rating.
 * Added to satisfy requirement Flow of Events 7.
 */
public class ReportGenerator {
    public StatisticalReport generateReport(List<SiteFeedback> feedbackList) {
        // Assumption: the location is taken from the first feedback item.
        // In a real scenario, we might pass the location separately.
        if (feedbackList.isEmpty()) {
            // Return a report with default values if no feedback
            return new StatisticalReport(null, 0, 0.0);
        }
        String locationId = feedbackList.get(0).getLocationId();
        Location location = new Location(locationId, "coords");
        int[] stats = calculateStats(feedbackList);
        return new StatisticalReport(location, stats[0], stats[1]);
    }

    /**
     * Calculates total feedback and average rating.
     * Added to satisfy requirement Flow of Events 7.
     */
    private int[] calculateStats(List<SiteFeedback> feedbackList) {
        int total = feedbackList.size();
        double sum = 0.0;
        for (SiteFeedback feedback : feedbackList) {
            sum += feedback.getRating();
        }
        double average = total > 0 ? sum / total : 0.0;
        return new int[]{total, (int) average};
    }
}