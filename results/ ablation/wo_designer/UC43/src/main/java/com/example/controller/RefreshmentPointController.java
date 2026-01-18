// Controller to manage the flow of updating refreshment point data
package com.example.controller;

import com.example.model.RefreshmentPoint;
import com.example.service.RefreshmentPointService;
import java.util.Scanner;

public class RefreshmentPointController {
    private RefreshmentPointService pointService;
    private Scanner scanner;
    private boolean isAuthenticated;
    private boolean isInMainView;

    public RefreshmentPointController() {
        pointService = new RefreshmentPointService();
        scanner = new Scanner(System.in);
        // Simulating entry conditions (assuming they are met for this run)
        isAuthenticated = true;
        isInMainView = true;
    }

    // Main method to execute the use case
    public void executeUpdateProcess() {
        try {
            // Entry condition check
            if (!isAuthenticated || !isInMainView) {
                System.out.println("Entry conditions not met. Operation aborted.");
                return;
            }

            System.out.println("Welcome, Restaurant Point Operator.");
            System.out.print("Enter the Refreshment Point ID to edit: ");
            String pointId = scanner.nextLine();

            // Check if point data is available for editing (entry condition)
            if (!pointService.isPointAvailableForEditing(pointId)) {
                System.out.println("Refreshment point data is not available for editing.");
                return;
            }

            // Step 1: Operator enables functionality (implied by starting this process)
            System.out.println("Functionality enabled. Loading point data...");

            // Step 2 & 3: Upload and display data
            RefreshmentPoint currentPoint = pointService.uploadPointData(pointId);
            System.out.println("Current Point Data:");
            displayPointData(currentPoint);

            // Step 4: Operator changes data
            RefreshmentPoint updatedPoint = getUpdatedDataFromOperator(currentPoint);

            // Step 5: Operator submits the form (implied by entering data)

            // Step 6: System verifies data
            if (!pointService.validatePointData(updatedPoint)) {
                System.out.println("Validation failed. Please check the entered data.");
                return;
            }

            // Step 7: Ask for confirmation
            System.out.print("Do you confirm the changes? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            // Step 8: Operator confirms
            if (!confirmation.equals("yes")) {
                System.out.println("Operation cancelled by operator.");
                return;
            }

            // Step 9: Store modified data
            pointService.updatePointData(updatedPoint);

            // Step 10: Notify success
            System.out.println("Success: The data of the selected refreshment point has been changed.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            // Simulating exit condition: connection interrupted
            System.out.println("The connection to the server ETOUR is interrupted.");
        } finally {
            scanner.close();
        }
    }

    // Helper to display point data in a readable format
    private void displayPointData(RefreshmentPoint point) {
        System.out.println("ID: " + point.getId());
        System.out.println("Name: " + point.getName());
        System.out.println("Location: " + point.getLocation());
        System.out.println("Description: " + point.getDescription());
        System.out.println("Seating Capacity: " + point.getSeatingCapacity());
        System.out.println("Operating Hours: " + point.getOperatingHours());
    }

    // Helper to collect updated data from operator via console
    private RefreshmentPoint getUpdatedDataFromOperator(RefreshmentPoint currentPoint) {
        System.out.println("\nEnter new values (press Enter to keep current value):");

        System.out.print("Name [" + currentPoint.getName() + "]: ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) {
            name = currentPoint.getName();
        }

        System.out.print("Location [" + currentPoint.getLocation() + "]: ");
        String location = scanner.nextLine();
        if (location.trim().isEmpty()) {
            location = currentPoint.getLocation();
        }

        System.out.print("Description [" + currentPoint.getDescription() + "]: ");
        String description = scanner.nextLine();
        if (description.trim().isEmpty()) {
            description = currentPoint.getDescription();
        }

        System.out.print("Seating Capacity [" + currentPoint.getSeatingCapacity() + "]: ");
        String capacityInput = scanner.nextLine();
        int seatingCapacity = currentPoint.getSeatingCapacity();
        if (!capacityInput.trim().isEmpty()) {
            try {
                seatingCapacity = Integer.parseInt(capacityInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Keeping current value.");
            }
        }

        System.out.print("Operating Hours [" + currentPoint.getOperatingHours() + "]: ");
        String operatingHours = scanner.nextLine();
        if (operatingHours.trim().isEmpty()) {
            operatingHours = currentPoint.getOperatingHours();
        }

        return new RefreshmentPoint(currentPoint.getId(), name, location, description, seatingCapacity, operatingHours);
    }
}