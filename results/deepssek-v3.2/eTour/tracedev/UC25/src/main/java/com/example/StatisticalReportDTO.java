package com.example;

/**
 * Data Transfer Object for StatisticalReport.
 * Used to transfer report data between layers.
 */
public class StatisticalReportDTO {
    public String locationName;
    public String statistics;

    public StatisticalReportDTO(String locationName, String statistics) {
        this.locationName = locationName;
        this.statistics = statistics;
    }
}