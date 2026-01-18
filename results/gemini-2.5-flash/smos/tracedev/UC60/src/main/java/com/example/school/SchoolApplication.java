package com.example.school;

import com.example.school.connector.SMOSServerConnector;
import com.example.school.controller.StudentController;
import com.example.school.repository.ArchiveStudentRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.service.AuthenticationService;
import com.example.school.service.SecurityService;
import com.example.school.service.StudentService;
import com.example.school.view.StudentView;

/**
 * Main application class to demonstrate the student information system.
 * This class is responsible for setting up the dependency graph and running
 * the main use case defined by the sequence diagram.
 */
public class SchoolApplication {

    public static void main(String[] args) { // This `main` method acts as the `System UI` participant.
        // --- System Initialization (Dependency Injection / IoC Container setup in a real app) ---
        System.out.println("--- Initializing School Application ---");

        // 1. Core Serv (independent or lowest level dependencies first)
        SecurityService securityService = new SecurityService();
        AuthenticationService authenticationService = new AuthenticationService();

        // 2. Connector (uses SecurityService)
        SMOSServerConnector smosServerConnector = new SMOSServerConnector(securityService);

        // 3. Repository (uses SMOSServerConnector)
        StudentRepository studentRepository = new ArchiveStudentRepository(smosServerConnector);

        // 4. Service (uses StudentRepository)
        StudentService studentService = new StudentService(studentRepository);

        // 5. View (uses SecurityService)
        StudentView studentView = new StudentView(securityService);

        // 6. Controller (uses StudentService, StudentView, AuthenticationService, SecurityService)
        StudentController studentController = new StudentController(
                studentService,
                studentView,
                authenticationService,
                securityService
        );

        System.out.println("--- Application Initialization Complete ---\n");

        // --- Simulate User Interaction ---
        // SD: Student -> System : initiates "Digital Log" function (m1)
        String studentIdToDisplay = "S1001"; // Example student ID

        System.out.println("System UI: Initiating 'Digital Log' function for student " + studentIdToDisplay + " (Trace: m1)");
        System.out.println("Note: Entry Condition: Student IS logged in (handled by AuthenticationService simulation) (Trace: m2)");

        // SD: System -> Controller : displayStudentClassRegistry(studentId)
        studentController.displayStudentClassRegistry(studentIdToDisplay);

        System.out.println("\n--- Testing another scenario (e.g., unauthorized student) ---");
        String unauthorizedStudentId = "S9999";
        System.out.println("System UI: Initiating 'Digital Log' function for student " + unauthorizedStudentId + " (Trace: m1)");
        System.out.println("Note: Entry Condition: Student IS logged in (handled by AuthenticationService simulation) (Trace: m2)");
        studentController.displayStudentClassRegistry(unauthorizedStudentId);

        System.out.println("\n--- Testing another scenario (e.g., student with access but no data) ---");
        String studentWithNoData = "S1002"; // Let's assume S1002 is authenticated and authorized but SMOS has limited data
        System.out.println("System UI: Initiating 'Digital Log' function for student " + studentWithNoData + " (Trace: m1)");
        System.out.println("Note: Entry Condition: Student IS logged in (handled by AuthenticationService simulation) (Trace: m2)");
        studentController.displayStudentClassRegistry(studentWithNoData);

        System.out.println("\n--- Application Shutdown ---");
        smosServerConnector.disconnect(); // Clean up connection
    }
}