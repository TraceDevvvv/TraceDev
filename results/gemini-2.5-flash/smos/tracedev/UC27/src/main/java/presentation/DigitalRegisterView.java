package presentation;

import domain.AcademicYear;
import domain.DigitalRegister;

import java.util.List;
import java.util.Scanner; // For simulating user input

/**
 * The view component responsible for displaying UI and handling user input related to digital registers.
 * It interacts with the DigitalRegisterController to trigger actions.
 */
public class DigitalRegisterView {

    // The view holds a reference to its controller to send commands.
    private DigitalRegisterController controller;
    private Scanner scanner = new Scanner(System.in); // For simulating user input

    /**
     * Sets the controller for this view. This is a common way to inject the controller into the view.
     *
     * @param controller The DigitalRegisterController instance.
     */
    public void setController(DigitalRegisterController controller) {
        this.controller = controller;
    }

    /**
     * Displays a screen for selecting an academic year.
     *
     * @param academicYears A list of available academic years to display.
     */
    public void showYearSelectionScreen(List<AcademicYear> academicYears) {
        System.out.println("\n--- Academic Year Selection ---");
        if (academicYears.isEmpty()) {
            System.out.println("No academic years available.");
            return;
        }
        System.out.println("Please select an academic year:");
        for (AcademicYear year : academicYears) {
            System.out.printf("[%s] %d-%d\n", year.getId(), year.getYearNumber(), year.getYearNumber() + 1);
        }
        System.out.print("Enter Year ID (e.g., AY2023-2024): ");

        // Simulate user input
        // For actual running, a real GUI or web UI would handle this asynchronously.
        // For this console simulation, we will prompt and read.
        // The MainApp will simulate this call directly for the sequence diagram flow.
    }

    /**
     * Displays a list of digital registers.
     *
     * @param registers A list of DigitalRegister objects to display.
     */
    public void displayDigitalRegisters(List<DigitalRegister> registers) {
        System.out.println("\n--- Digital Registers ---");
        if (registers.isEmpty()) {
            System.out.println("No digital registers found for the selected year.");
            return;
        }
        for (DigitalRegister register : registers) {
            System.out.println("  ID: " + register.getRegisterId());
            System.out.println("  Title: " + register.getTitle());
            System.out.println("  Academic Year: " + register.getAcademicYearId());
            System.out.println("  Academic Class: " + register.getAcademicClassId());
            System.out.println("  Content: " + (register.getContent().length() > 50 ? register.getContent().substring(0, 50) + "..." : register.getContent()));
            System.out.println("-------------------------");
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.err.println("ERROR: " + message);
    }

    /**
     * Handles the event when the 'Digital Register' button is clicked.
     * This method acts as an entry point for user interaction, mapping to R4.
     * It then delegates the action to the controller.
     */
    public void onDigitalRegisterButtonClick() {
        System.out.println("[View] Digital Register button clicked. Triggering selection handling.");
        // Step 2: View calls controller to handle the selection
        if (controller != null) {
            controller.handleDigitalRegisterSelection();
        } else {
            displayError("Controller not set for DigitalRegisterView.");
        }
    }

    /**
     * Handles the event when an academic year is selected by the user.
     * This method acts as an entry point for user interaction, mapping to R6.
     * It then delegates the action to the controller.
     *
     * @param yearId The ID of the selected academic year.
     */
    public void onAcademicYearSelected(String yearId) {
        System.out.println("[View] Academic year '" + yearId + "' selected. Triggering register retrieval.");
        // Step 4: View calls controller to retrieve registers for the selected year
        if (controller != null) {
            controller.selectAcademicYear(yearId);
        } else {
            displayError("Controller not set for DigitalRegisterView.");
        }
    }

    /**
     * Closes the scanner to prevent resource leaks.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}