package com.example.entity;

import java.util.Date;
import java.util.Map;

/**
 * Entity representing raw statistics data.
 */
public class StatisticsData {

    private Long id;
    private String operatorId;
    private String refreshmentPointId;
    private String refreshmentPointName;
    private Map<String, Double> salesData;
    private int orderCounts;
    private Date calculationDate;

    public StatisticsData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Map<String, Double> getSalesData() {
        return salesData;
    }

    public void setSalesData(Map<String, Double> salesData) {
        this.salesData = salesData;
    }

    public int getOrderCounts() {
        return orderCounts;
    }

    public void setOrderCounts(int orderCounts) {
        this.orderCounts = orderCounts;
    }

    public Date getCalculationDate() {
        return calculationDate;
    }

    public void setCalculationDate(Date calculationDate) {
        this.calculationDate = calculationDate;
    }
}