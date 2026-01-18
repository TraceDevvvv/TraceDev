package com.example;

import java.util.Scanner;

/**
 * Main application class to demonstrate the flow.
 */
public class Main {
    public static void main(String[] args) {
        // --- Dependency Injection / Object Graph Assembly ---\n        // For simplicity, we are manually creating objects. In a real application,\n        // a DI framework (e.g., Spring, Guice) would handle this.\n
        // Data Access Layer
        Object dummyDbConnection = new Object(); // Simulating a DB connection
        SMOSGateway smosGateway = new SMOSGateway(); // R15
        IClassRepository classRepository = new ClassRepository(dummyDbConnection, smosGateway);

        // Domain Layer
        ClassValidator classValidator = new ClassValidator();
        ErrodatiUseCaseHandler errodatiUseCaseHandler = new ErrodatiUseCaseHandler(); // R12

        // Application/Service Layer
        ErrorNotificationService errorNotificationService = new ErrorNotificationService(errodatiUseCaseHandler);
        InsertClassService insertClassService = new InsertClassService(classRepository, classValidator, errorNotificationService);
        AuthenticationService authenticationService = new AuthenticationService(); // R3
        SessionManager sessionManager = new SessionManager();                     // R3

        // Presentation Layer
        ClassListView classListView = new ClassListView(); // R4, R5
        
        // Handle circular dependency between AdministratorController and ClassForm
        // 1. Create controller instance first, passing null for ClassForm initially.
        AdministratorController administratorController = new AdministratorController(
                null, insertClassService, authenticationService, sessionManager, classListView);
        
        // 2. Create ClassForm, passing the controller instance.
        ClassForm classForm = new ClassForm(administratorController);
        
        // 3. Set the ClassForm instance back to the controller.
        administratorController.setClassForm(classForm);


        // --- Simulate Preconditions (R3, R4, R5) ---
        // Manually authenticate and create a session for the dummy admin.
        if (authenticationService.authenticate("admin", "password")) {
            sessionManager.createSession("admin");
            System.out.println("\nMain: Admin is authenticated and session created (R3).");
            classListView.displayClassList("2023-2024"); // R4, R5
            System.out.println("Main: System is viewing class list (R4, R5).");
        } else {
            System.err.println("Main: Failed to meet authentication precondition (R3). Exiting.");
            return;
        }

        // --- Simulate User Interaction for Class Creation Use Case ---
        System.out.println("\n--- Starting New Class Creation Use Case ---");
        // Admin -> Controller : handleNewClassButtonClick()
        administratorController.handleNewClassButtonClick();

        System.out.println("\n--- End of Use Case Simulation ---");
    }
}