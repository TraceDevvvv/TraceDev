package com.example.statistics;

/**
 * Domain Layer / Presentation Layer DTO
 * Data Transfer Object specifically designed for displaying personal statistics in the UI.
 * Contains formatted data.
 */
public class PersonalStatisticsDto {
    private String operatorName;
    private String pointName;
    private String formattedSales;
    private String formattedItemsSold;

    /**
     * Constructor for PersonalStatisticsDto.
     * @param operatorName The name of the operator (formatted).
     * @param pointName The name of the refreshment point (formatted).
     * @param formattedSales The total sales amount (formatted for display, e.g., "$123.45").
     * @param formattedItemsSold The total number of items sold (formatted for display, e.g., "123 items").
     */
    public PersonalStatisticsDto(String operatorName, String pointName, String formattedSales, String formattedItemsSold) {
        this.operatorName = operatorName;
        this.pointName = pointName;
        this.formattedSales = formattedSales;
        this.formattedItemsSold = formattedItemsSold;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public String getPointName() {
        return pointName;
    }

    public String getFormattedSales() {
        return formattedSales;
    }

    public String getFormattedItemsSold() {
        return formattedItemsSold;
    }

    // No setters as DTOs are typically immutable once created.
}