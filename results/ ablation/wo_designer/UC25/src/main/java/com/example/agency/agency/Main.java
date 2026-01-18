package com.example.agency.agency;

import com.example.agency.model.Location;
import com.example.agency.model.SiteFeedback;
import com.example.agency.model.StatisticalReport;
import com.example.agency.service.LocationService;
import com.example.agency.service.ReportService;
import com.example.agency.service.SiteFeedbackService;

import java.util.List;
import java.util.Scanner;

/**
 * Main class to simulate the statistical report use case.
 * Agency Operator can select a location and view a statistical report.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Agency Operator Statistical Report System");
        System.out.println("==========================================");

        // Simulate agency login (Entry Condition: Agency HAS logged)
        System.out.println("Agency Operator is logged in.");

        // Step 1: Agency Operator activates the feature on the statistical report.
        System.out.println("\nActivating statistical report feature...");

        // Step 2 & 3: System uploads and displays the list of places.
        LocationService locationService = new LocationService();
        List<Location> locations = locationService.getAllLocations();
        if (locations.isEmpty()) {
            System.out.println("No locations available. Exiting.");
            return;
        }

        System.out.println("\nAvailable Locations:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i).getName());
        }

        // Step 4: Agency Operator selects a location.
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice < 1 || choice > locations.size()) {
            System.out.print("\nSelect a location (enter number): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > locations.size()) {
                    System.out.println("Invalid selection. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        Location selectedLocation = locations.get(choice - 1);
        System.out.println("Selected location: " + selectedLocation.getName());

        // Step 5: Agency Operator submits the form.
        System.out.println("\nSubmitting form for " + selectedLocation.getName() + "...");

        // Step 6 & 7: System uploads midsize site feedback via SearchSite use case.
        SiteFeedbackService siteFeedbackService = new SiteFeedbackService();
        List<SiteFeedback> feedbacks = siteFeedbackService.getSiteFeedbackForLocation(selectedLocation.getId());
        if (feedbacks.isEmpty()) {
            System.out.println("No site feedback available for this location.");
            return;
        }

        // Step 8: System prepares the statistical report.
        ReportService reportService = new ReportService();
        StatisticalReport report = reportService.generateReport(selectedLocation, feedbacks);

        // Step 9: System displays the statistical report.
        System.out.println("\n--- Statistical Report for " + selectedLocation.getName() + " ---");
        System.out.println(report);
        System.out.println("\nReport generated successfully. Exiting.");

        scanner.close();
    }
}