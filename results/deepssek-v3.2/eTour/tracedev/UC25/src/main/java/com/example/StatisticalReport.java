package com.example;

/**
 * Domain class representing a statistical report for a location.
 */
public class StatisticalReport {
    private String locationName;
    private String statistics;

    public StatisticalReport(String locationName, String statistics) {
        this.locationName = locationName;
        this.statistics = statistics;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getStatistics() {
        return statistics;
    }
}