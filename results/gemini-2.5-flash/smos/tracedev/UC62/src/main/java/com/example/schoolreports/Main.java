package com.example.schoolreports;

/**
 * Main application entry point for demonstrating the Student Report Viewer system.
 * This class sets up the dependencies and orchestrates the initial application flow,
 * simulating a user interacting with the system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Student Report Viewer Application...");

        // 1. Data Access Layer (DAL) setup
        SMOSGateway smosGateway = new SMOSGateway();
        IReportRepository reportRepository = new SMOSReportRepository(smosGateway);

        // 2. Application Service Layer setup
        ReportViewingService reportService = new ReportViewingService(reportRepository);

        // 3. Presentation Layer setup
        // The View needs the Controller to send user actions.
        // The Controller needs the View to display results and errors.
        // This creates a circular dependency, which is common in MVC.
        // We'll create the view first, then the controller, then inject the controller into the view.
        ReportView reportView = new ReportView(null); // Temporarily null controller
        ReportController reportController = new ReportController(reportService, reportView);
        reportView = new ReportView(reportController); // Re-instantiate or set the controller

        // Now, trigger the initial user interaction flow as per sequence diagram.

        // Simulate Parent -> View: onRequestReports()
        System.out.println("\n--- Simulating User Scenario 1: View Student Reports ---");
        reportView.onRequestReports(); // This will internally call viewStudentReports with "parent123"

        // Simulate Parent -> View: onSelectReportCard(reportCardId : String) - Successful Path
        System.out.println("\n--- Simulating User Scenario 2: Select Report Card (Successful) ---");
        reportView.onSelectReportCard("RC001");

        // Simulate Parent -> View: onSelectReportCard(reportCardId : String) - Error Path
        System.out.println("\n--- Simulating User Scenario 3: Select Report Card (Connection Failure) ---");
        // Instruct SMOSGateway to simulate a connection failure
        smosGateway.setSimulateConnectionFailure(true);
        reportView.onSelectReportCard("RC002");
        smosGateway.setSimulateConnectionFailure(false); // Reset for further operations if any

        // Demonstrate a simple CLI for more interaction
        // reportView.startCLI(); // Uncomment this line to run the interactive CLI
    }
}