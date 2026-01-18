package com.example.report;

import java.util.Date;

/**
 * StatisticalReport class
 * Represents a comprehensive statistical summary for a specific location based on feedback.
 */
public class StatisticalReport {
    // CD: StatisticalReport attribute locationId
    private String locationId;
    private String locationName;
    private double averageRating;
    private int feedbackCount;
    private String details;
    // CD: StatisticalReport attribute generationDate
    private Date generationDate;

    /**
     * Constructor for StatisticalReport.
     * @param locationId The ID of the location this report is for.
     * @param locationName The name of the location.
     * @param averageRating The calculated average rating for the location.
     * @param feedbackCount The total number of feedback entries considered.
     * @param details A detailed summary string for the report.
     * @param generationDate The date and time when the report was generated.
     */
    public StatisticalReport(String locationId, String locationName, double averageRating, int feedbackCount, String details, Date generationDate) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.averageRating = averageRating;
        this.feedbackCount = feedbackCount;
        this.details = details;
        this.generationDate = generationDate;
    }

    // Getters
    public String getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getFeedbackCount() {
        return feedbackCount;
    }

    public String getDetails() {
        return details;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    /**
     * Converts this StatisticalReport object to a StatisticalReportDTO.
     * @return A StatisticalReportDTO object.
     */
    // CD: StatisticalReport method toDTO
    public StatisticalReportDTO toDTO() {
        return new StatisticalReportDTO(this.locationName, this.averageRating, this.feedbackCount, this.details);
    }
}