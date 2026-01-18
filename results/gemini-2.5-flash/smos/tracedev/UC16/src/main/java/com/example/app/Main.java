
package com.example.app;

import com.example.app.application.DeleteClassUseCaseController;
import com.example.app.domain.ArchivedClass;
import com.example.app.infrastructure.ClassRepositoryImpl;
import com.example.app.infrastructure.ConnectionInterruptedException;
import com.example.app.presentation.AdministratorView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Main application class to demonstrate the "Delete Class from Archive" use case.
 * This class sets up the dependencies and simulates the Administrator's interactions
 * as described in the sequence diagram, including various error scenarios.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Setup initial data
        Map<String, ArchivedClass> initialClasses = new HashMap<>();
        initialClasses.put("class101", new ArchivedClass("class101", "Introduction to Java", "Basic Java concepts", new Date(), new Date()));
        initialClasses.put("class102", new ArchivedClass("class102", "Advanced Algorithms", "Complex algorithms", new Date(), new Date()));
        initialClasses.put("class103", new ArchivedClass("class103", "Database Design", "Relational database modeling", new Date(), new Date()));
        initialClasses.put("class104", new ArchivedClass("class104", "Software Testing", "Quality assurance principles", new Date(), new Date()));

        // 2. Instantiate Infrastructure Layer
        ClassRepositoryImpl classRepository = new ClassRepositoryImpl(initialClasses);

        // 3. Instantiate Application/Service Layer
        DeleteClassUseCaseController deleteClassUseCaseController = new DeleteClassUseCaseController(classRepository);

        // 4. Instantiate Presentation Layer
        AdministratorView administratorView = new AdministratorView(deleteClassUseCaseController);

        System.out.println("--- Initial State of Classes ---");
        try {
            administratorView.displayUpdatedClassList(classRepository.findAll());
        } catch (ConnectionInterruptedException e) {
            System.err.println("Error displaying initial class list: " + e.getMessage());
        }


        // --- Scenario 1: Successful Deletion ---
        System.out.println("\n==============================================");
        System.out.println("Scenario 1: Successful Deletion of Class 101");
        System.out.println("==============================================");

        // Simulate entry conditions (REQ-003)
        // Traceability: m1 (Entry Conditions: Class selected, Delete button available)
        System.out.println("\nNote over Admin, UI: Entry Conditions:");
        System.out.println("Note over Admin, UI: - Administrator IS logged in");
        System.out.println("Note over Admin, UI: - A Class IS selected (ID: class101)");
        System.out.println("Note over Admin, UI: - Detailed information for the selected Class IS displayed");
        System.out.println("Note over Admin, UI: - Delete button IS available for the selected Class");

        administratorView.setSelectedClassId("class101");
        try {
            administratorView.displayClassDetails(classRepository.findById("class101")); // Simulate displaying details
        } catch (ConnectionInterruptedException e) {
            System.err.println("Error displaying details for class101: " + e.getMessage());
        }
        administratorView.onDeleteButtonClick();


        // --- Scenario 2: Deletion Fails (Integrity Issue) ---
        System.out.println("\n==============================================");
        System.out.println("Scenario 2: Deletion Failure (Integrity Issue) for Class 102");
        System.out.println("==============================================");
        // Reset state and introduce failure flag
        classRepository = new ClassRepositoryImpl(initialClasses); // Re-initialize with original data
        deleteClassUseCaseController = new DeleteClassUseCaseController(classRepository);
        administratorView = new AdministratorView(deleteClassUseCaseController);

        classRepository.setSimulateDeletionFailure(true); // Activate integrity failure simulation

        try {
            administratorView.displayClassDetails(classRepository.findById("class102"));
        } catch (ConnectionInterruptedException e) {
            System.err.println("Error displaying details for class102: " + e.getMessage());
        }
        administratorView.onDeleteButtonClick();

        // Verify Class 102 was NOT deleted due to rollback
        System.out.println("\n--- State after integrity failure attempt (Class 102 should still exist) ---");
        try {
            administratorView.displayUpdatedClassList(classRepository.findAll());
        } catch (ConnectionInterruptedException e) {
            System.err.println("Error displaying class list after integrity failure attempt: " + e.getMessage());
        }
        classRepository.setSimulateDeletionFailure(false); // Deactivate simulation


        // --- Scenario 3: Connection Interruption during Delete ---
        System.out.println("\n==============================================");
        System.out.println("Scenario 3: Connection Interruption during Delete for Class 103");
        System.out.println("==============================================");
        // Reset state
        classRepository = new ClassRepositoryImpl(initialClasses);
        deleteClassUseCaseController = new DeleteClassUseCaseController(classRepository);
        administratorView = new AdministratorView(deleteClassUseCaseController);

        classRepository.setSimulateConnectionInterruptOnDelete(true); // Activate connection interrupt simulation

        try {
            administratorView.displayClassDetails(classRepository.findById("class103"));
        } catch (ConnectionInterruptedException e) {
            System.err.println("Error displaying details for class103: " + e.getMessage());
        }
        administratorView.onDeleteButtonClick();

        // Verify Class 103 was NOT deleted
        System.out.println("\n--- State after connection interruption during delete (Class 103 should still exist) ---");
        try {
            administratorView.displayUpdatedClassList(classRepository.findAll());
        } catch (ConnectionInterruptedException e) {
            System.err.println("Error displaying class list after connection interruption during delete: " + e.getMessage());
        }
        classRepository.setSimulateConnectionInterruptOnDelete(false); // Deactivate simulation


        // --- Scenario 4: Connection Interruption during FindAll (after successful Delete) ---
        System.out.println("\n==============================================");
        System.out.println("Scenario 4: Connection Interruption during FindAll (after deleting Class 104)");
        System.out.println("==============================================");
        // Reset state
        classRepository = new ClassRepositoryImpl(initialClasses);
        deleteClassUseCaseController = new DeleteClassUseCaseController(classRepository);
        administratorView = new AdministratorView(deleteClassUseCaseController);

        classRepository.setSimulateConnectionInterruptOnFindAll(true); // Activate connection interrupt for findAll

        try {
            administratorView.displayClassDetails(classRepository.findById("class104"));
        } catch (ConnectionInterruptedException e) {
            System.err.println("Error displaying details for class104: " + e.getMessage());
        }
        administratorView.onDeleteButtonClick();

        // Verify Class 104 WAS deleted, but list refresh failed
        System.out.println("\n--- State after successful delete but findAll interruption (Class 104 should NOT exist) ---");
        // We're calling findAll directly on the repository to verify deletion occurred despite the UI error.
        System.out.println("Main: Verifying repository state directly:");
        try {
            administratorView.displayUpdatedClassList(classRepository.findAll());
        } catch (Exception e) { // This catch block was already present, keeping it as is.
            System.err.println("Main: Could not retrieve repository state for verification: " + e.getMessage());
        }
        classRepository.setSimulateConnectionInterruptOnFindAll(false); // Deactivate simulation
    }
}
