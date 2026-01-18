package com.restaurant.statistics.service;

import com.restaurant.statistics.model.RestaurantPointStatistics;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Statistics service for Point of Restaurant operations.
 * This service handles fetching and calculating statistics data for a refreshment point.
 * It simulates data retrieval from a server and handles connection interruptions.
 */
public class StatisticsService {
    
    private Random random;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * Constructor for StatisticsService.
     * Initializes random generator for simulating data.
     */
    public StatisticsService() {
        this.random = new Random();
    }
    
    /**
     * Retrieves statistics for the current refreshment point.
     * Simulates server communication and handles connection interruptions.
     * 
     * @return RestaurantPointStatistics object containing the statistics data
     * @throws RuntimeException if data retrieval fails due to server connection issues
     */
    public RestaurantPointStatistics getCurrentPointStatistics() {
        System.out.println("Fetching statistics for refreshment point...");
        
        try {
            // Simulate server connection check
            if (!isServerAvailable()) {
                throw new RuntimeException("Connection to statistics server interrupted");
            }
            
            // Simulate data retrieval delay
            simulateNetworkDelay();
            
            // Generate sample statistics data
            RestaurantPointStatistics stats = generateSampleStatistics();
            
            System.out.println("Statistics retrieved successfully.");
            return stats;
            
        } catch (RuntimeException e) {
            System.out.println("Error retrieving statistics: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Retrieves statistics for a specific date range.
     * 
     * @param startDate the start date for the statistics
     * @param endDate the end date for the statistics
     * @return RestaurantPointStatistics object for the specified date range
     */
    public RestaurantPointStatistics getStatisticsForDateRange(LocalDate startDate, LocalDate endDate) {
        System.out.println("Fetching statistics from " + startDate.format(dateFormatter) + 
                          " to " + endDate.format(dateFormatter));
        
        if (!isServerAvailable()) {
            throw new RuntimeException("Connection to statistics server interrupted");
        }
        
        simulateNetworkDelay();
        
        // Generate statistics for the date range
        return generateStatisticsForRange(startDate, endDate);
    }
    
    /**
     * Checks if the statistics server is available.
     * Simulates server availability with a chance of connection interruption.
     * 
     * @return true if server is available, false if connection is interrupted
     */
    private boolean isServerAvailable() {
        // Simulate server availability - 80% chance of success
        double availability = random.nextDouble();
        
        if (availability < 0.8) {
            return true;
        } else {
            System.out.println("Warning: Statistics server connection interrupted");
            return false;
        }
    }
    
    /**
     * Simulates network delay for data retrieval.
     * Adds a random delay between 100-500ms.
     */
    private void simulateNetworkDelay() {
        try {
            int delay = 100 + random.nextInt(400); // 100-500ms
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Network delay simulation interrupted");
        }
    }
    
    /**
     * Generates sample statistics data for demonstration.
     * 
     * @return RestaurantPointStatistics object with sample data
     */
    private RestaurantPointStatistics generateSampleStatistics() {
        LocalDate today = LocalDate.now();
        LocalDate weekStart = today.minusDays(7);
        
        RestaurantPointStatistics stats = new RestaurantPointStatistics();
        stats.setPointId("REST-POINT-" + (1000 + random.nextInt(9000)));
        stats.setPointName("Main Refreshment Point");
        stats.setOperatorName("Operator " + (char)('A' + random.nextInt(26)));
        
        // Generate random statistics data
        stats.setTotalCustomersServed(500 + random.nextInt(500)); // 500-1000
        stats.setTotalRevenue(10000 + random.nextInt(20000)); // 10000-30000
        stats.setAverageCustomerRating(3.5 + random.nextDouble() * 1.5); // 3.5-5.0
        stats.setMostPopularItem("Coffee");
        stats.setPeakHours("8:00-10:00, 12:00-14:00");
        
        // Calculate additional metrics
        calculateAdditionalMetrics(stats);
        
        return stats;
    }
    
    /**
     * Generates statistics for a specific date range.
     * 
     * @param startDate the start date
     * @param endDate the end date
     * @return RestaurantPointStatistics object for the date range
     */
    private RestaurantPointStatistics generateStatisticsForRange(LocalDate startDate, LocalDate endDate) {
        RestaurantPointStatistics stats = new RestaurantPointStatistics();
        stats.setPointId("REST-POINT-" + (1000 + random.nextInt(9000)));
        stats.setPointName("Main Refreshment Point");
        stats.setOperatorName("Operator " + (char)('A' + random.nextInt(26)));
        
        // Calculate days in range for realistic data
        long daysInRange = endDate.toEpochDay() - startDate.toEpochDay() + 1;
        
        // Generate proportional statistics based on date range
        int dailyCustomers = 50 + random.nextInt(50); // 50-100 per day
        stats.setTotalCustomersServed((int)(dailyCustomers * daysInRange));
        
        double dailyRevenue = 1000 + random.nextInt(1000); // 1000-2000 per day
        stats.setTotalRevenue((int)(dailyRevenue * daysInRange));
        
        stats.setAverageCustomerRating(3.5 + random.nextDouble() * 1.5); // 3.5-5.0
        stats.setMostPopularItem("Sandwich");
        stats.setPeakHours("11:00-13:00, 17:00-19:00");
        
        calculateAdditionalMetrics(stats);
        
        return stats;
    }
    
    /**
     * Calculates and sets additional metrics based on base statistics.
     * 
     * @param stats the RestaurantPointStatistics object to enrich
     */
    private void calculateAdditionalMetrics(RestaurantPointStatistics stats) {
        // Calculate average revenue per customer
        if (stats.getTotalCustomersServed() > 0) {
            double avgRevenuePerCustomer = (double) stats.getTotalRevenue() / stats.getTotalCustomersServed();
            stats.setAverageRevenuePerCustomer(avgRevenuePerCustomer);
        } else {
            stats.setAverageRevenuePerCustomer(0.0);
        }
        
        // Calculate customer satisfaction level
        double rating = stats.getAverageCustomerRating();
        if (rating >= 4.5) {
            stats.setSatisfactionLevel("Excellent");
        } else if (rating >= 4.0) {
            stats.setSatisfactionLevel("Good");
        } else if (rating >= 3.0) {
            stats.setSatisfactionLevel("Average");
        } else {
            stats.setSatisfactionLevel("Needs Improvement");
        }
        
        // Set last updated timestamp
        stats.setLastUpdated(LocalDate.now());
    }
    
    /**
     * Checks if the service can connect to the statistics server.
     * This method can be called from UI to handle connection status.
     * 
     * @return true if connection is available, false otherwise
     */
    public boolean checkConnectionStatus() {
        return isServerAvailable();
    }
}