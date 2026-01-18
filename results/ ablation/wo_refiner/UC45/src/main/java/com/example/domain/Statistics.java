package com.example.domain;

import java.util.List;

/**
 * Domain object representing statistics.
 */
public class Statistics {
    private String operatorId;
    private double totalSales;
    private double averageOrderValue;
    private int customerCount;
    private List<String> peakHours;

    public Statistics(String operatorId, double totalSales, double averageOrderValue,
                      int customerCount, List<String> peakHours) {
        this.operatorId = operatorId;
        this.totalSales = totalSales;
        this.averageOrderValue = averageOrderValue;
        this.customerCount = customerCount;
        this.peakHours = peakHours;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public double getAverageOrderValue() {
        return averageOrderValue;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public List<String> getPeakHours() {
        return peakHours;
    }

    // Setters for missing attributes from class diagram
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public void setAverageOrderValue(double averageOrderValue) {
        this.averageOrderValue = averageOrderValue;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public void setPeakHours(List<String> peakHours) {
        this.peakHours = peakHours;
    }
}