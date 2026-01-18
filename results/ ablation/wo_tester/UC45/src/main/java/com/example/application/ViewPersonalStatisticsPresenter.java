package com.example.application;

import com.example.domain.StatisticsData;
import com.example.presentation.StatisticsViewModel;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Presenter responsible for transforming domain data into presentation view model.
 */
public class ViewPersonalStatisticsPresenter {
    public ViewPersonalStatisticsPresenter() {
        // Initialization if needed
    }

    public StatisticsViewModel present(StatisticsData data) {
        // Sequence diagram message m10: "4. present(statisticsData)"
        
        // Sequence diagram message m11: "Format data for display"
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedTotalSales = currencyFormatter.format(data.getTotalSales());
        String formattedAverageOrderValue = currencyFormatter.format(data.computeAverage());

        // Join popular items list into a readable string
        String popularItemsList = String.join(", ", data.getPopularItems());

        // Sequence diagram message m12: "Create StatisticsViewModel"
        return new StatisticsViewModel(
                formattedTotalSales,
                formattedAverageOrderValue,
                popularItemsList,
                data.getPeriod()
        );
    }
}