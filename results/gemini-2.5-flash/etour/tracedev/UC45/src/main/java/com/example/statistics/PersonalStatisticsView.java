package com.example.statistics;

/**
 * Presentation Layer (MVC) Component
 * Responsible for rendering personal statistics or displaying error messages to the user.
 */
public class PersonalStatisticsView {
    private PersonalStatisticsDto statisticsData; // Stores data to be rendered

    /**
     * Renders the provided personal statistics to the user.
     * @param stats The PersonalStatisticsDto containing data to be displayed.
     */
    public void renderStatistics(PersonalStatisticsDto stats) {
        this.statisticsData = stats;
        System.out.println("\n--- Personal Statistics View ---");
        if (stats != null) {
            System.out.println("Operator: " + stats.getOperatorName());
            System.out.println("Refreshment Point: " + stats.getPointName());
            System.out.println("Total Sales: " + stats.getFormattedSales());
            System.out.println("Items Sold: " + stats.getFormattedItemsSold());
            System.out.println("------------------------------");
        } else {
            System.out.println("No statistics data available to render.");
            System.out.println("------------------------------");
        }
        // REQ_EXIT_COND_1: Display is handled by View.
        // This output on the console represents the display of the statistics screen
        // which corresponds to sequence diagram return message m15: displayStatisticsScreen()
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n--- ERROR ---");
        System.err.println("Error displaying statistics: " + message);
        System.err.println("-------------");
        // REQ_EXIT_COND_2: Display error message is handled by the view.
        // This output on the console represents the error message being displayed,
        // which corresponds to sequence diagram return message m20: errorMessageDisplayed
    }

    // Getter for statisticsData, though primarily used for rendering and not directly accessed after rendering.
    public PersonalStatisticsDto getStatisticsData() {
        return statisticsData;
    }
}