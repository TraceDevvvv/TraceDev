package com.example.delaysystem.view;

import com.example.delaysystem.controller.DelayRegistrationController;
import com.example.delaysystem.model.Student;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents the user interface for delay registration.
 * This class simulates a UI by printing to the console and accepting basic text input.
 */
public class DelayRegistrationView {
    // Controller associated with this view
    protected DelayRegistrationController controller;
    // Temporary storage for simulated delay inputs from the user
    private Map<String, Duration> simulatedStudentDelayInputs = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private String currentClassId; // To keep track of the class being viewed

    /**
     * Constructs a new DelayRegistrationView.
     *
     * @param controller The controller responsible for handling UI interactions.
     */
    public DelayRegistrationView(DelayRegistrationController controller) {
        this.controller = controller;
        // The controller's view reference needs to be set up in main for circular dependency.
        // For simplicity, we'll assume the controller is fully initialized elsewhere.
    }

    /**
     * Displays the initial registration screen for a given class.
     * As per sequence diagram, this triggers the loading of students.
     *
     * @param classId The ID of the class for which delays are to be registered.
     */
    public void displayDelayRegistrationScreen(String classId) {
        this.currentClassId = classId;
        System.out.println("--- Delay Registration for Class: " + classId + " ---");
        System.out.println("Loading students...");
        // As per sequence diagram: View -> Controller : loadClassStudents()
        controller.loadClassStudents();
    }

    /**
     * Displays a list of students to the user.
     * For simulation, it prints student details and prompts for delay selection.
     *
     * @param students The list of Student objects to display.
     */
    public void displayStudents(List<Student> students) {
        System.out.println("\nStudents in class " + currentClassId + ":");
        if (students.isEmpty()) {
            System.out.println("No students found for this class.");
            return;
        }

        // Reset temporary inputs for a new display session
        simulatedStudentDelayInputs.clear();

        for (Student student : students) {
            System.out.println("  [" + student.getStudentId() + "] " + student.getName());
            // Simulate user interaction: all students are available for selection
        }
        System.out.println("\\nPlease enter 'Y' for students who are delayed, or 'C' to cancel, or 'X' to simulate connection error, then press Enter.");
        System.out.println("Example: S001 Y, S002 N, S003 Y (Delay time assumed to be 15 min for Y)");

        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("C")) {
            System.out.println("[View] User chose to cancel.");
            controller.onCancelButtonClick(); // Call controller's cancel handler
            return;
        }
        
        if (input.equalsIgnoreCase("X")) {
             System.out.println("[View] Simulating connection error on next save attempt.");
             // This flag needs to be set in SmosServerConnector, which means we need a way to pass this.
             // For simplicity, let's assume the MainApplication handles setting this flag for testing purposes.
             // Here, we just proceed as if user selected, and the error will manifest later.
             // Or, we can have a static method on SmosServerConnector for this. Let's make a static method.
             com.example.delaysystem.connector.SmosServerConnector.setSimulateConnectionFailure(true);
             System.out.println("Proceeding with 'Y' for all students to trigger error.");
             for (Student student : students) {
                 // Automatically select all students as delayed for this test case
                 controller.onDelayCheckboxSelected(student.getStudentId(), true);
             }
        } else {
            String[] studentCho = input.split(",\\\\s*");
            for (String choice : studentCho) {
                String[] parts = choice.split("\\s+");
                if (parts.length == 2) {
                    String studentId = parts[0];
                    boolean isSelected = parts[1].equalsIgnoreCase("Y");
                    // As per sequence diagram: View -> Controller : onDelayCheckboxSelected()
                    controller.onDelayCheckboxSelected(studentId, isSelected);
                }
            }
        }
        
        System.out.println("\\nPress Enter to confirm delays for selected students, or 'C' to cancel.");
        String confirmInput = scanner.nextLine().trim();
        if (confirmInput.equalsIgnoreCase("C")) {
            System.out.println("[View] User chose to cancel during confirmation.");
            controller.onCancelButtonClick(); // Call controller's cancel handler
        } else {
            // As per sequence diagram: View -> Controller : onConfirmButtonClick()
            controller.onConfirmButtonClick();
        }
    }

    /**
     * Activates or deactivates delay input fields for a specific student.
     * In this console simulation, it just prints a message and updates internal state.
     *
     * @param studentId The ID of the student.
     * @param activated True to activate, false to deactivate.
     */
    public void activateDelayFields(String studentId, boolean activated) {
        System.out.println("  [UI] Delay fields for " + studentId + " " + (activated ? "ACTIVATED" : "DEACTIVATED"));
        if (activated) {
            // Assume user inputs a default delay of 15 minutes for demo purposes.
            // In a real UI, this would involve rendering input fields and getting actual input.
            simulatedStudentDelayInputs.put(studentId, Duration.ofMinutes(15));
            System.out.println("  [UI] (Simulated) Delay time for " + studentId + " set to 15 minutes.");
        } else {
            simulatedStudentDelayInputs.remove(studentId);
        }
    }

    /**
     * Retrieves the map of selected students and their corresponding delay durations.
     *
     * @return A map where keys are student IDs and values are Delay durations.
     */
    public Map<String, Duration> getSelectedDelayDataInput() {
        System.out.println("[View] Providing selected delay data input to Controller.");
        // Return a copy to prevent external modification of the view's internal state.
        return new HashMap<>(simulatedStudentDelayInputs);
    }

    /**
     * Displays a confirmation message to the user.
     *
     * @param message The message to display.
     */
    public void showConfirmationMessage(String message) {
        System.out.println("\n[SUCCESS] " + message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n[ERROR] " + message);
    }

    /**
     * Displays the initial screen again, typically after an operation is complete or cancelled.
     */
    public void showInitialScreen() {
        System.out.println("\n--- Returning to Initial Screen ---");
        System.out.println("You can now enter another class ID to register delays, or 'exit' to quit.");
        // In a real application, this might navigate back to a previous menu or reset the view.
    }
}