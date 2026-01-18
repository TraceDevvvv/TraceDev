package com.example.dto;

import java.util.Date;
import java.util.List;

/**
 * Data Transfer Object for statistics report.
 */
public class StatisticsReportDTO {
    private String pointName;
    private Date periodStart;
    private Date periodEnd;
    private double totalSales;
    private double averageRating;
    private List<String> popularItems;

    public StatisticsReportDTO() {
        // Default constructor
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String name) {
        this.pointName = name;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date date) {
        this.periodStart = date;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date date) {
        this.periodEnd = date;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double amount) {
        this.totalSales = amount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double rating) {
        this.averageRating = rating;
    }

    public List<String> getPopularItems() {
        return popularItems;
    }

    public void setPopularItems(List<String> items) {
        this.popularItems = items;
    }
}