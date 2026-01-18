package com.example.dto;

import java.util.Map;
import java.util.Date;

/**
 * Data Transfer Object for statistical report.
 */
public class StatisticalReportDTO {
    private String locationName;
    private Map<String, Object> metrics;
    private Date generationTime;

    public StatisticalReportDTO(String locationName, Map<String, Object> metrics, Date generationTime) {
        this.locationName = locationName;
        this.metrics = metrics;
        this.generationTime = generationTime;
    }

    public String getLocationName() {
        return locationName;
    }

    public Map<String, Object> getMetrics() {
        return metrics;
    }

    public Date getGenerationTime() {
        return generationTime;
    }
}