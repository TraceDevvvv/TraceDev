package com.example.views;

import com.example.models.RefreshmentPoint;
import com.example.serv.RefreshmentPointService;
import com.example.serv.ServerConnectionException;
import java.util.Optional;
import java.util.Scanner;

/**
 * Console-based view for displaying RefreshmentPoint details.
 */
public class RefreshmentPointCardView {
    private RefreshmentPointService service;
    private Scanner scanner;

    public RefreshmentPointCardView(RefreshmentPointService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the details of a selected refreshment point.
     * @param pointId the id of the refreshment point
     */
    public void displayPointDetails(String pointId) {
        System.out.println("=== View Refreshment Point Details ===");
        try {
            Optional<RefreshmentPoint> optionalPoint = service.getRefreshmentPointDetails(pointId);
            if (optionalPoint.isPresent()) {
                RefreshmentPoint point = optionalPoint.get();
                System.out.println("\n--- Details ---");
                System.out.println("ID: " + point.getId());
                System.out.println("Name: " + point.getName());
                System.out.println("Address: " + point.getAddress());
                System.out.println("Contact: " + point.getContactNumber());
                System.out.println("Hours: " + point.getOperationalHours());
                System.out.println("Capacity: " + point.getCapacity());
                System.out.println("Description: " + point.getDescription());
                System.out.println("----------------");
            } else {
                System.out.println("No refreshment point found with ID: " + pointId);
            }
        } catch (ServerConnectionException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Exit condition met: Connection to server ETOUR is interrupted.");
        }
    }

    /**
     * Interactive method to select a point and view details.
     */
    public void interactiveSelectAndView() {
        System.out.println("\nAvailable Point IDs: RP001, RP002, RP003");
        System.out.print("Enter the ID of the refreshment point to view: ");
        String selectedId = scanner.nextLine().trim();
        if (!selectedId.isEmpty()) {
            displayPointDetails(selectedId);
        } else {
            System.out.println("No ID entered.");
        }
    }

    public void close() {
        scanner.close();
    }
}