package com.example.smos.app;

import com.example.smos.controller.StudentMonitoringController;
import com.example.smos.model.ThresholdEvaluator;
import com.example.smos.repository.IStudentRepository;
import com.example.smos.repository.StudentRepositoryImpl;
import com.example.smos.service.StudentMonitoringService;
import com.example.smos.view.StudentMonitoringView;

/**
 * Main application class to demonstrate the Student Monitoring System.
 * This class sets up the dependencies and simulates user interaction.
 */
public class Main {

    public static void main(String[] args) {
        // --- System Initialization (Dependency Injection) ---

        // 1. Data Access Layer
        StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();

        // 2. Domain/Business Logic Layer
        ThresholdEvaluator thresholdEvaluator = new ThresholdEvaluator();

        // 3. Application/Service Layer
        StudentMonitoringService studentMonitoringService = new StudentMonitoringService(studentRepository, thresholdEvaluator);

        // 4. Presentation Layer
        StudentMonitoringView studentMonitoringView = new StudentMonitoringView();
        StudentMonitoringController studentMonitoringController = new StudentMonitoringController(studentMonitoringService, studentMonitoringView);

        // --- Simulate User Interactions ---

        System.out.println("--- Starting Student Monitoring Application ---");

        // Scenario 1: Successful retrieval of monitoring report
        System.out.println("\n--- Scenario 1: Successful Report Generation ---");
        // Ensure repository does NOT simulate failure for this scenario
        studentRepository.setSimulateConnectionFailure(false);
        // Simulate 'Direction' clicking Student Monitoring button
        studentMonitoringController.requestStudentMonitoringReport(3, 4); // absenceThreshold=3, noteThreshold=4

        // Expected output for Scenario 1:
        // Students S001 (5 absences), S002 (8 notes), S004 (8 absences), S005 (10 notes) should be flagged.

        // Scenario 2: Server connection failure
        System.out.println("\n--- Scenario 2: Server Connection Failure ---");
        // Configure repository to simulate connection failure
        studentRepository.setSimulateConnectionFailure(true);
        // Simulate 'Direction' clicking Student Monitoring button
        studentMonitoringController.requestStudentMonitoringReport(2, 6); // absenceThreshold=2, noteThreshold=6

        // Scenario 3: Successful retrieval with different thresholds (no students meet criteria)
        System.out.println("\n--- Scenario 3: Successful Report (No students flagged) ---");
        studentRepository.setSimulateConnectionFailure(false);
        studentMonitoringController.requestStudentMonitoringReport(10, 15); // Very high thresholds

        System.out.println("\n--- Student Monitoring Application Finished ---");
    }
}