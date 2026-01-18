package com.example.studentdelay;

import com.example.studentdelay.dataaccess.SMOSServerConnector;
import com.example.studentdelay.dataaccess.StudentDelayRepositoryImpl;
import com.example.studentdelay.domain.NotificationService;
import com.example.studentdelay.application.StudentDelayService;
import com.example.studentdelay.presentation.StudentDelayController;
import com.example.studentdelay.presentation.StudentDelayView;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Main class to demonstrate the student delay registration system.
 * It sets up the dependencies and simulates user interaction.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Student Delay Registration System Simulation...");

        // 1. Initialize Data Access Layer components
        SMOSServerConnector smosServerConnector = new SMOSServerConnector();
        StudentDelayRepositoryImpl studentDelayRepository = new StudentDelayRepositoryImpl(smosServerConnector);

        // 2. Initialize Domain Layer components
        NotificationService notificationService = new NotificationService();

        // 3. Initialize Application Layer components
        StudentDelayService studentDelayService = new StudentDelayService(studentDelayRepository, notificationService);

        // 4. Initialize Presentation Layer components
        StudentDelayView studentDelayView = new StudentDelayView();
        StudentDelayController studentDelayController = new StudentDelayController(studentDelayService);

        // Establish dependencies between Presentation layer components
        studentDelayView.setController(studentDelayController);
        studentDelayController.setView(studentDelayView);

        // --- Simulate Administrator Actions ---
        System.out.println("\n--- SIMULATION START ---");
        Scanner consoleScanner = new Scanner(System.in);
        LocalDate selectedDate = LocalDate.now(); // Administrator selects today's date

        // Flow of Events 1: System updates the screen displayed according to the date.
        System.out.println("\nAdministrator: Initializing screen for date: " + selectedDate);
        studentDelayController.initializeScreen(selectedDate);

        // Flow of Events 2 & 3: User fills out form, then clicks save.
        System.out.println("\nAdministrator: Simulating user entering data and clicking 'Save'...");
        System.out.println("Please provide input when prompted by the 'View'. Type 'exit' to quit simulation.");

        // Loop to allow multiple saves or exit
        while (true) {
            System.out.print("\nPress Enter to simulate clicking 'Save' or type 'exit' to quit: ");
            String input = consoleScanner.nextLine();
            if ("exit".equalsIgnoreCase(input.trim())) {
                break;
            }
            studentDelayView.clickSave(); // This will trigger getFormData and saveDelayData
            // After saving, the screen is re-initialized by the controller,
            // so we'll see the updated list for the same date.
        }

        System.out.println("\n--- SIMULATION END ---");
        consoleScanner.close();
        System.out.println("Student Delay Registration System Simulation Finished.");
    }
}