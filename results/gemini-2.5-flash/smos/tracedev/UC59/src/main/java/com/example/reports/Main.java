package com.example.reports;

import java.util.Scanner;

/**
 * Main class to simulate the application flow based on the sequence diagram.
 * It initializes the components and orchestrates interactions.
 * This class simulates the 'Student' actor.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Setup Application Components ---
        // 1. User Session (simulating a logged-in user)
        UserSession userSession = new UserSession("S101"); // Assume S101 is logged in

        // 2. Repository (data source, capable of simulating errors)
        StudentReportRepository repository = new StudentReportRepository();

        // 3. View and Controller setup:
        // Due to circular dependency (View needs Controller, Controller needs View),
        // we create the View first, then the Controller injecting the View,
        // and finally set the Controller into the View.
        StudentReportView view = new StudentReportView(); // Use default constructor
        StudentReportController controller = new StudentReportController(userSession, repository, view);
        view.setController(controller); // Inject the controller into the view

        System.out.println("User is logged in as " + userSession.getCurrentStudentId() + ".");
        System.out.println("User clicks on \"Online report\"...");

        // --- First interaction: Requesting list of reports ---
        // Student -> View: clickOnlineReport() (simulated by calling view.requestReportListDisplay())
        // View -> View: requestReportListDisplay()
        view.requestReportListDisplay(); // Initial request for report list

        // Simulate a scenario with server error for fetching report list
        System.out.print("\n--- Testing Server Error Scenario for Report List ---\n");
        System.out.print("Simulate a server error for fetching reports list? (yes/no): ");
        boolean simulateErrorReports = scanner.nextLine().trim().equalsIgnoreCase("yes");
        repository.setSimulateServerConnectionError(simulateErrorReports);
        view.requestReportListDisplay(); // Re-request after setting error flag

        // Reset error simulation for the next step if it was enabled
        if (simulateErrorReports) {
            repository.setSimulateServerConnectionError(false);
            System.out.println("\nServer error simulation for report list reset. Re-fetching successfully...");
            view.requestReportListDisplay(); // Fetch successfully to get a list for selection
        }


        // --- Second interaction: Requesting details of a specific report ---
        System.out.println("\n(User sees the list of reports, now selects one.)");
        System.out.print("Enter Report ID to view details (e.g., rep001, rep002, rep003): ");
        String selectedReportId = scanner.nextLine().trim();

        // Simulate a scenario with server error for fetching report details
        System.out.print("\n--- Testing Server Error Scenario for Report Details ---\n");
        System.out.print("Simulate a server error for fetching report details? (yes/no): ");
        boolean simulateErrorDetails = scanner.nextLine().trim().equalsIgnoreCase("yes");
        repository.setSimulateServerConnectionError(simulateErrorDetails);

        // Student -> View: selectReport(reportId) (simulated by calling view.requestReportDetailsDisplay())
        // View -> View: requestReportDetailsDisplay(reportId)
        view.requestReportDetailsDisplay(selectedReportId);

        // Reset error simulation if it was enabled
        if (simulateErrorDetails) {
            repository.setSimulateServerConnectionError(false);
            System.out.println("\nServer error simulation for report details reset. Re-fetching successfully...");
            view.requestReportDetailsDisplay(selectedReportId); // Fetch successfully
        }

        scanner.close();
        System.out.println("\nSimulation ended.");
    }
}