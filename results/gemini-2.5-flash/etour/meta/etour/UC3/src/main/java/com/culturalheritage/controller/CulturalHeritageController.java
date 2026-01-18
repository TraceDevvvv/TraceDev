package com.culturalheritage.controller;

import com.culturalheritage.model.CulturalHeritageObject;
import com.culturalheritage.service.CulturalHeritageService;
import com.culturalheritage.exception.CulturalHeritageNotFoundException;
import com.culturalheritage.exception.InvalidCulturalHeritageDataException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Controller for handling user interactions and orchestrating the cultural heritage modification process.
 * This class acts as the entry point for the console-based application, managing the flow
 * of events for modifying cultural heritage objects.
 */
public class CulturalHeritageController {

    private final CulturalHeritageService service;
    private final Scanner scanner;
    private boolean inputBlocked = false; // Quality requirement: blocks input controls

    /**
     * Constructs a new CulturalHeritageController.
     *
     * @param service The CulturalHeritageService instance to interact with the business logic.
     */
    public CulturalHeritageController(CulturalHeritageService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the main application loop, allowing the operator to perform actions.
     * This method simulates the entry point for the agency operator after logging in.
     */
    public void start() {
        System.out.println("Welcome to the Cultural Heritage Management System (Agency Operator)");
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View all Cultural Heritage Objects");
            System.out.println("2. Modify Cultural Heritage Object");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        displayCulturalHeritageList();
                        break;
                    case 2:
                        modifyCulturalHeritage();
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Displays the list of all cultural heritage objects currently in the system.
     * This simulates the result of the "SearchCulturalHeritage" use case.
     */
    public void displayCulturalHeritageList() {
        System.out.println("\n--- Cultural Heritage Objects List ---");
        List<CulturalHeritageObject> objects = service.getAllCulturalHeritageObjects();
        if (objects.isEmpty()) {
            System.out.println("No cultural heritage objects found.");
        } else {
            objects.forEach(System.out::println);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Initiates the process of modifying a cultural heritage object.
     * This method guides the user through selecting an object, editing its data,
     * confirming the changes, and saving them.
     */
    public void modifyCulturalHeritage() {
        if (inputBlocked) {
            System.out.println("Operation in progress. Please wait for current transaction to complete.");
            return;
        }

        System.out.println("\n--- Modify Cultural Heritage Object ---");
        displayCulturalHeritageList(); // Step 1: View the list and select an object

        String objectId = promptForCulturalHeritageId();
        if (objectId == null) { // User cancelled selection
            System.out.println("Modification cancelled by user.");
            return;
        }

        try {
            // Step 2: Load the data and display the form for editing
            CulturalHeritageObject originalObject = loadAndDisplayForm(objectId);
            if (originalObject == null) { // Object not found or user cancelled
                return;
            }

            // Create a mutable copy for editing
            CulturalHeritageObject modifiedObject = new CulturalHeritageObject(
                originalObject.getId(),
                originalObject.getName(),
                originalObject.getDescription(),
                originalObject.getOrigin(),
                originalObject.getYear()
            );

            // Step 3: Change data in the form and submit
            modifiedObject = editCulturalHeritageData(modifiedObject);
            if (modifiedObject == null) { // User cancelled editing
                System.out.println("Modification cancelled by user during editing.");
                return;
            }

            // Step 4: Verify the data entered and asks for confirmation
            if (confirmTransaction(modifiedObject)) {
                inputBlocked = true; // Block input to prevent multiple submissions
                System.out.println("Processing your request...");
                try {
                    // Step 6: Stores the modified data of the cultural
                    saveModifiedData(modifiedObject);
                    System.out.println("Notification: Cultural heritage data changed successfully.");
                } catch (InvalidCulturalHeritageDataException e) {
                    handleInvalidData(e); // Activates use case Errored
                } catch (CulturalHeritageNotFoundException e) {
                    handleNotFound(e);
                } catch (Exception e) { // General error, potentially connection interruption
                    System.err.println("Error during save operation: " + e.getMessage());
                    System.err.println("Interruption of the connection to the server ETOUR (simulated). Please try again later.");
                } finally {
                    inputBlocked = false; // Unblock input after transaction attempt
                }
            } else {
                System.out.println("Operation cancelled by Agency Operator."); // Operator Agency cancels the operation.
            }

        } catch (CulturalHeritageNotFoundException e) {
            handleNotFound(e);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during modification: " + e.getMessage());
        }
    }

    /**
     * Prompts the user to enter the ID of the cultural heritage object to modify.
     *
     * @return The ID entered by the user, or null if the user cancels.
     */
    private String promptForCulturalHeritageId() {
        System.out.print("Enter the ID of the cultural heritage object to modify (or 'cancel' to return): ");
        String id = scanner.nextLine();
        if (id.equalsIgnoreCase("cancel")) {
            return null;
        }
        return id;
    }

    /**
     * Loads the data of the cultural object and displays it for editing.
     *
     * @param id The ID of the object to load.
     * @return The CulturalHeritageObject loaded from the service.
     * @throws CulturalHeritageNotFoundException if the object with the given ID is not found.
     */
    private CulturalHeritageObject loadAndDisplayForm(String id) {
        System.out.println("Loading data for ID: " + id);
        CulturalHeritageObject object = service.getCulturalHeritageObject(id);
        System.out.println("\n--- Current Data for " + object.getName() + " ---");
        System.out.println(object);
        System.out.println("------------------------------------");
        return object;
    }

    /**
     * Allows the user to edit the data of a cultural heritage object.
     *
     * @param object The CulturalHeritageObject to be edited.
     * @return The CulturalHeritageObject with updated data, or null if the user cancels.
     */
    private CulturalHeritageObject editCulturalHeritageData(CulturalHeritageObject object) {
        System.out.println("\n--- Editing Cultural Heritage Object (Enter new values or press Enter to keep current) ---");
        System.out.println("Current Name: " + object.getName());
        System.out.print("New Name: ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            object.setName(newName.trim());
        }

        System.out.println("Current Description: " + object.getDescription());
        System.out.print("New Description: ");
        String newDescription = scanner.nextLine();
        if (!newDescription.trim().isEmpty()) {
            object.setDescription(newDescription.trim());
        }

        System.out.println("Current Origin: " + object.getOrigin());
        System.out.print("New Origin: ");
        String newOrigin = scanner.nextLine();
        if (!newOrigin.trim().isEmpty()) {
            object.setOrigin(newOrigin.trim());
        }

        System.out.println("Current Year: " + object.getYear());
        System.out.print("New Year (integer): ");
        String newYearStr = scanner.nextLine();
        if (!newYearStr.trim().isEmpty()) {
            try {
                int newYear = Integer.parseInt(newYearStr.trim());
                object.setYear(newYear);
            } catch (NumberFormatException e) {
                System.out.println("Invalid year format. Keeping original year.");
            }
        }
        System.out.print("Confirm changes? (yes/no): ");
        String confirmEdit = scanner.nextLine();
        if (!confirmEdit.equalsIgnoreCase("yes")) {
            return null; // User cancelled editing
        }
        return object;
    }

    /**
     * Asks for confirmation of the transaction from the operator.
     *
     * @param object The CulturalHeritageObject with the proposed changes.
     * @return true if the operator confirms, false otherwise.
     */
    private boolean confirmTransaction(CulturalHeritageObject object) {
        System.out.println("\n--- Review and Confirm Changes ---");
        System.out.println("Proposed changes for ID: " + object.getId());
        System.out.println(object);
        System.out.print("Do you confirm these changes? (yes/no): ");
        String confirmation = scanner.nextLine();
        return confirmation.equalsIgnoreCase("yes");
    }

    /**
     * Stores the modified data of the cultural object by calling the service layer.
     *
     * @param object The CulturalHeritageObject to be saved.
     * @throws InvalidCulturalHeritageDataException if the data is invalid.
     * @throws CulturalHeritageNotFoundException if the object to update is not found.
     */
    private void saveModifiedData(CulturalHeritageObject object) {
        System.out.println("Saving modified data for object ID: " + object.getId());
        service.updateCulturalHeritageObject(object);
        System.out.println("Data saved successfully.");
    }

    /**
     * Handles cases where the entered data is invalid or insufficient.
     * This method simulates activating the "Errored" use case by displaying an error message.
     *
     * @param e The InvalidCulturalHeritageDataException containing the error message.
     */
    private void handleInvalidData(InvalidCulturalHeritageDataException e) {
        System.err.println("\n--- Data Validation Error (Errored Use Case) ---");
        System.err.println("Error: " + e.getMessage());
        System.err.println("Please review the data and try again.");
        System.err.println("------------------------------------");
    }

    /**
     * Handles cases where the cultural heritage object is not found.
     *
     * @param e The CulturalHeritageNotFoundException containing the error message.
     */
    private void handleNotFound(CulturalHeritageNotFoundException e) {
        System.err.println("\n--- Object Not Found Error ---");
        System.err.println("Error: " + e.getMessage());
        System.err.println("Please check the ID and try again.");
        System.err.println("------------------------------------");
    }
}