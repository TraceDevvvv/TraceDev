package com.example.domain;

import java.util.List;

/**
 * Domain entity representing personal statistics data for an operator.
 * Performance Requirement: The system shall respond within 3 seconds for statistics retrieval and display.
 */
public class StatisticsData {
    private final String refreshmentPointId;
    private final double totalSales;
    private final double averageOrderValue;
    private final List<String> popularItems;
    private final String period;

    public StatisticsData(String refreshmentPointId, double totalSales, double averageOrderValue,
                          List<String> popularItems, String period) {
        this.refreshmentPointId = refreshmentPointId;
        this.totalSales = totalSales;
        this.averageOrderValue = averageOrderValue;
        this.popularItems = popularItems;
        this.period = period;
    }

    // Added to satisfy requirement: Flow of Events "System displays a form that shows data for the statistics…"
    public double computeAverage() {
        return averageOrderValue;
    }

    // Added to satisfy requirement: Flow of Events "System displays a form that shows data for the statistics…"
    public String getPeriod() {
        return period;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public double getAverageOrderValue() {
        return averageOrderValue;
    }

    public List<String> getPopularItems() {
        return popularItems;
    }
}