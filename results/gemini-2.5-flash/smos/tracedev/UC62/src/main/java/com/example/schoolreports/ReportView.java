package com.example.schoolreports;

import java.util.List;
import java.util.Scanner;

/**
 * Presentation Layer (MVC/MVVM) - View.
 * Responsible for displaying information to the user and capturing user input.
 * It observes the ReportController by holding a reference to it and calling its methods.
 */
public class ReportView {

    // <<observes>> relationship: The View interacts with and triggers actions on the Controller.
    private ReportController reportController;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new ReportView with a dependency on ReportController.
     *
     * @param reportController The controller that handles user actions from this view.
     */
    public ReportView(ReportController reportController) {
        this.reportController = reportController;
    }

    /**
     * Displays a list of student report summaries.
     *
     * @param reports A list of StudentReportSummary objects to be displayed.
     */
    public void displayReportCards(List<StudentReportSummary> reports) {
        System.out.println("\n--- Student Reports Summary ---");
        if (reports == null || reports.isEmpty()) {
            System.out.println("No student reports available.");
            return;
        }
        for (StudentReportSummary summary : reports) {
            System.out.println(summary);
        }
        System.out.println("-----------------------------\n");
    }

    /**
     * Displays the detailed information of a single report card.
     *
     * @param reportCard The ReportCard object containing full details.
     */
    public void displayReportCardDetails(ReportCard reportCard) {
        System.out.println("\n" + reportCard.toString());
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be shown.
     */
    public void showErrorMessage(String message) {
        System.err.println("\nERROR: " + message + "\n");
    }

    /**
     * Simulates a user request to load all reports.
     * This method is triggered by user interaction (Parent -> View: onRequestReports()).
     * It delegates the action to the controller.
     */
    public void onRequestReports() {
        System.out.println("View: User requested to load all student reports.");
        // In a real application, parentId would come from authenticated user session.
        // For this demo, we use a fixed parent ID.
        reportController.viewStudentReports("parent123");
    }

    /**
     * Simulates a user selecting a specific report card for detailed viewing.
     * This method is triggered by user interaction (Parent -> View: onSelectReportCard()).
     * It delegates the action to the controller.
     *
     * @param reportCardId The ID of the report card selected by the user.
     */
    public void onSelectReportCard(String reportCardId) {
        System.out.println("View: User selected report card with ID: " + reportCardId);
        reportController.selectReportCard(reportCardId);
    }

    /**
     * Provides a basic command-line interface for interaction.
     */
    public void startCLI() {
        System.out.println("Welcome to the Student Report Viewer!");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. View all student reports");
            System.out.println("2. View detailed report card (by ID)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    onRequestReports();
                    break;
                case "2":
                    System.out.print("Enter Report Card ID (e.g., RC001, RC002, RC003): ");
                    String id = scanner.nextLine();
                    onSelectReportCard(id);
                    break;
                case "3":
                    running = false;
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}