package com.restaurant.statistics.model;

import java.time.LocalDate;

/**
 * Model class representing statistics for a Point of Restaurant.
 * This class encapsulates all statistical data associated with a refreshment point.
 * It follows JavaBean conventions with getter and setter methods.
 */
public class RestaurantPointStatistics {
    
    // Basic point information
    private String pointId;
    private String pointName;
    private String operatorName;
    
    // Core statistics
    private int totalCustomersServed;
    private int totalRevenue; // in currency units (e.g., dollars, euros)
    private double averageCustomerRating; // 1.0 to 5.0 scale
    private String mostPopularItem;
    private String peakHours;
    
    // Calculated metrics
    private double averageRevenuePerCustomer;
    private String satisfactionLevel;
    private LocalDate lastUpdated;
    
    /**
     * Default constructor.
     * Initializes the statistics object with default values.
     */
    public RestaurantPointStatistics() {
        this.lastUpdated = LocalDate.now();
    }
    
    /**
     * Constructor with basic point information.
     * 
     * @param pointId the unique identifier for the refreshment point
     * @param pointName the name of the refreshment point
     * @param operatorName the name of the operator
     */
    public RestaurantPointStatistics(String pointId, String pointName, String operatorName) {
        this.pointId = pointId;
        this.pointName = pointName;
        this.operatorName = operatorName;
        this.lastUpdated = LocalDate.now();
    }
    
    // Getters and setters for all fields
    
    /**
     * Gets the unique identifier for the refreshment point.
     * 
     * @return the point ID
     */
    public String getPointId() {
        return pointId;
    }
    
    /**
     * Sets the unique identifier for the refreshment point.
     * 
     * @param pointId the point ID to set
     */
    public void setPointId(String pointId) {
        this.pointId = pointId;
    }
    
    /**
     * Gets the name of the refreshment point.
     * 
     * @return the point name
     */
    public String getPointName() {
        return pointName;
    }
    
    /**
     * Sets the name of the refreshment point.
     * 
     * @param pointName the point name to set
     */
    public void setPointName(String pointName) {
        this.pointName = pointName;
    }
    
    /**
     * Gets the name of the operator.
     * 
     * @return the operator name
     */
    public String getOperatorName() {
        return operatorName;
    }
    
    /**
     * Sets the name of the operator.
     * 
     * @param operatorName the operator name to set
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    
    /**
     * Gets the total number of customers served.
     * 
     * @return the total customers served
     */
    public int getTotalCustomersServed() {
        return totalCustomersServed;
    }
    
    /**
     * Sets the total number of customers served.
     * 
     * @param totalCustomersServed the total customers served to set
     */
    public void setTotalCustomersServed(int totalCustomersServed) {
        this.totalCustomersServed = totalCustomersServed;
    }
    
    /**
     * Gets the total revenue generated.
     * 
     * @return the total revenue in currency units
     */
    public int getTotalRevenue() {
        return totalRevenue;
    }
    
    /**
     * Sets the total revenue generated.
     * 
     * @param totalRevenue the total revenue to set (in currency units)
     */
    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    
    /**
     * Gets the average customer rating.
     * Rating is typically on a scale of 1.0 to 5.0.
     * 
     * @return the average customer rating
     */
    public double getAverageCustomerRating() {
        return averageCustomerRating;
    }
    
    /**
     * Sets the average customer rating.
     * 
     * @param averageCustomerRating the average customer rating to set (1.0 to 5.0)
     * @throws IllegalArgumentException if rating is outside valid range (1.0-5.0)
     */
    public void setAverageCustomerRating(double averageCustomerRating) {
        if (averageCustomerRating < 1.0 || averageCustomerRating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 1.0 and 5.0");
        }
        this.averageCustomerRating = averageCustomerRating;
    }
    
    /**
     * Gets the most popular item sold at the refreshment point.
     * 
     * @return the most popular item
     */
    public String getMostPopularItem() {
        return mostPopularItem;
    }
    
    /**
     * Sets the most popular item sold at the refreshment point.
     * 
     * @param mostPopularItem the most popular item to set
     */
    public void setMostPopularItem(String mostPopularItem) {
        this.mostPopularItem = mostPopularItem;
    }
    
