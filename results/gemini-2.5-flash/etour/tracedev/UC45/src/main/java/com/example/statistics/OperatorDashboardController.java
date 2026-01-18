package com.example.statistics;

/**
 * Presentation Layer (MVC) Component
 * Handles initial user interaction for displaying statistics.
 */
public class OperatorDashboardController {
    private PersonalStatisticsController statisticsController;
    private SessionManager sessionManager;

    /**
     * Constructor for OperatorDashboardController.
     * @param statisticsController The controller responsible for personal statistics display.
     * @param sessionManager The session manager to retrieve authenticated operator ID.
     */
    public OperatorDashboardController(PersonalStatisticsController statisticsController, SessionManager sessionManager) {
        this.statisticsController = statisticsController;
        this.sessionManager = sessionManager;
    }

    /**
     * Simulates the operator selecting to display personal statistics.
     * This method retrieves the authenticated operator ID and then delegates
     * to the PersonalStatisticsController to fetch and display the statistics.
     */
    public void selectDisplayStatistics() {
        System.out.println("OperatorDashboardController: Operator selected to display statistics.");

        // REQ_ENTRY_COND_1: Retrieve authenticated operator ID from SessionManager
        String operatorId = sessionManager.getAuthenticatedOperatorId();
        if (operatorId == null || !sessionManager.isAuthenticated()) {
            System.out.println("OperatorDashboardController: Operator not authenticated. Cannot display statistics.");
            // In a real application, this would redirect to a login page or display an error.
            return;
        }

        System.out.println("OperatorDashboardController: Authenticated Operator ID retrieved: " + operatorId);

        // Delegate to PersonalStatisticsController to handle the display logic
        statisticsController.getStatisticsForDisplay(operatorId);
    }
}