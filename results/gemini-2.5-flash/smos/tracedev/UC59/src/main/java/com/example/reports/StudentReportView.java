package com.example.reports;

import java.util.List;
import java.util.concurrent.TimeUnit; // For simulating delay

/**
 * Represents the user interface for displaying student reports.
 * It interacts with the StudentReportController to request data and display results.
 */
public class StudentReportView {
    private StudentReportController controller;

    /**
     * Default constructor for StudentReportView.
     * The controller can be set later using {@link #setController(StudentReportController)}.
     * This helps in resolving circular dependencies during initialization.
     */
    public StudentReportView() {
        // Controller will be set via setter.
    }

    /**
     * Constructs a new StudentReportView with a specified controller.
     * As per UML.
     * @param controller The controller responsible for handling report-related logic.
     */
    public StudentReportView(StudentReportController controller) {
        this.controller = controller;
    }

    /**
     * Sets the controller for this view.
     * Useful for dependency injection or breaking circular dependencies.
     * @param controller The StudentReportController instance.
     */
    public void setController(StudentReportController controller) {
        this.controller = controller;
    }

    /**
     * Displays a list of student reports to the user.
     * @param reports The list of StudentReport objects to display.
     */
    public void displayReportsList(List<StudentReport> reports) {
        System.out.println("\n--- Student Reports List ---");
        if (reports == null || reports.isEmpty()) {
            System.out.println("No reports found for this student.");
            return;
        }
        for (StudentReport report : reports) {
            System.out.printf("  Report ID: %s, Student ID: %s, Generated: %s (%d report cards)\n",
                    report.getReportId(), report.getStudentId(), report.getGenerationDate(), report.getReportCards().size());
        }
        System.out.println("----------------------------\n");
    }

    /**
     * Displays the detailed information of a single student report.
     * @param report The StudentReport object to display.
     */
    public void displayReportDetails(StudentReport report) {
        System.out.println("\n--- Student Report Details ---");
        if (report == null) {
            System.out.println("Report details not found.");
            return;
        }
        System.out.printf("  Report ID: %s\n", report.getReportId());
        System.out.printf("  Student ID: %s\n", report.getStudentId());
        System.out.printf("  Generated On: %s\n", report.getGenerationDate());
        System.out.println("  Report Cards:");
        for (ReportCard card : report.getReportCards()) {
            System.out.printf("    - Subject: %s, Grade: %s, Comments: %s\n",
                    card.getSubject(), card.getGrade(), card.getComments());
        }
        System.out.println("----------------------------\n");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.err.println("\n!!! ERROR: " + message + " !!!\n");
    }

    /**
     * Initiates the process of requesting and displaying the list of all student reports.
     * This method is called when the user (simulated) clicks on "Online Report".
     * Corresponds to `View -> View: requestReportListDisplay()` in the sequence diagram.
     */
    public void requestReportListDisplay() {
        System.out.println("[View] Requesting list of reports from controller...");
        // Delay to simulate UI processing/waiting for controller
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (controller != null) {
            controller.handleRequestReportsList();
        } else {
            System.err.println("[View] Error: Controller not set in StudentReportView. Cannot handle request.");
        }
    }

    /**
     * Initiates the process of requesting and displaying the details of a specific student report.
     * This method is called when the user (simulated) selects a report from the list.
     * Corresponds to `View -> View: requestReportDetailsDisplay(reportId)` in the sequence diagram.
     *
     * @param reportId The ID of the report whose details are to be displayed.
     */
    public void requestReportDetailsDisplay(String reportId) {
        System.out.println("[View] Requesting details for report ID: " + reportId + " from controller...");
        // Delay to simulate UI processing/waiting for controller
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (controller != null) {
            controller.handleRequestReportDetails(reportId);
        } else {
            System.err.println("[View] Error: Controller not set in StudentReportView. Cannot handle request.");
        }
    }
}