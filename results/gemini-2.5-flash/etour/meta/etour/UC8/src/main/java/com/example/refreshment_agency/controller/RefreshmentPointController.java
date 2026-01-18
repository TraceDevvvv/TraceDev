package com.example.refreshment_agency.controller;

import com.example.refreshment_agency.model.RefreshmentPoint;
import com.example.refreshment_agency.service.RefreshmentPointService;
import com.example.refreshment_agency.exception.InvalidDataException;
import com.example.refreshment_agency.exception.RefreshmentPointNotFoundException;
import com.example.refreshment_agency.util.InputReader;

import java.util.List;
import java.util.Optional;

/**
 * Controller class to handle user interactions and orchestrate the modification of refreshment point data.
 * It acts as an intermediary between the user interface (console in this case) and the service layer.
 */
public class RefreshmentPointController {
    private final RefreshmentPointService refreshmentPointService;
    private final InputReader inputReader;

    /**
     * Constructs a RefreshmentPointController with the given service and input reader.
     *
     * @param refreshmentPointService The service responsible for business logic related to refreshment points.
     * @param inputReader             The utility for reading user input from the console.
     */
    public RefreshmentPointController(RefreshmentPointService refreshmentPointService, InputReader inputReader) {
        this.refreshmentPointService = refreshmentPointService;
        this.inputReader = inputReader;
    }

    /**
     * Orchestrates the process of modifying refreshment point data.
     * This method guides the user through selecting a refreshment point,
     * entering new data, confirming changes, and handling various outcomes.
     */
    public void modifyRefreshmentPointData() {
        boolean continueModification = true;
        while (continueModification) {
            System.out.println("\n--- Modify Refreshment Point Data ---");
            displayRefreshmentPoints();

            System.out.print("Enter the ID of the refreshment point to modify (or 'exit' to quit): ");
            String idInput = inputReader.readLine();

            if ("exit".equalsIgnoreCase(idInput)) {
                continueModification = false;
                System.out.println("Exiting modification process.");
                break;
            }

            try {
                long selectedId = Long.parseLong(idInput);
                Optional<RefreshmentPoint> currentPointOptional = refreshmentPointService.getRefreshmentPointById(selectedId);

                if (currentPointOptional.isPresent()) {
                    RefreshmentPoint currentPoint = currentPointOptional.get();
                    System.out.println("\n--- Current Data for Refreshment Point ID: " + currentPoint.getId() + " ---");
                    System.out.println(currentPoint);

                    // Display form and get updated data
                    RefreshmentPoint updatedPoint = getUpdatedRefreshmentPointData(currentPoint);

                    // Ask for confirmation
                    System.out.print("Confirm changes? (yes/no): ");
                    String confirmation = inputReader.readLine();

                    if ("yes".equalsIgnoreCase(confirmation)) {
                        // Simulate blocking input controls (conceptual for console app)
                        System.out.println("Processing your request... (Input controls blocked)");
                        try {
                            RefreshmentPoint savedPoint = refreshmentPointService.updateRefreshmentPoint(selectedId, updatedPoint);
                            System.out.println("Modification successful! Updated Refreshment Point:");
                            System.out.println(savedPoint);
                        } catch (InvalidDataException e) {
                            // Activates the 'Errored' use case
                            displayError("Invalid data: " + e.getMessage() + ". Please check your input.");
                        } finally {
                            // Simulate unblocking input controls
                            System.out.println("(Input controls unblocked)");
                        }
                    } else {
                        refreshmentPointService.cancelOperation(); // Conceptual cancellation
                        System.out.println("Operation cancelled by operator.");
                    }
                } else {
                    displayError("Refreshment point with ID " + selectedId + " not found.");
                }
            } catch (NumberFormatException e) {
                displayError("Invalid ID format. Please enter a numeric ID.");
            } catch (RefreshmentPointNotFoundException e) {
                displayError(e.getMessage());
            } catch (Exception e) {
                // Catch any unexpected errors, including potential ETOUR server interruption
                displayError("An unexpected error occurred: " + e.getMessage());
                System.err.println("Error details: " + e.getClass().getName());
                // In a real system, this would involve more sophisticated error logging and handling
            }

            System.out.print("\nDo you want to modify another refreshment point? (yes/no): ");
            String continueChoice = inputReader.readLine();
            if (!"yes".equalsIgnoreCase(continueChoice)) {
                continueModification = false;
            }
        }
    }

    /**
     * Displays a list of all available refreshment points.
     * This simulates the output of the `SearchRefreshmentPoint` use case.
     */
    private void displayRefreshmentPoints() {
        System.out.println("\n--- Available Refreshment Points ---");
        List<RefreshmentPoint> points = refreshmentPointService.getRefreshmentPoints();
        if (points.isEmpty()) {
            System.out.println("No refreshment points found.");
        } else {
            points.forEach(System.out::println);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Prompts the user to enter updated data for a refreshment point.
     * This simulates displaying an edit form and capturing user input.
     *
     * @param currentPoint The refreshment point with its current data.
     * @return A new RefreshmentPoint object with the updated data.
     */
    private RefreshmentPoint getUpdatedRefreshmentPointData(RefreshmentPoint currentPoint) {
        System.out.println("\nEnter new data (press Enter to keep current value):");

        // For simplicity, we'll update all fields. In a real UI, only changed fields would be sent.
        // ID is not modifiable by user, it's used for identification.
        long id = currentPoint.getId();

        System.out.print("Name [" + currentPoint.getName() + "]: ");
        String name = inputReader.readLine();
        if (name.isEmpty()) {
            name = currentPoint.getName();
        }

        System.out.print("Address [" + currentPoint.getAddress() + "]: ");
        String address = inputReader.readLine();
        if (address.isEmpty()) {
            address = currentPoint.getAddress();
        }

        System.out.print("Contact Info [" + currentPoint.getContactInfo() + "]: ");
        String contactInfo = inputReader.readLine();
        if (contactInfo.isEmpty()) {
            contactInfo = currentPoint.getContactInfo();
        }

        int capacity = currentPoint.getCapacity();
        System.out.print("Capacity [" + currentPoint.getCapacity() + "]: ");
        String capacityInput = inputReader.readLine();
        if (!capacityInput.isEmpty()) {
            try {
                capacity = Integer.parseInt(capacityInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid capacity format. Keeping current capacity.");
            }
        }

        System.out.print("Serv Offered [" + currentPoint.getServOffered() + "]: ");
        String servOffered = inputReader.readLine();
        if (servOffered.isEmpty()) {
            servOffered = currentPoint.getServOffered();
        }

        System.out.print("Status (ACTIVE/INACTIVE) [" + currentPoint.getStatus() + "]: ");
        String statusInput = inputReader.readLine();
        RefreshmentPoint.Status status = currentPoint.getStatus();
        if (!statusInput.isEmpty()) {
            try {
                status = RefreshmentPoint.Status.valueOf(statusInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Keeping current status.");
            }
        }

        return new RefreshmentPoint(id, name, address, contactInfo, capacity, servOffered, status);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    private void displayError(String message) {
        System.err.println("ERROR: " + message);
    }
}