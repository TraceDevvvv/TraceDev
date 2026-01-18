package com.example.report;

/**
 * Application class
 * Serves as the entry point to demonstrate the sequence diagram's flow.
 * It initializes all components and simulates user interactions.
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("--- Starting Application ---");

        // 1. Initialize Repositories
        ILocationRepository locationRepository = new LocationRepository();
        ISiteFeedbackRepository siteFeedbackRepository = new SiteFeedbackRepository();

        // 2. Initialize ReportGenerator
        ReportGenerator reportGenerator = new ReportGenerator();

        // 3. Initialize ReportService with dependencies
        ReportService reportService = new ReportService(locationRepository, siteFeedbackRepository, reportGenerator);

        // 4. Initialize ReportViewController with ReportService
        ReportViewController reportViewController = new ReportViewController(reportService);

        // Simulate Agency Operator (AO) interactions based on Sequence Diagram

        // AO -> RVC: onActivateFeature()
        reportViewController.onActivateFeature();

        // Simulate AO selecting a location and submitting the form
        // We'll try with a valid ID first, then maybe simulate the error by rerunning or using a loop.
        String selectedLocationId = "loc1"; // Example: Central Park

        // AO -> RVC: onLocationSelected(selectedLocationId)
        reportViewController.onLocationSelected(selectedLocationId);

        // AO -> RVC: onSubmitForm(selectedLocationId)
        System.out.println("\n--- Simulating User Submitting Form for " + selectedLocationId + " ---");
        reportViewController.onSubmitForm(selectedLocationId);

        // Simulate another attempt with a different ID
        selectedLocationId = "loc2"; // Example: Eiffel Tower
        System.out.println("\n--- Simulating User Submitting Form for " + selectedLocationId + " ---");
        reportViewController.onLocationSelected(selectedLocationId);
        reportViewController.onSubmitForm(selectedLocationId);

        // To demonstrate the error path, you might need to run the application multiple times
        // or introduce a loop that keeps trying until an error occurs.
        // For this single run, we rely on the random error simulation in ReportService.
        System.out.println("\n--- End of Application Demonstration ---");
        System.out.println("Note: Connection interruption errors are simulated randomly in ReportService.");
        System.out.println("      You may need to run the application a few times to see the error message.");
    }
}