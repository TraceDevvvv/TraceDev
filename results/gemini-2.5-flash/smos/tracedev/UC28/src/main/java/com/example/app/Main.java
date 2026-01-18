package com.example.app;

import com.example.app.application.RegisterService;
import com.example.app.infrastructure.*;
import com.example.app.presentation.RegisterDetailsController;
import com.example.app.presentation.RegisterDetailsView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Main application class to demonstrate the interaction between components.
 * This sets up the dependencies and simulates user interaction.
 */
public class Main {
    public static void main(String[] args) {
        // --- Dependency Injection / Object Assembly ---
        // Infrastructure Layer
        IRegisterRepository registerRepository = new RegisterRepositoryImpl();
        IStudentRepository studentRepository = new StudentRepositoryImpl();
        IAttendanceRepository attendanceRepository = new AttendanceRepositoryImpl();
        IJustificationRepository justificationRepository = new JustificationRepositoryImpl();
        IDisciplinaryNoteRepository disciplinaryNoteRepository = new DisciplinaryNoteRepositoryImpl();

        // Application Layer
        RegisterService registerService = new RegisterService(
                registerRepository,
                studentRepository,
                attendanceRepository,
                justificationRepository,
                disciplinaryNoteRepository
        );

        // Presentation Layer
        RegisterDetailsView registerDetailsView = new RegisterDetailsView();
        RegisterDetailsController registerDetailsController = new RegisterDetailsController(
                registerService,
                registerDetailsView
        );

        // --- Simulation of Use Case: Viewing a Class Register's Details ---

        System.out.println("Simulating 'Viewing a Class Register's Details' Use Case.");
        System.out.println("Precondition: Administrator IS logged in.");
        System.out.println("Precondition: Administrator HAS completed Use Case 'ViewingElCoregistri' and a register selected.");

        Scanner scanner = new Scanner(System.in);
        String registerId = "REG001"; // Example register ID from mock data

        System.out.println("\n--- Scenario 1: Successful retrieval of Register Details ---");
        System.out.println("Admin: Requesting details for Register ID: " + registerId);
        registerService.setSimulateSMOSInterruption(false); // Ensure no interruption for this scenario
        registerDetailsView.onDetailsButtonClick(registerId); // Admin initiates action

        System.out.println("\n--- Scenario 2: Register Not Found (assuming service handles this gracefully) ---");
        String nonExistentRegisterId = "REG999";
        System.out.println("Admin: Requesting details for NON-EXISTENT Register ID: " + nonExistentRegisterId);
        registerService.setSimulateSMOSInterruption(false);
        registerDetailsView.onDetailsButtonClick(nonExistentRegisterId);

        System.out.println("\n--- Scenario 3: Simulate SMOS server connection IS interrupted ---");
        System.out.println("Admin: Requesting details for Register ID: " + registerId + " with simulated server interruption.");
        registerService.setSimulateSMOSInterruption(true); // Simulate interruption
        registerDetailsView.onDetailsButtonClick(registerId); // Admin initiates action

        System.out.println("\n--- End of Simulation ---");

        // Example of calling R11 method
        System.out.println("\n--- Simulating R11: Adding a new Justification (simplified DTO) ---");
        // Note: The JustificationDTO in the Class Diagram is for *display*.
        // For 'addJustification', a more complete DTO or direct parameters would be needed.
        // We make assumptions here for demonstrative purposes as per implementation rules.
        String dummyStudentName = "John Doe"; // Placeholder for studentName
        String dummyReason = "Doctor's appointment";
        registerService.addJustification(new com.example.app.dto.JustificationDTO(null, dummyStudentName, dummyReason, null));

        // Example of calling R12 method
        System.out.println("\n--- Simulating R12: Adding a new Disciplinary Note (simplified DTO) ---");
        String dummyStudentName2 = "Jane Smith"; // Placeholder for studentName
        String dummyDescription = "Not completing homework for 3 consecutive days.";
        String dummyType = "Formal Warning";
        registerService.addDisciplinaryNote(new com.example.app.dto.DisciplinaryNoteDTO(null, dummyStudentName2, dummyDescription, dummyType));

        scanner.close();
    }
}