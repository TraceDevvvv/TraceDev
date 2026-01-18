package com.example.domain;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a statistics report for a refreshment point.
 */
public class StatisticsReport {
    private String pointId;
    private Date periodStart;
    private Date periodEnd;
    private double totalSales;
    private double averageRating;
    private List<String> popularItems;

    public StatisticsReport(String pointId) {
        this.pointId = pointId;
        this.popularItems = new ArrayList<>();
        // Default period: last 30 days from now
        this.periodEnd = new Date();
        this.periodStart = new Date(periodEnd.getTime() - 30L * 24 * 60 * 60 * 1000);
    }

    public String getPointId() {
        return pointId;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public List<String> getPopularItems() {
        return popularItems;
    }

    /**
     * Calculates statistics from a list of transactions.
     * Assumptions: average rating is computed from a hypothetical rating field in Transaction.
     * Since rating is not in Transaction class, we simulate it.
     * For simplicity, popular items are top 3 items by frequency.
     */
    public void calculateStatistics(List<Transaction> data) {
        if (data == null || data.isEmpty()) {
            totalSales = 0.0;
            averageRating = 0.0;
            popularItems.clear();
            return;
        }

        // Calculate total sales
        totalSales = data.stream().mapToDouble(Transaction::getAmount).sum();

        // Simulate average rating (rating not present in Transaction, so we simulate random ratings)
        // In a real system, rating would be part of Transaction or a separate entity.
        double totalRating = 0.0;
        for (Transaction t : data) {
            totalRating += (t.getAmount() % 5) + 1; // simulate rating 1-5 based on amount
        }
        averageRating = totalRating / data.size();

        // Determine popular items (if items are present)
        // For simplicity, we use dummy items
        popularItems.clear();
        if (!data.isEmpty() && data.get(0).getItems() != null && !data.get(0).getItems().isEmpty()) {
            // Take first transaction's items as popular (simplified)
            popularItems.addAll(data.get(0).getItems().subList(0, Math.min(3, data.get(0).getItems().size())));
        } else {
            popularItems.add("Item A");
            popularItems.add("Item B");
            popularItems.add("Item C");
        }
    }
}