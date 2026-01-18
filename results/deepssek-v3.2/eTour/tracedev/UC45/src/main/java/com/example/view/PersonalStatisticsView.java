package com.example.view;

import com.example.controller.ViewPersonalStatisticsController;
import com.example.dto.PersonalStatisticsDTO;

/**
 * Boundary/Form class representing the UI for personal statistics.
 * Implements the view component from the sequence diagram.
 */
public class PersonalStatisticsView {

    private ViewPersonalStatisticsController controller;

    /**
     * Constructor with dependency injection.
     */
    public PersonalStatisticsView(ViewPersonalStatisticsController controller) {
        this.controller = controller;
    }

    /**
     * Called when the operator selects "view personal statistics".
     * Sequence diagram step: onViewPersonalStatisticsSelected(operatorId)
     */
    public void onViewPersonalStatisticsSelected(String operatorId) {
        displayForm();
        PersonalStatisticsDTO dto = controller.handleViewStatisticsRequest(operatorId);
        if (dto != null) {
            renderStatistics(dto);
        }
        // If dto is null, error handling is performed inside controller and showErrorMessage is called
    }

    /**
     * Displays the initial form.
     * Sequence diagram step: displayForm()
     */
    public void displayForm() {
        // In a real UI, would initialize form components
        System.out.println("Displaying personal statistics form...");
    }

    /**
     * Renders the statistics data received from controller.
     * Sequence diagram step: renderStatistics(statisticsDto)
     */
    public void renderStatistics(PersonalStatisticsDTO statisticsDto) {
        if (statisticsDto.isEmpty()) {
            showErrorMessage("No statistics data available");
            return;
        }
        // Display the statistics data
        System.out.println("=== Personal Statistics ===");
        System.out.println("Operator ID: " + statisticsDto.getOperatorId());
        System.out.println("Refreshment Point: " + statisticsDto.getRefreshmentPointName());
        System.out.println("Total Sales: $" + statisticsDto.getTotalSales());
        System.out.println("Average Order Value: $" + statisticsDto.getAverageOrderValue());
        System.out.println("Number of Orders: " + statisticsDto.getNumberOfOrders());
        System.out.println("Period: " + statisticsDto.getPeriodStartDate() + " to " + statisticsDto.getPeriodEndDate());
    }

    /**
     * Displays an error message to the user.
     * Sequence diagram step: showErrorMessage(message)
     */
    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }
}