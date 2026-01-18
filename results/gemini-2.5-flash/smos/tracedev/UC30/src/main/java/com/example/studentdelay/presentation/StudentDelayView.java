package com.example.studentdelay.presentation;

import com.example.studentdelay.dataaccess.StudentDelayDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

/**
 * StudentDelayView is a mock presentation layer component responsible for
 * displaying UI elements and capturing user input.
 * In a real application, this would be a Swing, JavaFX, or web UI component.
 */
public class StudentDelayView {
    private StudentDelayController controller; // Dependency injection for controller
    private List<StudentDelayDTO> currentDelayRecords; // Data currently displayed
    private final Scanner scanner = new Scanner(System.in);
    // Stores the last form data simulated to be entered by the user
    private AtomicReference<StudentDelayFormData> lastFormData = new AtomicReference<>(null);

    public StudentDelayView() {
        // Initialize with empty records
        this.currentDelayRecords = List.of();
    }

    /**
     * Sets the controller for this view, enabling interaction.
     *
     * @param controller The StudentDelayController instance.
     */
    public void setController(StudentDelayController controller) {
        this.controller = controller;
    }

    /**
     * Displays the delay registration form along with existing delay records.
     *
     * @param delayRecords A list of StudentDelayDTOs to display.
     */
    public void displayForm(List<StudentDelayDTO> delayRecords) {
        this.currentDelayRecords = delayRecords;
        System.out.println("\n--- Student Delay Registration Screen ---");
        System.out.println("Currently displayed date: " + (lastFormData.get() != null ? lastFormData.get().selectedDate : LocalDate.now()));
        System.out.println("Existing Delay Records:");
        if (delayRecords.isEmpty()) {
            System.out.println("  No records found for this date.");
        } else {
            delayRecords.forEach(dto ->
                System.out.println("  ID: " + dto.id + ", Student: " + dto.studentName + " (" + dto.studentId +
                                   "), Reason: " + dto.reason + ", Entry: " + dto.entryTimestamp)
            );
        }
        System.out.println("\n--- Enter New Delay Information (or type 'exit' to quit) ---");
        System.out.println("To simulate user input, this method only displays records and waits for clickSave().");
        System.out.println("When clickSave() is called, it will use predefined data or prompt for it once.");
    }

    /**
     * Simulates getting form data from the user.
     * This implementation provides predefined data or prompts once.
     *
     * @return A StudentDelayFormData object containing the simulated user input.
     */
    public StudentDelayFormData getFormData() {
        // Simulate user entering data
        if (lastFormData.get() == null) {
            System.out.println("View: Simulating user input for new delay record...");
            System.out.print("  Enter Student ID (e.g., S003): ");
            String studentId = scanner.nextLine();
            if ("exit".equalsIgnoreCase(studentId)) {
                return null;
            }
            System.out.print("  Enter Delay Reason (e.g., Doctor appointment): ");
            String reason = scanner.nextLine();
            // Assuming the selectedDate on the form is the one currently displayed.
            LocalDate date = lastFormData.get() != null ? lastFormData.get().selectedDate : LocalDate.now();

            StudentDelayFormData formData = new StudentDelayFormData(date, studentId, reason);
            lastFormData.set(formData); // Store for subsequent calls if needed by controller logic
            System.out.println("View: Captured form data: " + formData);
        } else {
            System.out.println("View: Re-using last captured form data: " + lastFormData.get());
        }
        return lastFormData.get();
    }

    /**
     * Displays a success message to the user.
     *
     * @param message The success message to show.
     */
    public void showSuccessMessage(String message) {
        System.out.println("VIEW SUCCESS: " + message);
        // Clear last form data after successful save
        lastFormData.set(null);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to show.
     */
    public void showErrorMessage(String message) {
        System.err.println("VIEW ERROR: " + message);
    }

    /**
     * Simulates the user clicking the "Save" button on the form.
     * This triggers the controller to process the form data.
     */
    public void clickSave() {
        System.out.println("View: User clicked Save.");
        // Retrieve form data internally before passing to controller
        StudentDelayFormData formData = getFormData();
        if (formData != null) {
            if (controller != null) {
                controller.saveDelayData(formData);
            } else {
                showErrorMessage("Controller not set for View!");
            }
        } else {
            showErrorMessage("Form data could not be retrieved.");
        }
    }

    /**
     * Displays an updated log entry, typically for a newly added record.
     *
     * @param delayDTO The StudentDelayDTO representing the updated/new record.
     */
    public void displayUpdatedLog(StudentDelayDTO delayDTO) {
        System.out.println("\nVIEW: Displaying updated log for new entry:");
        System.out.println("  NEW: ID: " + delayDTO.id + ", Student: " + delayDTO.studentName + " (" + delayDTO.studentId +
                           "), Reason: " + delayDTO.reason + ", Entry: " + delayDTO.entryTimestamp);
        // In a real UI, this would update a list or table component
        // For simulation, we'll just show the new entry.
    }

    /**
     * Allows setting a specific date for form data simulation.
     */
    public void simulateFormDataForDate(LocalDate date) {
        // This will be used by the Controller to initialize the screen.
        // The getFormData() method will then use this date.
        lastFormData.set(new StudentDelayFormData(date, null, null));
    }
}