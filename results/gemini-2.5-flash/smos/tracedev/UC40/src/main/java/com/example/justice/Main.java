package com.example.justice;

import com.example.justice.controller.AdminController;
import com.example.justice.domain.Justice;
import com.example.justice.repository.impl.JusticeRepositoryImpl;
import com.example.justice.service.JusticeApplicationService;
import com.example.justice.validation.JusticeValidator;
import com.example.justice.view.JusticeUpdateFormView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Main class to demonstrate the flow of the Justice update use case.
 * This class simulates the Administrator and orchestrates the interaction
 * between different layers of the application.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Initialize all components (Dependency Injection setup)
        System.out.println("--- Initializing Application Components ---");
        JusticeRepositoryImpl justiceRepository = new JusticeRepositoryImpl();
        JusticeApplicationService justiceApplicationService = new JusticeApplicationService(justiceRepository);
        JusticeValidator justiceValidator = new JusticeValidator();

        // AdminController needs a reference to the view for callbacks (e.g., showSuccessMessage)
        // We'll create the view after the controller to resolve this circular dependency for injection.
        // For simplicity in this demo, let's create it this way, or we could use interfaces/factories.
        // Let's create a placeholder for view and inject it later.
        JusticeUpdateFormView justiceUpdateFormView = new JusticeUpdateFormView(null); // Will set controller later
        AdminController adminController = new AdminController(justiceApplicationService, justiceValidator, justiceUpdateFormView);
        justiceUpdateFormView = new JusticeUpdateFormView(adminController); // Re-instantiate with controller

        // To make sure the view gets the correct controller reference if created this way
        // In a real framework (Spring, etc.), this would be handled by the DI container.
        // For this demo, we'll manually set the controller on the view after both are instantiated.
        // (This would typically be done via a method in JusticeUpdateFormView or by injecting the controller into view's constructor.)
        // Since JusticeUpdateFormView constructor already takes AdminController, let's just make sure AdminController is fully initialized before passed.
        // A simple way to handle this in a non-framework scenario is to initialize the view *after* the controller and then pass the controller.
        // Or make AdminController pass itself to the view. Let's use the explicit constructor injection for clarity.
        // The first `new JusticeUpdateFormView(null)` was a temporary hack; the second one is the correct one.
        adminController = new AdminController(justiceApplicationService, justiceValidator, justiceUpdateFormView); // Re-create with correct view reference

        System.out.println("--- Application Components Initialized ---\n");

        Scanner scanner = new Scanner(System.in);
        String justiceIdToUpdate = "J101"; // Pre-existing ID for demonstration

        // --- Simulate Initial Display (Entry Conditions) ---
        System.out.println("--- Simulating Entry Condition: Administrator logs in and views Justice details ---");
        Justice initialJustice = justiceApplicationService.getJusticeDetails(justiceIdToUpdate);
        justiceUpdateFormView.displayJusticeDetails(initialJustice);

        // --- Simulate Admin interaction (Flow of Events) ---
        System.out.println("--- Simulating Administrator modifying and saving Justice details ---");

        // First attempt: Successful update
        System.out.println("\n--- Scenario 1: Successful Update ---");
        // Administrator changes fields and clicks "Save"
        // The triggerUpdate method in view simulates this user action.
        // For this demo, we'll manually set the scanner input to simulate user typing valid data.
        System.out.println("Please enter a new valid date (e.g., 2024-07-20) for Justice ID " + justiceIdToUpdate + ":");
        // In a real console app, you'd type. Here for demonstration, we will mock input or use actual input.
        // For automated testing or fixed scenarios, you might use a ByteArrayInputStream.
        // For an interactive demo, System.in is fine.
        justiceUpdateFormView.triggerUpdate(justiceIdToUpdate);

        // Verify the update
        System.out.println("\n--- Verifying Update for Justice ID " + justiceIdToUpdate + " ---");
        Justice updatedJustice = justiceApplicationService.getJusticeDetails(justiceIdToUpdate);
        justiceUpdateFormView.displayJusticeDetails(updatedJustice);

        // Second attempt: Validation failure (e.g., null date)
        System.out.println("\n--- Scenario 2: Validation Failure (Invalid Date) ---");
        System.out.println("Please enter an INVALID date (e.g., an empty string or 'abc') for Justice ID " + justiceIdToUpdate + ":");
        justiceUpdateFormView.triggerUpdate(justiceIdToUpdate);

        // Verify that the justice object was NOT updated in this scenario
        System.out.println("\n--- Verifying Justice ID " + justiceIdToUpdate + " state after failed update ---");
        Justice justiceAfterFailedAttempt = justiceApplicationService.getJusticeDetails(justiceIdToUpdate);
        justiceUpdateFormView.displayJusticeDetails(justiceAfterFailedAttempt);


        // --- Simulating trying to update a non-existent Justice ---
        System.out.println("\n--- Scenario 3: Update non-existent Justice ---");
        String nonExistentJusticeId = "J999";
        System.out.println("Please enter a valid date (e.g., 2024-08-01) for non-existent Justice ID " + nonExistentJusticeId + ":");
        justiceUpdateFormView.triggerUpdate(nonExistentJusticeId);


        scanner.close();
        System.out.println("\n--- Demonstration Complete ---");
    }
}