package com.example.schoolreports;

import java.util.List;

/**
 * Presentation Layer (MVC/MVVM) - Controller.
 * Handles user input from the ReportView and orchestrates interactions
 * with the ReportViewingService to fulfill requests.
 */
public class ReportController {

    private ReportViewingService reportService;
    private ReportView reportView; // The controller observes the view, but also sends data back to it.

    /**
     * Constructs a new ReportController with dependencies on ReportViewingService and ReportView.\n
     * @param reportService The service layer for report viewing operations.\n
     * @param reportView The view that this controller will interact with to display information and errors.
     */
    public ReportController(ReportViewingService reportService, ReportView reportView) {
        this.reportService = reportService;
        this.reportView = reportView;
    }

    /**
     * Handles the request to view student reports for a specific parent.\n
     * This method is triggered by the `ReportView` (e.g., via `onRequestReports`).\n
     * @param parentId The ID of the parent whose reports are to be viewed.
     */
    public void viewStudentReports(String parentId) {
        System.out.println("ReportController: Received request to view student reports for parent: " + parentId);
        try {
            List<StudentReportSummary> reports = reportService.getStudentReportsForParent(parentId);
            reportView.displayReportCards(reports);
        } catch (SMOSConnectionException e) {
            // The sequence diagram does not show this path specifically for initial report listing,
            // but it's good practice to handle potential connection errors here too.
            System.err.println("ReportController: Error fetching student reports: " + e.getMessage());
            reportView.showErrorMessage("Failed to load student reports due to a connection issue. Please try again.");
        } catch (Exception e) {
            System.err.println("ReportController: An unexpected error occurred while fetching student reports: " + e.getMessage());
            reportView.showErrorMessage("An unexpected error occurred. Please try again later.");
        }
    }

    /**
     * Handles the request to view detailed information for a specific report card.\n
     * This method is triggered by the `ReportView` (e.g., via `onSelectReportCard`).\n
     * @param reportCardId The ID of the report card to display details for.
     */
    public void selectReportCard(String reportCardId) {
        System.out.println("ReportController: Received request to select report card: " + reportCardId);
        try {
            ReportCard reportCard = reportService.getReportCardDetails(reportCardId);
            reportView.displayReportCardDetails(reportCard);
        } catch (SMOSConnectionException e) {
            // Controller handles the exception and forms the message, as per sequence diagram.
            System.err.println("ReportController: SMOS connection error while fetching report card details: " + e.getMessage());
            reportView.showErrorMessage("Failed to retrieve report details."); // m30: AppService -> Controller: Error: "Failed to retrieve report details."
        } catch (Exception e) {
            System.err.println("ReportController: An unexpected error occurred while fetching report card details: " + e.getMessage());
            reportView.showErrorMessage("An unexpected error occurred. Please try again later.");
        }
    }
}