    /**
     * Gets the peak business hours.
     * Typically represented as time ranges (e.g., "8:00-10:00, 12:00-14:00").
     * 
     * @return the peak hours
     */
    public String getPeakHours() {
        return peakHours;
    }
    
    /**
     * Sets the peak business hours.
     * 
     * @param peakHours the peak hours to set
     */
    public void setPeakHours(String peakHours) {
        this.peakHours = peakHours;
    }
    
    /**
     * Gets the average revenue per customer.
     * This is a calculated metric based on total revenue and total customers.
     * 
     * @return the average revenue per customer
     */
    public double getAverageRevenuePerCustomer() {
        return averageRevenuePerCustomer;
    }
    
    /**
     * Sets the average revenue per customer.
     * 
     * @param averageRevenuePerCustomer the average revenue per customer to set
     */
    public void setAverageRevenuePerCustomer(double averageRevenuePerCustomer) {
        this.averageRevenuePerCustomer = averageRevenuePerCustomer;
    }
    
    /**
     * Gets the customer satisfaction level.
     * This is a categorical representation based on the average rating.
     * 
     * @return the satisfaction level (e.g., "Excellent", "Good", "Average", "Needs Improvement")
     */
    public String getSatisfactionLevel() {
        return satisfactionLevel;
    }
    
    /**
     * Sets the customer satisfaction level.
     * 
     * @param satisfactionLevel the satisfaction level to set
     */
    public void setSatisfactionLevel(String satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel;
    }
    
    /**
     * Gets the date when the statistics were last updated.
     * 
     * @return the last updated date
     */
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }
    
    /**
     * Sets the date when the statistics were last updated.
     * 
     * @param lastUpdated the last updated date to set
     */
    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    /**
     * Returns a formatted string representation of the statistics.
     * This is useful for displaying the data in a readable format.
     * 
     * @return formatted string with all statistics
     */
    @Override
    public String toString() {
        return String.format(
            "Restaurant Point Statistics:%n" +
            "---------------------------%n" +
            "Point ID: %s%n" +
            "Point Name: %s%n" +
            "Operator: %s%n" +
            "Total Customers Served: %d%n" +
            "Total Revenue: %d%n" +
            "Average Customer Rating: %.1f/5.0%n" +
            "Average Revenue per Customer: %.2f%n" +
            "Most Popular Item: %s%n" +
            "Peak Hours: %s%n" +
            "Satisfaction Level: %s%n" +
            "Last Updated: %s%n",
            pointId, pointName, operatorName,
            totalCustomersServed, totalRevenue,
            averageCustomerRating, averageRevenuePerCustomer,
            mostPopularItem, peakHours, satisfactionLevel,
            lastUpdated.toString()
        );
    }
    
    /**
     * Validates that all required statistics fields are populated.
     * This method checks for null or invalid values in critical fields.
     * 
     * @return true if the statistics are valid, false otherwise
     */
    public boolean isValid() {
        return pointId != null && !pointId.isEmpty() &&
               pointName != null && !pointName.isEmpty() &&
               operatorName != null && !operatorName.isEmpty() &&
               totalCustomersServed >= 0 &&
               totalRevenue >= 0 &&
               averageCustomerRating >= 1.0 && averageCustomerRating <= 5.0 &&
               mostPopularItem != null && !mostPopularItem.isEmpty() &&
               peakHours != null && !peakHours.isEmpty() &&
               lastUpdated != null;
    }
    
    /**
     * Calculates and returns the revenue per customer.
     * This is a convenience method that performs the calculation on the fly.
     * 
     * @return revenue per customer, or 0.0 if no customers were served
     */
    public double calculateRevenuePerCustomer() {
        if (totalCustomersServed == 0) {
            return 0.0;
        }
        return (double) totalRevenue / totalCustomersServed;
    }
    
    /**
     * Updates the last updated timestamp to the current date.
     * This should be called whenever statistics are modified.
     */
    public void updateTimestamp() {
        this.lastUpdated = LocalDate.now();
    }
}