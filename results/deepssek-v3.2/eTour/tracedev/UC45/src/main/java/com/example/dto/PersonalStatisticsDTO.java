package com.example.dto;

import java.util.Date;

/**
 * Data Transfer Object for personal statistics.
 * Contains refreshment point data explicitly linked via refreshmentPointId.
 */
public class PersonalStatisticsDTO {

    private String operatorId;
    private String refreshmentPointId;
    private String refreshmentPointName;
    private double totalSales;
    private double averageOrderValue;
    private int numberOfOrders;
    private Date periodStartDate;
    private Date periodEndDate;

    public PersonalStatisticsDTO() {
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public void setRefreshmentPointId(String refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
    }

    public String getRefreshmentPointName() {
        return refreshmentPointName;
    }

    public void setRefreshmentPointName(String refreshmentPointName) {
        this.refreshmentPointName = refreshmentPointName;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public double getAverageOrderValue() {
        return averageOrderValue;
    }

    public void setAverageOrderValue(double averageOrderValue) {
        this.averageOrderValue = averageOrderValue;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(Date periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public Date getPeriodEndDate() {
        return periodEndDate;
    }

    public void setPeriodEndDate(Date periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    /**
     * Checks if the DTO contains empty/default data.
     */
    public boolean isEmpty() {
        return "N/A".equals(operatorId) || "No data".equals(refreshmentPointName);
    }
}