
package com.example.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

/**
 * Main class to demonstrate the functionality of the system.
 * It sets up the dependencies and simulates user interactions (Administrator).\
 * This acts as the entry point for the runnable application.
 */
public class Main {

    public static void main(String[] args) {
        // --- Dependency Injection / Setup ---\
        // Create low-level clients and serv
        TeachingArchiveDBClient dbClient = new TeachingArchiveDBClient();
        ErrodatiService errodatiService = new ErrodatiService();

        // Create repository implementation, injecting its dependencies
        ITeachingRepository teachingRepository = new TeachingRepositoryImpl(dbClient);

        // Create validator, injecting its dependencies
        TeachingValidator teachingValidator = new TeachingValidator(teachingRepository);

        // Create use case, injecting its dependencies
        InsertTeachingUseCase insertTeachingUseCase = new InsertTeachingUseCase(teachingRepository, teachingValidator);

        // Create presenter, injecting its dependencies
        AdministratorPresenter presenter = new AdministratorPresenter(insertTeachingUseCase, errodatiService);

        System.out.println("--- Application Started ---");
        System.out.println("Pre-condition: Administrator is logged in and list of teachings is displayed.");

        // --- Simulate Administrator Workflow ---\
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try {
            while (true) {
                System.out.println("\n--- Administrator Actions ---");
                System.out.println("1. Click 'New Teaching' button");
                System.out.println("2. Simulate saving a teaching");
                System.out.println("3. Simulate saving an INVALID teaching (empty name)");
                System.out.println("4. Simulate saving a teaching with an EXISTING name");
                System.out.println("5. Simulate saving a teaching with DATABASE CONNECTION ERROR");
                System.out.println("6. Simulate cancelling teaching creation");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");

                input = reader.readLine();

                if (input == null || input.equals("0")) {
                    System.out.println("Exiting application.");
                    break;
                }

                switch (input) {
                    case "1":
                        // Sequence Diagram: Administrator -> UI: clickNewTeachingButton()\
                        presenter.onNewTeachingClicked();
                        break;
                    case "2":
                        // Simulate Administrator fills out form and saves
                        System.out.print("Enter new teaching name (e.g., 'Physics 101'): ");
                        String validTeachingName = reader.readLine();
                        if (validTeachingName != null && !validTeachingName.trim().isEmpty()) {
                            presenter.onSaveTeaching(validTeachingName.trim());
                        } else {
                            System.out.println("Input cannot be empty. Please try option 3 for empty name.");
                        }
                        break;
                    case "3":
                        // Simulate Invalid Data: Empty name
                        System.out.println("Simulating saving with an empty teaching name...");
                        // Sequence Diagram: Administrator -> UI: fillsOutForm(name : "")\
                        presenter.onSaveTeaching("");
                        break;
                    case "4":
                        // Simulate Invalid Data: Existing name
                        // First, save a teaching to ensure one exists for the test
                        String existingTeachingName = "Existing Course";
                        System.out.println("First, saving '" + existingTeachingName + "' to ensure it exists for uniqueness test.");
                        presenter.onSaveTeaching(existingTeachingName);
                        
                        System.out.println("\nNow, attempting to save a teaching with the same name '" + existingTeachingName + "'...");
                        presenter.onSaveTeaching(existingTeachingName);
                        break;
                    case "5":
                        // Simulate Database Connection Error
                        System.out.println("Simulating a database connection error during save...");
                        dbClient.setSimulateConnectionErrorOnInsert(true); // Activate error simulation
                        presenter.onSaveTeaching("Course_DB_Error");
                        // Reset flag just in case (though it's reset internally after one use)
                        dbClient.setSimulateConnectionErrorOnInsert(false);
                        break;
                    case "6":
                        // Sequence Diagram: Administrator -> UI: clickCancelButton()\
                        presenter.onCancelTeachingCreation();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        }
        System.out.println("--- Application Ended ---");
    }
}
