package com.example.digitalregister.view;

import com.example.digitalregister.model.DigitalRegister;
import java.util.List;
import java.util.Scanner; // Used for simulating user input in main application

/**
 * Represents the user interface for displaying and interacting with digital registers.
 * This class simulates UI interactions by printing to the console.
 */
public class RegisterView {

    // Dummy scanner to simulate user input. In a real UI, this would be handled by event listeners.
    private final Scanner scanner = new Scanner(System.in);
    private boolean cancellationRequested = false; // Flag to simulate user cancellation

    /**
     * Displays a message indicating that the academic year selection screen is shown.
     * Flow of Events 2: System shows screen for selection of academic year.
     */
    public void displayAcademicYearSelection() {
        System.out.println("\n--- Register View: Display Academic Year Selection ---");
        System.out.println("Please select an academic year to view digital registers (e.g., AY2023-2024, AY2022-2023):");
        System.out.println("Type 'cancel' to interrupt the operation.");
    }

    /**
     * Simulates getting the selected academic year ID from the user.
     * In a real UI, this would be an event from a dropdown or text field.
     * For this simulation, we'll return a hardcoded value or take simple input from main.
     *
     * @return The selected academic year ID.
     */
    public String getSelectedAcademicYearId() {
        System.out.print("Your selection (e.g., AY2023-2024): ");
        String input = scanner.nextLine(); // This is a blocking call in a real console app.
        if ("cancel".equalsIgnoreCase(input.trim())) {
            cancellationRequested = true;
            return null; // Indicate cancellation
        }
        return input.trim();
    }

    /**
     * Displays a list of digital registers to the user.
     * Flow of Events 5: System displays digital registers.
     * @param registers The list of DigitalRegister objects to display.
     */
    public void displayRegisters(List<DigitalRegister> registers) {
        System.out.println("\n--- Register View: Displaying Digital Registers ---");
        if (registers == null || registers.isEmpty()) {
            System.out.println("No digital registers found for the selected academic year.");
            return;
        }
        System.out.println("Found " + registers.size() + " digital registers:");
        for (DigitalRegister register : registers) {
            System.out.println(" - " + register.getDetails());
        }
        System.out.println("----------------------------------------------");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n--- Register View: ERROR ---");
        System.err.println("An error occurred: " + message);
        System.err.println("-------------------------");
    }

    /**
     * Checks if the user has requested to cancel the operation.
     * @return True if cancellation was requested, false otherwise.
     */
    public boolean isCancellationRequested() {
        return cancellationRequested;
    }

    /**
     * Resets the cancellation flag.
     */
    public void resetCancellation() {
        this.cancellationRequested = false;
    }
}