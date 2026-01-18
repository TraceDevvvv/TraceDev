package com.example.domain;

/**
 * Domain entity representing a statistical report.
 * Added getTotalFeedback and getAverageRating to satisfy requirement Flow of Events 7,8.
 */
public class StatisticalReport {
    private Location location;
    private int totalFeedback;
    private double averageRating;

    public StatisticalReport(Location location, int totalFeedback, double averageRating) {
        this.location = location;
        this.totalFeedback = totalFeedback;
        this.averageRating = averageRating;
    }

    public Location getLocation() {
        return location;
    }

    public int getTotalFeedback() {
        return totalFeedback;
    }

    public double getAverageRating() {
        return averageRating;
    }
}