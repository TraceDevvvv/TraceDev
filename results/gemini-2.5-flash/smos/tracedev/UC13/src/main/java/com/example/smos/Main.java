package com.example.smos;

import com.example.smos.application.ViewClassesUseCase;
import com.example.smos.infrastructure.ArchiveAcademicYearRepository;
import com.example.smos.infrastructure.ArchiveClassRepository;
import com.example.smos.presentation.ClassListController;
import com.example.smos.presentation.ClassListView;

import java.util.Scanner;

/**
 * Main application class to set up the MVC components and simulate user interactions
 * according to the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Setup Dependencies (Dependency Injection)
        // Repositories
        ArchiveAcademicYearRepository academicYearRepository = new ArchiveAcademicYearRepository();
        ArchiveClassRepository classRepository = new ArchiveClassRepository();

        // Use Case
        ViewClassesUseCase viewClassesUseCase = new ViewClassesUseCase(academicYearRepository, classRepository);

        // Presentation Layer
        ClassListController classListController = new ClassListController(viewClassesUseCase);
        ClassListView classListView = new ClassListView();

        // Link View to Controller and Controller to View
        classListView.setController(classListController);
        classListController.setView(classListView);

        Scanner mainScanner = new Scanner(System.in);
        int scenario = -1;

        while (scenario != 0) {
            System.out.println("\n--- SMOS Application Simulation ---");
            System.out.println("Select a scenario to run:");
            System.out.println("1. View Class List (Success)");
            System.out.println("2. View Class List (Academic Year Retrieval Failure - R12)");
            System.out.println("3. View Class List (Class Retrieval Failure - R12)");
            System.out.println("4. View Class List (Cancellation after year selection - R11)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                scenario = Integer.parseInt(mainScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            // Reset simulation flags
            academicYearRepository.setSimulateConnectionFailure(false);
            classRepository.setSimulateConnectionFailure(false);

            switch (scenario) {
                case 1:
                    System.out.println("\n--- Running Scenario 1: View Class List (Success) ---");
                    // Admin clicks 'Manage Classes'
                    classListView.onManageClassesClicked();
                    // User interacts via displayAcademicYearSelection and onAcademicYearSelected methods
                    // This path expects successful retrieval of both academic years and classes.
                    break;
                case 2:
                    System.out.println("\n--- Running Scenario 2: View Class List (Academic Year Retrieval Failure) ---");
                    academicYearRepository.setSimulateConnectionFailure(true);
                    classListView.onManageClassesClicked();
                    break;
                case 3:
                    System.out.println("\n--- Running Scenario 3: View Class List (Class Retrieval Failure) ---");
                    // Admin clicks 'Manage Classes' -> successful year selection
                    classListView.onManageClassesClicked();
                    // After academic year selection, simulate failure
                    classRepository.setSimulateConnectionFailure(true);
                    // The view will then attempt to call selectAcademicYear, which will now fail.
                    break;
                case 4:
                    System.out.println("\n--- Running Scenario 4: View Class List (Cancellation) ---");
                    // Admin clicks 'Manage Classes' -> display year selection
                    // User enters 'C' to cancel in the `displayAcademicYearSelection` method
                    classListView.onManageClassesClicked();
                    break;
                case 0:
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid scenario choice.");
                    break;
            }
            System.out.println("\nSimulation complete for scenario " + scenario + ".");
            // Give a moment before re-prompting
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        mainScanner.close();
    }
}