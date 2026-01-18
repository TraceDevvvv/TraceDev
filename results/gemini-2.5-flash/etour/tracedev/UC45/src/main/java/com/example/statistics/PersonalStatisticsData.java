package com.example.statistics;

/**
 * Domain Layer Entity / Data Transfer Object (internal to domain/application layers)
 * Represents raw personal statistics data as retrieved from the data source.
 */
public class PersonalStatisticsData {
    private String operatorId;
    private String pointId;
    private double totalSales;
    private int itemsSold;

    /**
     * Constructor for PersonalStatisticsData.
     * @param operatorId The ID of the operator.
     * @param pointId The ID of the refreshment point.
     * @param totalSales The total sales amount.
     * @param itemsSold The total number of items sold.
     */
    public PersonalStatisticsData(String operatorId, String pointId, double totalSales, int itemsSold) {
        this.operatorId = operatorId;
        this.pointId = pointId;
        this.totalSales = totalSales;
        this.itemsSold = itemsSold;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getPointId() {
        return pointId;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public int getItemsSold() {
        return itemsSold;
    }

    // No setters provided as this object is typically immutable once fetched.
}