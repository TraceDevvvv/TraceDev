package com.agency.report.model;

public class ReportStatistic {
    private Long locationId;
    private String locationName;
    private double averageRating;
    private long totalFeedbacks;

    public ReportStatistic(Long locationId, String locationName, double averageRating, long totalFeedbacks) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.averageRating = averageRating;
        this.totalFeedbacks = totalFeedbacks;
    }

    // Getters
    public Long getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public long getTotalFeedbacks() {
        return totalFeedbacks;
    }
}
