package com.example.justification;

import com.example.justification.application.StudentJustificationService;
import com.example.justification.application.dto.AbsenceDisplayDTO;
import com.example.justification.application.dto.JustificationStatus;
import com.example.justification.infrastructure.AbsenceDbRepository;
import com.example.justification.presentation.StudentJustificationController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class to run the demonstration of the student justification system.
 * This class simulates the Administrator and UI interactions.
 */
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("--- Student Justification System Demonstration ---");

        // --- System Initialization (Dependency Injection) ---
        // Create repository implementation
        AbsenceDbRepository absenceRepository = new AbsenceDbRepository();
        // Create service with repository
        StudentJustificationService studentJustificationService = new StudentJustificationService(absenceRepository);
        // Create controller with service
        StudentJustificationController studentJustificationController = new StudentJustificationController(studentJustificationService);

        // --- Simulate Admin & UI Interactions ---
        // Precondition R3: Administrator is logged in.
        System.out.println("\n(R3) Administrator is logged in.");
        // Precondition R4: Administrator has completed registration.
        System.out.println("(R4) Administrator has completed registration.");

        Scanner scanner = new Scanner(System.in);
        String studentIdToView = "s123"; // Example student ID

        // m1: Admin -> UI: requests to view justifications for student (studentId)
        System.out.println("\nAdmin -> UI: requests to view justifications for student (ID: " + studentIdToView + ")");
        // m2: UI -> UI: Administrator has selected a student and initiated the function.
        System.out.println("[UI] Administrator has selected student '" + studentIdToView + "' and initiated the function.");

        // UI -> Controller: viewStudentJustifications(studentId)
        List<AbsenceDisplayDTO> justifications = studentJustificationController.viewStudentJustifications(studentIdToView);

        // Controller --> UI: List<AbsenceDisplayDTO> (this is the return value)
        // The UI needs to "display" these justifications.

        // R12: Alt Connection to SMOS server interrupted by Administrator
        System.out.println("\nSimulating connection status check...");
        boolean connectionInterrupted = false; // Set to true to simulate interruption
        if (new java.util.Random().nextBoolean()) { // Randomly simulate interruption
             connectionInterrupted = true;
        }

        if (connectionInterrupted) {
            System.out.println("Admin -> UI: Interrupt connection request");
            System.err.println("[UI] Displaying error message: Connection to SMOS server interrupted. Please try again.");
            System.out.println("[UI] Exiting application due to interruption.");
            // UI --> Admin : Display error message / Exit application
        } else {
            // m14: Controller -> UI: displayJustifications(absences : List<AbsenceDisplayDTO>)
            // m15: UI -> UI: UI displays the list. It highlights absences based on AbsenceDisplayDTO.status
            // m16: UI -> Admin: Displays list of justifications for the selected student.
            displayJustifications(justifications, studentIdToView);
        }

        scanner.close();
        System.out.println("\n--- Demonstration End ---");
    }

    /**
     * Simulates the UI's action of displaying the list of justifications.
     * This method corresponds to sequence diagram messages m14, m15, and m16.
     *
     * @param justifications The list of AbsenceDisplayDTOs to display.
     * @param studentIdToView The ID of the student whose justifications are being displayed.
     */
    private static void displayJustifications(List<AbsenceDisplayDTO> justifications, String studentIdToView) {
        System.out.println("\n[UI] Received list of justifications from controller. Processing for display...");
        System.out.println("\n[UI] Displaying list of justifications for student: " + studentIdToView);
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-12s | %-20s | %-20s | %s%n",
                "Absence ID", "Date", "Reason", "Status", "Details");
        System.out.println("--------------------------------------------------------------------------------------------------");

        if (justifications.isEmpty()) {
            System.out.println("No absences found for student " + studentIdToView + " in the current year.");
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (AbsenceDisplayDTO dto : justifications) {
                String statusColor = "";
                String resetColor = "";
                // R8, R11: UI processes and displays the list, highlighting based on status
                if (dto.status == JustificationStatus.JUSTIFIED) {
                    statusColor = "\u001B[32m"; // Green
                } else if (dto.status == JustificationStatus.NEEDS_JUSTIFICATION) {
                    statusColor = "\u001B[31m"; // Red
                }
                resetColor = "\u001B[0m"; // Reset color

                System.out.printf("%-10s | %-12s | %-20s | %s%-20s%s | %s%n",
                        dto.absenceId,
                        dateFormat.format(dto.date),
                        dto.reason,
                        statusColor, dto.status, resetColor,
                        dto.justificationDetails != null ? dto.justificationDetails : "N/A");
            }
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
    }
}