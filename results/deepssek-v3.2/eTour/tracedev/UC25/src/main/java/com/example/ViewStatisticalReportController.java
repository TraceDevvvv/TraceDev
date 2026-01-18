package com.example;

import java.util.List;

/**
 * Controller that manages the flow for viewing statistical reports.
 * Acts as the intermediary between the AgencyOperator and the service layer.
 */
public class ViewStatisticalReportController {
    private StatisticalReportService service;

    public ViewStatisticalReportController() {
        this.service = new StatisticalReportService();
    }

    /**
     * Activates the view statistical report feature.
     * This is called by the AgencyOperator to start the process.
     */
    public void activateViewStatisticalReportFeature() {
        try {
            List<LocationDTO> locations = service.getAllLocationDTOs();
            displayLocationListForm(locations);
        } catch (ConnectionException e) {
            service.handleConnectionException(e);
            displayErrorMessage("Connection Interrupted while loading locations.");
        }
    }

    /**
     * Handles the request to view a report for a specific location.
     * This method is triggered after location selection and form submission.
     * @param locationId The ID of the selected location.
     */
    public void handleViewReportRequest(String locationId) {
        try {
            StatisticalReportDTO report = service.generateReport(locationId);
            displayReport(report);
        } catch (ConnectionException e) {
            service.handleConnectionException(e);
            displayErrorMessage("Connection Interrupted while generating report.");
        }
    }

    /**
     * Displays the location list form to the user.
     * @param locations List of LocationDTO objects.
     */
    public void displayLocationListForm(List<LocationDTO> locations) {
        System.out.println("Displaying location list form:");
        for (LocationDTO loc : locations) {
            System.out.println(" - " + loc.id + ": " + loc.name);
        }
    }

    /**
     * Displays the generated statistical report.
     * @param report The StatisticalReportDTO to display.
     */
    public void displayReport(StatisticalReportDTO report) {
        System.out.println("Displaying Statistical Report:");
        System.out.println("Location: " + report.locationName);
        System.out.println("Statistics: " + report.statistics);
    }

    /**
     * Displays an error message to the user.
     * @param message The error message.
     */
    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Handles location selection by the user.
     * This is called when the user selects a location from the list.
     * @param locationId The ID of the selected location.
     */
    public void handleLocationSelection(String locationId) {
        System.out.println("Location selected: " + locationId);
    }

    /**
     * Handles form submission after location selection.
     * This triggers the report generation.
     * @param locationId The ID of the selected location.
     */
    public void handleSubmit(String locationId) {
        System.out.println("Form submitted for location: " + locationId);
        handleViewReportRequest(locationId);
    }

    /**
     * Handler for sequence message: selectLocation(locationId)
     * @param locationId The ID of the selected location.
     */
    public void selectLocation(String locationId) {
        handleLocationSelection(locationId);
    }

    /**
     * Handler for sequence message: submitForm(locationId)
     * @param locationId The ID of the selected location.
     */
    public void submitForm(String locationId) {
        handleSubmit(locationId);
    }
}