package com.example.report;

/**
 * StatisticalReportDTO (Data Transfer Object)
 * Used to transfer statistical report data to the UI or other layers, containing a summary.
 */
public class StatisticalReportDTO {
    public String locationName;
    public double averageRating;
    public int feedbackCount;
    public String details;

    /**
     * Constructor for StatisticalReportDTO.
     * @param locationName The name of the location the report is about.
     * @param averageRating The calculated average rating.
     * @param feedbackCount The number of feedback entries.
     * @param details A summary of the report.
     */
    public StatisticalReportDTO(String locationName, double averageRating, int feedbackCount, String details) {
        this.locationName = locationName;
        this.averageRating = averageRating;
        this.feedbackCount = feedbackCount;
        this.details = details;
    }

    @Override
    public String toString() {
        return "StatisticalReportDTO{" +
               "locationName='" + locationName + '\'' +
               ", averageRating=" + averageRating +
               ", feedbackCount=" + feedbackCount +
               ", details='" + details + '\'' +
               '}';
    }
}