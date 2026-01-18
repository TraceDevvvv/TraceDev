
package com.example.app;

/**
 * Main application class to demonstrate the "Administrator Views Class Details" use case.
 * This class orchestrates the creation of objects and simulates the user interaction
 * as described in the sequence diagram.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting Class Details Application Demo ---");

        // 1. Instantiate core components
        SMOSConnectionManager smosConnectionManager = new SMOSConnectionManager();
        IClassRepository classRepository = new ClassRepositoryImpl(smosConnectionManager);
        ClassService classService = new ClassService(classRepository);
        SessionManager sessionManager = new SessionManager();
        SystemContext systemContext = new SystemContext();

        // 2. Instantiate View and Controller
        ClassDetailsView classDetailsView = new ClassDetailsView();
        ClassDetailsController classDetailsController = new ClassDetailsController(
                classService, classDetailsView, sessionManager, systemContext
        );
        // Link view to controller is handled in ClassDetailsController's constructor (setController)

        // 3. Optional: Instantiate ClassList components (R5 - precondition to display list)
        ClassListView classListView = new ClassListView();
        ClassListController classListController = new ClassListController(classService, classListView);

        // --- Scenario 1: Happy Path - Administrator views existing class details ---
        System.out.println("\n--- Scenario 1: Happy Path - View existing class details ---");
        // Preconditions for the sequence diagram:
        // - Administrator IS logged in (R3) - sessionManager.isAuthenticated() is true by default
        // - ViewingLancoclasses use case IS completed (R4) - systemContext.isViewingLancoclassesCompleted() is true by default
        // - List of classes of an academic year IS displayed (R5) - simulated
        classListController.showClassList("2023-2024"); // Simulate R5 precondition

        // Simulate Admin selecting a class and clicking 'Show details' (R6)
        // The view will then call the controller's showClassDetails method.
        classDetailsView.handleShowDetailsClick("CLASS001");

        // --- Scenario 2: Admin Not Logged In ---
        System.out.println("\n--- Scenario 2: Admin Not Logged In (R3) ---");
        sessionManager.setAuthenticated(false); // Simulate Admin not logged in
        classDetailsView.handleShowDetailsClick("CLASS002");
        sessionManager.setAuthenticated(true); // Reset for next scenarios

        // --- Scenario 3: Previous Use Case Not Completed ---
        System.out.println("\n--- Scenario 3: Previous Use Case Not Completed (R4) ---");
        systemContext.setViewingLancoclassesCompleted(false); // Simulate R4 precondition not met
        classDetailsView.handleShowDetailsClick("CLASS002");
        systemContext.setViewingLancoclassesCompleted(true); // Reset for next scenarios

        // --- Scenario 4: Connection Interrupted (R12) ---
        System.out.println("\n--- Scenario 4: Connection Interrupted (R12) ---");
        smosConnectionManager.setConnected(false); // Simulate connection error
        // The compilation error "exception ConnectionException is never thrown" indicates that
        // classDetailsView.handleShowDetailsClick is not declared to throw a checked ConnectionException.
        // Therefore, the try-catch block for ConnectionException is invalid and must be removed
        // to fix the compilation error in Main.java without modifying external classes.
        classDetailsView.handleShowDetailsClick("CLASS001");
        smosConnectionManager.setConnected(true); // Reset connection

        // --- Scenario 5: Class Not Found ---
        System.out.println("\n--- Scenario 5: Class Not Found ---");
        classDetailsView.handleShowDetailsClick("NONEXISTENT_CLASS");

        System.out.println("\n--- Class Details Application Demo Finished ---");
    }
}
