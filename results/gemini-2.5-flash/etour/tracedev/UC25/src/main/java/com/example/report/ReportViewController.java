
package com.example.report;

import java.util.List;

/**
 * ReportViewController class
 * Represents the view layer component responsible for handling user interactions
 * related to reports and displaying information.
 */
public class ReportViewController {
    private ReportService reportService;
    private String selectedLocationId; // Store selected location ID for form submission

    /**
     * Constructor for ReportViewController, injecting the ReportService dependency.
     * @param reportService The service responsible for report generation logic.
     */
    public ReportViewController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Handler for activating the feature (e.g., when a "View Reports" button is clicked).
     * Initiates the process of fetching and displaying available locations.
     */
    public void onActivateFeature() {
        System.out.println("\n[RVC] User activated 'View Statistical Reports' feature.");
        System.out.println("[RVC] Calling ReportService.getLocations().");
        List<LocationDTO> locations = reportService.getLocations();
        displayPlaces(locations);
    }

    /**
     * Displays a list of locations to the user.
     * @param locations A list of LocationDTO objects to be displayed.
     */
    public void displayPlaces(List<LocationDTO> locations) {
        System.out.println("\n[RVC] Displaying available places:");
        // SD: m9
        if (locations.isEmpty()) {
            System.out.println("No locations found.");
            return;
        }
        for (LocationDTO loc : locations) {
            System.out.println("  - " + loc.name + " (ID: " + loc.id + ")");
        }
        System.out.println("[RVC] User can now select a location to view its report.");
    }

    /**
     * Handler for when a user selects a location from the displayed list.
     * This method typically updates the UI state or internal state to reflect the selection.
     * @param locationId The ID of the newly selected location.
     */
    public void onLocationSelected(String locationId) {
        this.selectedLocationId = locationId; // Store for later submission
        System.out.println("[RVC] User selected location with ID: " + locationId);
    }

    /**
     * Handler for when the user submits the form to generate a report for the selected location.
     * @param selectedLocationId The ID of the location for which the report is requested.
     */
    public void onSubmitForm(String selectedLocationId) {
        System.out.println("\n[RVC] User submitted form for location ID: " + selectedLocationId);
        try {
            System.out.println("[RVC] Calling ReportService.generateStatisticalReport().");
            StatisticalReportDTO report = reportService.generateStatisticalReport(selectedLocationId);
            showStatisticalReport(report);
        } catch (IllegalArgumentException e) { // Catch more specific exception first
            displayErrorMessage("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            // SD: m26 (error reception)
            // Catches the simulated connection interrupted error
            displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Displays the generated statistical report to the user.
     * @param report The StatisticalReportDTO containing the report data.
     */
    public void showStatisticalReport(StatisticalReportDTO report) {
        // SD: m25
        System.out.println("\n[RVC] Displaying Statistical Report:");
        System.out.println("------------------------------------");
        System.out.println("Location:       " + report.locationName);
        System.out.println("Average Rating: " + String.format("%.2f", report.averageRating));
        System.out.println("Feedback Count: " + report.feedbackCount);
        System.out.println("Details:        " + report.details);
        System.out.println("------------------------------------");
    }

    /**
     * Displays an error message to the user.
     * This method is added to satisfy requirement R12 from the original prompt (though R12 isn't explicitly provided,
     * the sequence diagram's error path implies this method's existence).
     * @param message The error message to be displayed.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n[RVC] ERROR: " + message);
        System.err.println("[RVC] Please try the operation again.");
    }
}
