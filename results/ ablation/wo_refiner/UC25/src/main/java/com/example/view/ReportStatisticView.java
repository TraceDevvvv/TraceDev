package com.example.view;

import com.example.controller.ReportStatisticController;
import com.example.dto.StatisticalReportDTO;
import com.example.model.Location;
import java.util.List;
import java.util.Scanner;

/**
 * View for report statistics.
 * Precondition: Agency Operator must be logged in (REQ-004).
 * LoggedIn stereotype is indicated.
 */
public class ReportStatisticView {
    private ReportStatisticController controller;

    public ReportStatisticView(ReportStatisticController controller) {
        this.controller = controller;
    }

    /**
     * Displays locations and initiates the use case.
     * Corresponds to sequence diagram: activate ViewReportStatistic feature.
     */
    public void displayLocations() {
        // Simulate the logged‑in operator.
        System.out.println("=== View Report Statistic ===");
        List<Location> locations = controller.getLocations();
        System.out.println("Available Locations:");
        for (Location loc : locations) {
            System.out.println("  " + loc.getId() + ": " + loc.getName());
        }
        // Corresponds to sequence message: display location selection form
        displayLocationSelectionForm();
    }

    /**
     * Displays location selection form.
     * Corresponds to sequence diagram: display location selection form.
     */
    public void displayLocationSelectionForm() {
        System.out.println("Please select a location and submit the form.");
    }

    /**
     * Submits location selection and displays report.
     * Implements the flow from sequence diagram.
     * Corresponds to sequence message: select location and submit form.
     */
    public void submitLocationSelection(String locationId) {
        System.out.println("Generating report for location ID: " + locationId);
        StatisticalReportDTO report = controller.generateReport(locationId);
        displayReport(report);
    }

    /**
     * Displays the statistical report (or error).
     * Corresponds to sequence diagram: display statistical report.
     */
    public void displayReport(StatisticalReportDTO report) {
        if (report.getLocationName().startsWith("Error")) {
            // Corresponds to sequence diagram: display error message
            displayErrorMessage(report.getLocationName());
        } else {
            System.out.println("=== Statistical Report ===");
            System.out.println("Location: " + report.getLocationName());
            System.out.println("Generated at: " + report.getGenerationTime());
            if (report.getMetrics() != null) {
                System.out.println("Metrics: " + report.getMetrics());
            }
        }
    }

    /**
     * Displays error message.
     * Corresponds to sequence diagram: display error message.
     */
    public void displayErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * A simple interactive main for demonstration.
     */
    public static void main(String[] args) {
        // Setup dependencies.
        javax.sql.DataSource ds = null; // In reality would be configured.
        com.example.repository.LocationRepository locRepo =
            new com.example.repository.LocationRepositoryImpl(ds);
        com.example.repository.SiteFeedbackRepository feedbackRepo =
            new com.example.repository.SiteFeedbackRepositoryImpl(ds);
        com.example.service.SearchSiteService service =
            new com.example.service.SearchSiteService(feedbackRepo);
        ReportStatisticController controller =
            new ReportStatisticController(locRepo, service);
        ReportStatisticView view = new ReportStatisticView(controller);

        // Simulate the use case: activate ViewReportStatistic feature
        view.displayLocations();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter location ID to generate report: ");
        String locationId = scanner.nextLine();
        view.submitLocationSelection(locationId);
        scanner.close();
    }
}