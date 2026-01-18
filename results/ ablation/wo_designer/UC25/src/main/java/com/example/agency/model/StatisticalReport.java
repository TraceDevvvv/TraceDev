package com.example.agency.model;

import java.util.Map;

/**
 * Represents a statistical report for a location.
 */
public class StatisticalReport {
    private String reportId;
    private Location location;
    private int totalFeedbacks;
    private double averageRating;
    private Map<Integer, Integer> ratingDistribution; // rating -> count
    private String generatedTimestamp;

    public StatisticalReport(String reportId, Location location, int totalFeedbacks,
                             double averageRating, Map<Integer, Integer> ratingDistribution,
                             String generatedTimestamp) {
        this.reportId = reportId;
        this.location = location;
        this.totalFeedbacks = totalFeedbacks;
        this.averageRating = averageRating;
        this.ratingDistribution = ratingDistribution;
        this.generatedTimestamp = generatedTimestamp;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public void setTotalFeedbacks(int totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Map<Integer, Integer> getRatingDistribution() {
        return ratingDistribution;
    }

    public void setRatingDistribution(Map<Integer, Integer> ratingDistribution) {
        this.ratingDistribution = ratingDistribution;
    }

    public String getGeneratedTimestamp() {
        return generatedTimestamp;
    }

    public void setGeneratedTimestamp(String generatedTimestamp) {
        this.generatedTimestamp = generatedTimestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report ID: ").append(reportId).append("\n");
        sb.append("Location: ").append(location.getName()).append("\n");
        sb.append("Total Feedbacks: ").append(totalFeedbacks).append("\n");
        sb.append("Average Rating: ").append(String.format("%.2f", averageRating)).append("\n");
        sb.append("Rating Distribution:\n");
        for (Map.Entry<Integer, Integer> entry : ratingDistribution.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(" stars: ").append(entry.getValue()).append("\n");
        }
        sb.append("Generated At: ").append(generatedTimestamp);
        return sb.toString();
    }
}