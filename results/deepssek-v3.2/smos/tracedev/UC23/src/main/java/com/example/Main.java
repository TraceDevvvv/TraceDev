package com.example;

/**
 * Main class to run the application and simulate the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Simulate the sequence diagram steps
        System.out.println("=== Simulating Teaching Insertion Use Case ===\n");

        // Step: Administrator authenticates
        AuthenticationService authService = new AuthenticationService();
        Session session = authService.authenticate("admin", "password");
        System.out.println("Administrator authenticated.\n");

        // Step: Initialize UI and set session
        TeachingFormUI ui = new TeachingFormUI();
        ui.setSession(session);
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setUi(ui);

        // Step: Administrator views technology (entry condition)
        System.out.println("--- Entry Condition: Viewing Technology ---");
        ui.viewTechnology();
        System.out.println();

        // Step: Administrator clicks "New Teaching" button (entry condition)
        System.out.println("--- Entry Condition: Click 'New Teaching' Button ---");
        ui.onNewTeachingClicked();
        System.out.println();

        // Step: Administrator fills form with teaching name
        System.out.println("--- Filling Form ---");
        TeachingForm form = new TeachingForm();
        form.setTeachingName("Mathematics 101");
        ui.fillForm(form.getTeachingName());
        System.out.println();

        // Simulate both cancellation and save paths (comment/uncomment to test)
        boolean simulateCancellation = false; // Change to true to test cancellation

        if (simulateCancellation) {
            // Step: Administrator cancels operation (exit condition)
            System.out.println("--- Cancelling Operation ---");
            ui.onCancelClicked();
            System.out.println("Operation cancelled by administrator.\n");
        } else {
            // Step: Administrator clicks "Save" button
            System.out.println("--- Saving Teaching ---");
            ui.onSaveClicked(form);
            System.out.println();
        }

        System.out.println("=== Simulation Complete ===");
    }
}