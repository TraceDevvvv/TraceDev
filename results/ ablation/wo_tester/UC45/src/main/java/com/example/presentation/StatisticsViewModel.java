package com.example.presentation;

/**
 * ViewModel for presenting statistics data in the UI.
 */
public class StatisticsViewModel {
    public final String formattedTotalSales;
    public final String formattedAverageOrderValue;
    public final String popularItemsList;
    public final String periodDisplay;

    public StatisticsViewModel(String formattedTotalSales, String formattedAverageOrderValue,
                               String popularItemsList, String periodDisplay) {
        this.formattedTotalSales = formattedTotalSales;
        this.formattedAverageOrderValue = formattedAverageOrderValue;
        this.popularItemsList = popularItemsList;
        this.periodDisplay = periodDisplay;
    }
}