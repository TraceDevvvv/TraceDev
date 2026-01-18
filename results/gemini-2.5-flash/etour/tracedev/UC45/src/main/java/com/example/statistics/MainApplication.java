package com.example.statistics;

/**
 * Main application class to demonstrate the interaction flow.
 * Acts as the entry point and sets up the dependencies (simple DI container).
 */
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("--- Starting Personal Statistics Application ---");

        // 1. Data Access Layer Setup
        StatisticsRepositoryImpl statisticsRepository = new StatisticsRepositoryImpl();

        // 2. Application Layer Setup
        StatisticsService statisticsService = new StatisticsService(statisticsRepository);

        // 3. Presentation Layer (Model, View, Controller) Setup
        PersonalStatisticsModel statisticsModel = new PersonalStatisticsModel(statisticsService);
        PersonalStatisticsView statisticsView = new PersonalStatisticsView();
        PersonalStatisticsController personalStatisticsController = new PersonalStatisticsController(statisticsModel, statisticsView);

        // 4. Session Management
        SessionManager sessionManager = new SessionManager(); // Default constructor mocks an authenticated user "OP001"

        // 5. Entry Point: Operator Dashboard Controller
        OperatorDashboardController operatorDashboardController = new OperatorDashboardController(personalStatisticsController, sessionManager);

        System.out.println("\n--- Scenario 1: Successful Data Retrieval ---");
        // Simulate the operator's action to view statistics
        operatorDashboardController.selectDisplayStatistics();

        System.out.println("\n--- Scenario 2: Data Not Found for Operator ---");
        // Change authenticated operator to one with no data
        sessionManager.setAuthenticatedOperatorId("OP999");
        operatorDashboardController.selectDisplayStatistics();

        System.out.println("\n--- Scenario 3: Simulate Database Connection Error ---");
        // Set the repository to simulate an error
        sessionManager.setAuthenticatedOperatorId("OP001"); // Back to a valid operator
        statisticsRepository.setSimulateConnectionError(true);
        operatorDashboardController.selectDisplayStatistics();
        statisticsRepository.setSimulateConnectionError(false); // Reset for other scenarios if needed

        System.out.println("\n--- Scenario 4: Unauthenticated User ---");
        sessionManager.setAuthenticated(false); // Simulate unauthenticated state
        operatorDashboardController.selectDisplayStatistics();

        System.out.println("\n--- Application Finished ---");
    }
}