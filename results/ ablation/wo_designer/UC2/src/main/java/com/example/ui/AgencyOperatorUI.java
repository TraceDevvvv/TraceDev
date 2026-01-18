package com.example.ui;

import com.example.model.CulturalObject;
import com.example.service.CulturalObjectService;

import java.util.Scanner;

/**
 * Simulates the user interface for the Agency Operator.
 * Handles form input, confirmation, and interaction with the service.
 */
public class AgencyOperatorUI {
    private CulturalObjectService service;
    private Scanner scanner;

    public AgencyOperatorUI(CulturalObjectService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the form for entering cultural object data.
     * @return the populated CulturalObject, or null if cancelled.
     */
    public CulturalObject displayForm() {
        System.out.println("=== Insert a New Cultural Object ===");
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        System.out.print("Enter Era: ");
        String era = scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        return new CulturalObject(id, name, description, location, era, category);
    }

    /**
     * Asks for confirmation of the transaction.
     * @return true if confirmed, false otherwise
     */
    public boolean askConfirmation() {
        System.out.print("Confirm insertion? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    /**
     * Activates the insertion flow as per the use case.
     */
    public void activateInsertion() {
        System.out.println("Agency Operator activates the feature for insertion.");
        CulturalObject culturalObject = displayForm();
        if (culturalObject == null) {
            System.out.println("Operation cancelled by Agency Operator.");
            return;
        }

        // Simulate step 4: Agency Operator submits the form
        System.out.println("Submitting form...");

        // Simulate step 5: System verifies the data entered
        // Verification is done in service.insertCulturalObject

        // Simulate step 6: System asks for confirmation
        boolean confirmed = askConfirmation();
        if (!confirmed) {
            System.out.println("Operation cancelled by Agency Operator.");
            return;
        }

        // Simulate step 7 and 8: System checks if data invalid/insufficient and activates ErroreInserimentoDati
        // Simulate step 9: Agency Operator confirms (already done above)
        // Simulate step 10: System memorizes the new cultural good
        boolean success = service.insertCulturalObject(culturalObject);
        if (success) {
            System.out.println("System notifies the proper inclusion of the cultural object.");
        } else {
            System.out.println("Insertion failed.");
        }
    }

    public void close() {
        scanner.close();
    }
}