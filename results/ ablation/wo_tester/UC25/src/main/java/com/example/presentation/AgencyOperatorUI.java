package com.example.presentation;

import com.example.domain.Place;
import com.example.domain.StatisticalReport;
import com.example.domain.Location;
import java.util.List;

/**
 * Boundary class representing the user interface for agency operator.
 * Quality Requirement N/A – No specific quality constraints apply.
 */
public class AgencyOperatorUI {
    private GenerateStatisticalReportController controller;

    public AgencyOperatorUI(GenerateStatisticalReportController controller) {
        this.controller = controller;
    }

    /**
     * Simulates activation of "Generate Report" feature by the actor.
     * This triggers the controller to handle the request.
     */
    public void activateGenerateReportFeature() {
        controller.handleGenerateReportRequest();
    }

    public void displayPlaceList(List<Place> places) {
        System.out.println("Displaying list of places:");
        for (Place place : places) {
            System.out.println(" - " + place.getName() + " (ID: " + place.getId() + ")");
        }
    }

    public void renderReport(StatisticalReport report) {
        System.out.println("=== Statistical Report ===");
        System.out.println("Location ID: " + report.getLocation().getPlaceId());
        System.out.println("Total Feedback: " + report.getTotalFeedback());
        System.out.println("Average Rating: " + report.getAverageRating());
    }

    /**
     * Simulates the UI getting the selected location from the operator.
     * In a real UI, this would come from user input.
     */
    public Location getSelectedLocation() {
        // For simulation, we return a dummy location.
        // In the sequence diagram, this is called internally.
        Place dummyPlace = new Place("place1", "Sample Place");
        return Location.createFromPlace(dummyPlace);
    }

    public void showError(String message) {
        System.err.println("UI Error: " + message);
    }

    public void selectLocationAndSubmitForm() {
        Location selectedLocation = getSelectedLocation();
        controller.handleGenerateReportRequest(selectedLocation);
    }

    public void displayAuthenticationError() {
        showError("Authentication failed. Please log in.");
    }

    public void displayErrorMessage(String message) {
        showError(message);
    }
}