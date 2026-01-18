package com.example.view;

import com.example.viewmodel.StatisticsViewModel;
import com.example.dto.StatisticsDTO;

/**
 * Presentation layer view for displaying statistics.
 * Binds to StatisticsViewModel to handle UI logic.
 */
public class StatisticsView {
    private StatisticsViewModel viewModel;

    public StatisticsView(StatisticsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Displays the statistics form with provided data.
     * If dto is null, shows empty form.
     */
    public void displayForm(StatisticsDTO statisticsDto) {
        if (statisticsDto == null) {
            System.out.println("Displaying empty statistics form.");
        } else {
            System.out.println("=== Statistics Form ===");
            System.out.println("Operator ID: " + statisticsDto.getOperatorId());
            System.out.println("Total Sales: " + statisticsDto.getTotalSales());
            System.out.println("Average Order Value: " + statisticsDto.getAverageOrderValue());
            System.out.println("Customer Count: " + statisticsDto.getCustomerCount());
            System.out.println("Peak Hours: " + statisticsDto.getPeakHours());
        }
    }

    public void showLoadingIndicator() {
        System.out.println("Loading... please wait.");
    }

    public void hideLoadingIndicator() {
        System.out.println("Loading completed.");
    }

    public void showError(String message) {
        System.out.println("ERROR: " + message);
    }

    // Simulates navigation to this view (triggered by operator)
    public void navigateToStatisticsView() {
        System.out.println("Statistics view opened.");
        displayForm(null); // Initially empty form
    }

    /**
     * Called when statistics are successfully loaded.
     * Per missing sequence diagram return message "2. Displays statistics form"
     * and missing class diagram method 'displayForm'.
     */
    public void onStatisticsLoaded(StatisticsDTO statisticsDto) {
        hideLoadingIndicator();
        displayForm(statisticsDto);
    }

    /**
     * Called when an error occurs during loading.
     * Per missing sequence diagram return message "Shows error message"
     * and missing class diagram method 'showError'.
     */
    public void onError(String errorMessage) {
        hideLoadingIndicator();
        showError(errorMessage);
    }
}