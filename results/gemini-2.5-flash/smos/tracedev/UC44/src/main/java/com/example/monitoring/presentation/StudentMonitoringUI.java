package com.example.monitoring.presentation;

import com.example.monitoring.application.StudentMonitoringDTO;
import com.example.monitoring.application.StudentMonitoringService;
import com.example.monitoring.infrastructure.DatabaseConnectionException;

import java.util.List;

/**
 * Presentation Layer: Handles user interaction and displays student monitoring data.
 * Acts as the entry point for the "Student Monitoring" feature.
 */
public class StudentMonitoringUI {
    // Dependency: StudentMonitoringService to handle application logic
    private StudentMonitoringService studentMonitoringService;

    /**
     * Constructor for StudentMonitoringUI.
     * @param studentMonitoringService The application service to interact with.
     */
    public StudentMonitoringUI(StudentMonitoringService studentMonitoringService) {
        this.studentMonitoringService = studentMonitoringService;
    }

    /**
     * Simulates the user clicking the "Student Monitoring" button.
     * Initiates the process of requesting and displaying monitoring data.
     */
    public void requestMonitoringData() {
        System.out.println("Administrator clicked 'Student Monitoring' button.");

        try {
            // Attempt to get student data exceeding thresholds
            List<StudentMonitoringDTO> monitoringData = studentMonitoringService.getStudentsExceedingThresholds();

            // If data is successfully retrieved, display it
            displayMonitoringData(monitoringData);

        } catch (DatabaseConnectionException e) {
            // Handle the case where the database connection is interrupted
            displaySystemUnavailable(); // Corresponds to sequence diagram message m19
            System.err.println("Error details: " + e.getMessage());
            // In a real application, this might show a user-friendly dialog.
        } catch (SecurityException e) {
            // Handle authentication/authorization failure
            displayAccessDenied(); // Corresponds to sequence diagram message m4
            System.err.println("Error details: " + e.getMessage());
        } catch (Exception e) {
            // Catch any other unexpected errors
            System.err.println("An unexpected error occurred: " + e.getMessage());
            System.err.println("Administrator: Display 'An unexpected error occurred. Please contact support.'");
        }
    }

    /**
     * Displays the provided list of student monitoring data to the user.
     * @param data A list of StudentMonitoringDTO objects to display.
     */
    public void displayMonitoringData(List<StudentMonitoringDTO> data) {
        System.out.println("\n--- Student Monitoring Data ---");
        if (data.isEmpty()) {
            System.out.println("No students found exceeding thresholds for the current year.");
        } else {
            for (StudentMonitoringDTO dto : data) {
                System.out.printf("Student ID: %s, Name: %s, Absences: %d, Notes: %d%n",
                        dto.getStudentId(), dto.getStudentName(), dto.getAbsences(), dto.getNotes());
            }
        }
        System.out.println("-----------------------------");
    }

    /**
     * Displays an "Access Denied" message to the administrator.
     * This method explicitly represents the sequence diagram message m4 "Display 'Access Denied'".
     */
    private void displayAccessDenied() {
        System.out.println("Administrator: Display 'Access Denied'");
    }

    /**
     * Displays a "System Unavailable - Please try again later" message to the administrator.
     * This method explicitly represents the sequence diagram message m19 "Display 'System Unavailable - Please try again later'".
     */
    private void displaySystemUnavailable() {
        System.err.println("Administrator: Display 'System Unavailable - Please try again later'");
    }

    /**
     * Main method to run the application (for demonstration purposes).
     * Initializes the necessary components and triggers the UI flow.
     */
    public static void main(String[] args) {
        // --- Infrastructure Layer Initialization ---
        // Create an error logger
        com.example.monitoring.infrastructure.ConsoleErrorLogger errorLogger = new com.example.monitoring.infrastructure.ConsoleErrorLogger();
        // Create a mock database connection. Set 'simulateFailure = true' to test error handling.
        com.example.monitoring.infrastructure.mock.DatabaseConnection mockDbConnection = new com.example.monitoring.infrastructure.mock.DatabaseConnection();
        // Create the student repository, injecting the mock connection and logger
        com.example.monitoring.infrastructure.StudentDatabaseRepository studentRepository =
                new com.example.monitoring.infrastructure.StudentDatabaseRepository(mockDbConnection, errorLogger);

        // --- Domain Layer Initialization ---
        // Initialize threshold configuration with example values
        com.example.monitoring.domain.ThresholdConfiguration thresholdConfig =
                new com.example.monitoring.domain.ThresholdConfiguration(5, 3);

        // --- Application Layer Initialization ---
        // Initialize authentication service
        com.example.monitoring.application.AuthService authService = new com.example.monitoring.application.AuthService();
        // Initialize the StudentMonitoringService with its dependencies
        StudentMonitoringService studentMonitoringService =
                new StudentMonitoringService(studentRepository, thresholdConfig, authService);

        // --- Presentation Layer Initialization ---
        // Initialize the UI with the application service
        StudentMonitoringUI ui = new StudentMonitoringUI(studentMonitoringService);

        // --- Simulate the use case flow ---
        System.out.println("--- Scenario 1: Successful Data Retrieval (Auth=true, DB=ok) ---");
        mockDbConnection.setSimulateConnectionFailure(false); // Ensure DB connection is successful
        authService.setAuthorized(true); // Ensure user is authorized
        ui.requestMonitoringData();

        System.out.println("\n--- Scenario 2: Access Denied (Auth=false) ---");
        authService.setAuthorized(false); // Simulate unauthorized user
        ui.requestMonitoringData();
        authService.setAuthorized(true); // Reset for next scenario

        System.out.println("\n--- Scenario 3: Database Connection Interrupted (DB=failure) ---");
        mockDbConnection.setSimulateConnectionFailure(true); // Simulate DB connection failure
        ui.requestMonitoringData();
        mockDbConnection.setSimulateConnectionFailure(false); // Reset for next scenario

        System.out.println("\n--- Scenario 4: No Students Exceeding Thresholds ---");
        // To simulate this, the mock DB should return an empty list
        mockDbConnection.setReturnEmptyList(true);
        ui.requestMonitoringData();
        mockDbConnection.setReturnEmptyList(false); // Reset for next scenario
    }
}