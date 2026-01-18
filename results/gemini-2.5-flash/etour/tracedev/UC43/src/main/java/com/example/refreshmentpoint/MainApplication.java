package com.example.refreshmentpoint;

import java.util.Scanner;

/**
 * Main application class to demonstrate the interaction between the components.
 * Simulates a user flow as described in the sequence diagram.
 */
public class MainApplication {

    public static void main(String[] args) {
        // Initialize dependencies
        NetworkStatusMonitor networkStatusMonitor = new NetworkStatusMonitor();
        IRefreshmentPointRepository repository = new RefreshmentPointRepositoryImpl(networkStatusMonitor);
        RefreshmentPointValidator validator = new RefreshmentPointValidator();
        RefreshmentPointService service = new RefreshmentPointService(repository, validator, networkStatusMonitor);
        RefreshmentPointForm form = new RefreshmentPointForm();
        AuthenticationService authService = new AuthenticationService();
        RefreshmentPointController controller = new RefreshmentPointController(service, form, authService);

        Scanner scanner = new Scanner(System.in);
        String operatorId = "admin"; // EC1: Authenticated operator
        String testPointId = "RP001";
        RefreshmentPointDTO initialDTO = null;

        System.out.println("--- Starting Refreshment Point Edit Use Case Simulation ---");

        // --- SCENARIO 1: Successful Data Retrieval and Update ---

        System.out.println("\n--- Scenario 1: Successful Data Retrieval and Update ---");
        initialDTO = controller.requestEditForm(testPointId, operatorId);

        if (initialDTO != null) {
            // Step 5: Operator changes data in the form.
            // Simulate user interaction with form fields using the changeData method.
            form.changeData("Updated Coffee Corner", "Main Lobby - West Wing", "Premium Cafe");

            // Operator -> Controller: submitEditForm
            RefreshmentPointDTO updatedFormData = form.getData();
            controller.submitEditForm(testPointId, updatedFormData);

            // Simulate Operator confirming the operation
            System.out.print("Simulating user input: Confirm changes? (y/n): ");
            String confirmation = scanner.nextLine();
            if ("y".equalsIgnoreCase(confirmation)) {
                controller.confirmEditOperation(testPointId); // Step 10: Operator confirms operation
            } else {
                controller.cancelEditOperation(); // Operator cancels operation
            }
        }

        // --- SCENARIO 2: Invalid Form Submission ---
        System.out.println("\n--- Scenario 2: Invalid Form Submission ---");
        initialDTO = controller.requestEditForm("RP002", operatorId); // Request edit for another point

        if (initialDTO != null) {
            // Simulate user entering invalid data (e.g., empty name)
            form.changeData("", "New Location", "Snack Bar"); // Empty name
            controller.submitEditForm("RP002", form.getData()); // Submit with invalid data
            // Expected: displayErrors will be called by the form
        }

        // --- SCENARIO 3: ETOUR Connection Interruption during Retrieval (XC3) ---
        System.out.println("\n--- Scenario 3: ETOUR Connection Interruption during Retrieval ---");
        networkStatusMonitor.setConnected(false); // Simulate connection loss
        controller.requestEditForm("RP003", operatorId);
        networkStatusMonitor.setConnected(true); // Restore connection for next scenarios

        // --- SCENARIO 4: ETOUR Connection Interruption during Update (XC3) ---
        System.out.println("\n--- Scenario 4: ETOUR Connection Interruption during Update ---");
        initialDTO = controller.requestEditForm("RP001", operatorId); // Re-fetch RP001
        if (initialDTO != null) {
            form.changeData("Connectivity Test Cafe", "Ground Floor", "Cafe");
            controller.submitEditForm("RP001", form.getData());

            // Simulate confirmation
            System.out.print("Simulating user input: Confirm changes (with connection loss soon)? (y/n): ");
            String confirmation = scanner.nextLine();
            if ("y".equalsIgnoreCase(confirmation)) {
                networkStatusMonitor.setConnected(false); // Simulate connection loss JUST BEFORE finalize
                controller.confirmEditOperation("RP001");
                networkStatusMonitor.setConnected(true);
            } else {
                controller.cancelEditOperation();
            }
        }

        // --- SCENARIO 5: Unauthenticated Operator (EC1) ---
        System.out.println("\n--- Scenario 5: Unauthenticated Operator ---");
        controller.requestEditForm("RP001", "unauthorizedUser"); // Try with unauthenticated operator

        System.out.println("\n--- Simulation Complete ---");
        scanner.close();
    }
}