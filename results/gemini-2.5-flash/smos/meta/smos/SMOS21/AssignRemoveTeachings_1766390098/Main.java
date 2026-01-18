// Main.java
package com.example.teachingsystem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main class to simulate the program execution and user interaction for the AssignRemoveTeachings use case.
 * This class orchestrates the flow, simulating user actions and system responses.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static TeachingManagementService service;

    public static void main(String[] args) {
        service = new TeachingManagementService();

        System.out.println("--- Starting Assign/Remove Teachings Use Case Simulation ---");

        // Precondition 1: The user is logged in to the system as an administrator
        simulateAdminLogin();
        if (!service.isAdminLoggedIn()) {
            System.out.println("Simulation aborted: Administrator login failed.");
            return;
        }

        // Precondition 2 & 3: The user has made the case of use "viewdettaglizzizzo"
        // and displays the detailed information of an address.
        String selectedAddressId = selectAddressToView();
        if (selectedAddressId == null) {
            System.out.println("Simulation aborted: No address selected or invalid address ID.");
            return;
        }

        Address currentAddress = service.viewAddressDetails(selectedAddressId);
        if (currentAddress == null) {
            System.out.println("Simulation aborted: Could not retrieve address details.");
            return;
        }

        // Precondition 4: The user clicks on the "Teachings Address" button
        System.out.println("\n--- User clicks on 'Teachings Address' button ---");

        // System Event 1: Displays a form from which you can add and / or remove lessons at the address.
        Map<String, Object> formData = service.displayAssignmentForm(selectedAddressId);
        if (formData == null) {
            System.out.println("Simulation aborted: Failed to display assignment form.");
            return;
        }

        // User Event 2: Select the teachings to be assigned or removed
        Set<String> teachingsToAdd = new HashSet<>();
        Set<String> teachingsToRemove = new HashSet<>();

        // Simulate user input for teachings to add
        System.out.println("\nEnter Teaching IDs to ADD (comma-separated, e.g., T001,T005). Press Enter for none:");
        String addInput = scanner.nextLine().trim();
        if (!addInput.isEmpty()) {
            teachingsToAdd = Arrays.stream(addInput.split(","))
                                   .map(String::trim)
                                   .filter(s -> !s.isEmpty())
                                   .collect(Collectors.toSet());
        }

        // Simulate user input for teachings to remove
        System.out.println("Enter Teaching IDs to REMOVE (comma-separated, e.g., T002,T003). Press Enter for none:");
        String removeInput = scanner.nextLine().trim();
        if (!removeInput.isEmpty()) {
            teachingsToRemove = Arrays.stream(removeInput.split(","))
                                      .map(String::trim)
                                      .filter(s -> !s.isEmpty())
                                      .collect(Collectors.toSet());
        }

        // User Event 3: Click on the "Send" button
        System.out.println("\n--- User clicks on 'Send' button ---");

        // System Event 4: Associate or removes selected teachings at the address.
        boolean success = service.assignRemoveTeachings(selectedAddressId, teachingsToAdd, teachingsToRemove);

        // System Event 5: Back to display details of the address.
        System.out.println("\n--- System returns to display details of the address ---");
        service.viewAddressDetails(selectedAddressId); // Display updated details

        // Postconditions:
        // The administrator interrupts the operation connection to the SMOS server interrupted
        // (Simulated by calling interruptOperation, or implicitly by program end)
        // The system has changed the teachings relating to the address (already printed by service method)
        if (!success) {
            System.out.println("\nOperation did not result in changes or encountered an error.");
            // Simulate interruption if operation failed or user chooses to
            System.out.println("Do you want to simulate administrator interrupting the operation? (yes/no)");
            String interruptChoice = scanner.nextLine().trim().toLowerCase();
            if ("yes".equals(interruptChoice)) {
                service.interruptOperation();
            }
        } else {
            System.out.println("\nTeachings updated successfully. Simulation complete.");
        }

        scanner.close();
        System.out.println("--- End of Simulation ---");
    }

    /**
     * Simulates the administrator login process.
     */
    private static void simulateAdminLogin() {
        System.out.println("Simulating Administrator Login:");
        System.out.print("Enter Administrator ID (e.g., admin1): ");
        String adminId = scanner.nextLine();
        System.out.print("Enter Password (any non-empty string): ");
        String password = scanner.nextLine();

        service.loginAdministrator(adminId, password);
    }

    /**
     * Simulates the user selecting an address to view its details.
     *
     * @return The ID of the selected address, or null if selection is invalid.
     */
    private static String selectAddressToView() {
        System.out.println("\nAvailable Addresses (for 'viewdettaglizzizzo' precondition):");
        // In a real system, this would come from a list provided by the service
        System.out.println("  A001: Main Campus");
        System.out.println("  A002: Branch Office A");
        System.out.println("  A003: Downtown Center");
        System.out.print("Enter Address ID to view details (e.g., A001): ");
        String addressId = scanner.nextLine().trim();

        // Basic validation to ensure it's one of the known sample IDs
        if (!service.getAddressById(addressId).getAddressId().equals(addressId)) { // Using getAddressById to check existence
            System.out.println("Invalid Address ID entered.");
            return null;
        }
        return addressId;
    }
}