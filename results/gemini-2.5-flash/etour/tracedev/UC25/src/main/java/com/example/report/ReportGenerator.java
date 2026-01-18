package com.example.report;

import java.util.Date;
import java.util.List;

/**
 * ReportGenerator class
 * Responsible for processing raw location and feedback data to generate a statistical report.
 */
public class ReportGenerator {

    /**
     * Prepares a statistical report based on a location and its associated feedback.
     * @param location The Location object for which the report is to be generated.
     * @param feedback A list of SiteFeedback objects related to the location.
     * @return A StatisticalReport object containing aggregated data.
     */
    public StatisticalReport prepareReport(Location location, List<SiteFeedback> feedback) {
        System.out.println("[RG] Preparing report for location: " + location.getName());

        int totalRating = 0;
        for (SiteFeedback fb : feedback) {
            totalRating += fb.getRating();
        }

        double averageRating = feedback.isEmpty() ? 0.0 : (double) totalRating / feedback.size();
        int feedbackCount = feedback.size();
        String details = "Report generated with " + feedbackCount + " feedback entries. ";
        if (feedbackCount > 0) {
            details += String.format("Average rating: %.2f.", averageRating);
        } else {
            details += "No feedback available.";
        }

        return new StatisticalReport(
                location.getId(),
                location.getName(),
                averageRating,
                feedbackCount,
                details,
                new Date()
        );
    }
}