package com.example.dto;

import com.example.domain.Statistics;
import java.util.List;

/**
 * Data Transfer Object for statistics, used by presentation layer.
 * Converts from Statistics domain object.
 */
public class StatisticsDTO {
    private String operatorId;
    private double totalSales;
    private double averageOrderValue;
    private int customerCount;
    private List<String> peakHours;

    public StatisticsDTO(Statistics statistics) {
        this.operatorId = statistics.getOperatorId();
        this.totalSales = statistics.getTotalSales();
        this.averageOrderValue = statistics.getAverageOrderValue();
        this.customerCount = statistics.getCustomerCount();
        this.peakHours = statistics.getPeakHours();
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
}