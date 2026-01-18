package com.example.app;

import com.example.app.controller.UserDetailController;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserApplicationService;
import com.example.app.view.UserDetailView;

/**
 * Main application class to demonstrate the functionality of the
 * User Details module based on the provided Class and Sequence Diagrams.
 * This class sets up the dependencies and simulates user interactions.
 *
 * Preconditions for the use case (R1, R2) are noted here:
 * R1: Administrator is logged in.
 * R2: System is displaying a list of users.
 * (These preconditions are assumed to be handled by other system components
 * and are not directly implemented in this module, but are prerequisites
 * for the 'clicksDetailsButton' action.)
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting User Details Application Simulation...\n");

        // 1. Initialize Data Access Layer
        UserRepository userRepository = new UserRepository();

        // 2. Initialize Application Service Layer, injecting repository dependency
        UserApplicationService userApplicationService = new UserApplicationService(userRepository);

        // 3. Initialize Presentation Layer (Controller and View)
        // The UserDetailController creates its own UserDetailView (composition),
        // and passes itself to the view for event handling.
        UserDetailController userDetailController = new UserDetailController(userApplicationService);
        // Get the view instance from the controller to simulate UI interactions.
        UserDetailView userDetailView = userDetailController.getUserDetailView();

        System.out.println("Components initialized. Simulating user interactions...\n");

        // --- Scenario 1: Successful User Details Retrieval ---
        System.out.println("--- Scenario 1: Successful User Details Retrieval (ID: user123) ---");
        userRepository.setSimulateConnectionError(false); // Ensure no connection error
        String userId1 = "user123";
        userDetailView.clicksDetailsButton(userId1); // Administrator clicks details button

        // --- Scenario 2: User Not Found ---
        System.out.println("\n--- Scenario 2: User Not Found (ID: nonExistentUser) ---");
        userRepository.setSimulateConnectionError(false); // Ensure no connection error
        String userId2 = "nonExistentUser";
        userDetailView.clicksDetailsButton(userId2);

        // --- Scenario 3: Connection Error during Retrieval ---
        System.out.println("\n--- Scenario 3: Connection Error (ID: admin456) ---");
        userRepository.setSimulateConnectionError(true); // Simulate a connection error
        String userId3 = "admin456";
        userDetailView.clicksDetailsButton(userId3);

        System.out.println("\nSimulation finished.");
    }
}