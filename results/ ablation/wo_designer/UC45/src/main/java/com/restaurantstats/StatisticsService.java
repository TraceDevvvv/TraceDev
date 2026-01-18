package com.restaurantstats;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/**
 * Service class for fetching and calculating statistics for a refreshment point.
 */
public class StatisticsService {

    /**
     * Represents a single statistic item with name and value.
     */
    public static class StatisticItem {
        private String name;
        private double value;

        public StatisticItem(String name, double value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.format("%s: %.2f", name, value);
        }
    }

    /**
     * Fetches statistics for a given refreshment point ID.
     * In a real application, this would query a database or external service.
     *
     * @param refreshmentPointId the ID of the refreshment point
     * @return a list of StatisticItem for the point
     */
    public List<StatisticItem> fetchStatistics(String refreshmentPointId) {
        // Simulating data fetching with random values for demonstration
        List<StatisticItem> stats = new ArrayList<>();
        Random rand = new Random();

        // Example statistics for a restaurant point
        stats.add(new StatisticItem("Total Orders Today", rand.nextInt(200)));
        stats.add(new StatisticItem("Average Order Value ($)", 20 + rand.nextDouble() * 30));
        stats.add(new StatisticItem("Customer Satisfaction (%)", 70 + rand.nextDouble() * 30));
        stats.add(new StatisticItem("Most Popular Item", 100 + rand.nextInt(50))); // quantity sold
        stats.add(new StatisticItem("Peak Hour", 12 + rand.nextInt(6))); // hour of day

        // Simulate network delay, but ensure it's under 3 seconds as per requirement
        try {
            Thread.sleep(100 + rand.nextInt(500)); // 100-600 ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return stats;
    }

    /**
     * Validates if the given refreshment point ID exists.
     *
     * @param refreshmentPointId the ID to validate
     * @return true if valid, false otherwise
     */
    public boolean isValidPoint(String refreshmentPointId) {
        // In a real system, this would check against a database.
        // For demo, accept non‑null, non‑empty strings.
        return refreshmentPointId != null && !refreshmentPointId.trim().isEmpty();
    }
}