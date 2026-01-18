package com.culturalheritage.view;

import com.culturalheritage.controller.CulturalHeritageController;
import com.culturalheritage.model.CulturalHeritage;
import com.culturalheritage.exception.ValidationException;
import com.culturalheritage.exception.DuplicateHeritageException;

import java.util.Scanner;

/**
 * ConsoleUI provides a command-line interface for the Cultural Heritage Management System.
 * This class handles user interactions, form display, input collection, and result presentation.
 * It follows the MVC pattern by acting as the View component.
 */
public class ConsoleUI {
    
    private final CulturalHeritageController controller;
    private final Scanner scanner;
    private boolean isRunning;
    
    /**
     * Constructs a ConsoleUI with the specified controller.
     * 
     * @param controller The CulturalHeritageController to handle business logic
     * @throws IllegalArgumentException if controller is null
     */
    public ConsoleUI(CulturalHeritageController controller) {
        if (controller == null) {
            throw new IllegalArgumentException("CulturalHeritageController cannot be null");
        }
        this.controller = controller;
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
    }
    
    /**
     * Starts the ConsoleUI and displays the main menu.
     * This method runs the main application loop until the user chooses to exit.
     */
    public void start() {
        System.out.println("\n=== CULTURAL HERITAGE MANAGEMENT SYSTEM ===\n");
        System.out.println("Welcome, Agency Operator!");
        
        while (isRunning) {
            displayMenu();
            handleMenuChoice();
        }
        
        System.out.println("Thank you for using the Cultural Heritage Management System. Goodbye!");
        scanner.close();
    }
    
    /**
     * Displays the main menu options to the user.
     */
    public void displayMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Insert New Cultural Heritage");
        System.out.println("2. View All Cultural Heritage");
        System.out.println("3. Exit");
        System.out.print("Please enter your choice (1-3): ");
    }
    
    /**
     * Handles the user's menu choice and invokes the appropriate functionality.
     */
    private void handleMenuChoice() {
        try {
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    activateInsertionFeature();
                    break;
                case "2":
                    // In a complete implementation, this would call a method to view all heritage
                    System.out.println("\nFeature not implemented in this version.");
                    System.out.println("Please check back in a future update!");
                    break;
                case "3":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        } catch (Exception e) {
            System.err.println("Error processing menu choice: " + e.getMessage());
        }
    }
    
    /**
     * Activates the feature for inserting a new cultural heritage.
     * This method corresponds to Step 1 of the use case flow.
     */
    public void activateInsertionFeature() {
        System.out.println("\n=== INSERT NEW CULTURAL HERITAGE ===\n");
        
        try {
            // Step 2: Display the form and get form data
            CulturalHeritage formData = displayForm();
            
            if (formData == null) {
                System.out.println("Form filling cancelled.");
                return;
            }
            
            // Step 3: Fill out the form with data
            CulturalHeritage filledForm = fillForm(formData);
            
            if (filledForm == null) {
                System.out.println("Form submission cancelled.");
                return;
            }
            
            // Step 4 & 5: Verify data and ask for confirmation
            boolean confirmed = askForConfirmation(filledForm);
            
            if (confirmed) {
                // Step 6: Insert the cultural heritage
                CulturalHeritage insertedHeritage = controller.insertCulturalHeritage(filledForm);
                
                // Display success message
                displaySuccess(insertedHeritage);
            } else {
                System.out.println("\nOperation cancelled by user.");
            }
            
        } catch (ValidationException e) {
            handleError("Validation Error: " + e.getMessage());
        } catch (DuplicateHeritageException e) {
            handleError("Duplicate Detection Error: " + e.getMessage());
        } catch (Exception e) {
            handleError("Unexpected Error: " + e.getMessage());
        }
    }
    
    /**
     * Displays the form for entering cultural heritage data.
     * This method corresponds to Step 2 of the use case flow.
     * 
     * @return A CulturalHeritage object with default values
     */
    public CulturalHeritage displayForm() {
        System.out.println("\n=== CULTURAL HERITAGE REGISTRATION FORM ===\n");
        System.out.println("Please fill out the following information:");
        System.out.println("(All fields marked with * are required)\n");
        
        // Get a form template from the controller
        CulturalHeritage formTemplate = controller.displayForm();
        
        if (formTemplate == null) {
            System.err.println("Failed to get form template from controller.");
            return null;
        }
        
        return formTemplate;
    }
    
    /**
     * Fills out the form with user-provided data.
     * This method corresponds to Step 3 of the use case flow.
     * 
     * @param formTemplate The CulturalHeritage object to fill with data
     * @return The filled CulturalHeritage object, or null if user cancels
     */
    public CulturalHeritage fillForm(CulturalHeritage formTemplate) {
        if (formTemplate == null) {
            return null;
        }
        
        try {
            // Collect name
            System.out.print("* Name of cultural heritage: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty. Please try again.");
                return null;
            }
            formTemplate.setName(name);
            
            // Collect type
            System.out.print("* Type/Category (e.g., Monument, Artifact, Site): ");
            String type = scanner.nextLine().trim();
            if (type.isEmpty()) {
                System.out.println("Type cannot be empty. Please try again.");
                return null;
            }
            formTemplate.setType(type);
            
            // Collect location
            System.out.print("* Location (City/Region/Country): ");
            String location = scanner.nextLine().trim();
            if (location.isEmpty()) {
                System.out.println("Location cannot be empty. Please try again.");
                return null;
            }
            formTemplate.setLocation(location);
            
            // Collect era (optional)
            System.out.print("Historical Era (e.g., Renaissance, Ancient Rome): ");
            String era = scanner.nextLine().trim();
            if (!era.isEmpty()) {
                formTemplate.setEra(era);
            }
            
            // Collect description (optional)
            System.out.print("Description: ");
            String description = scanner.nextLine().trim();
            if (!description.isEmpty()) {
                formTemplate.setDescription(description);
            }
            
            // Collect agency code (pre-filled from controller, allow override)
            System.out.print("* Agency Code (default: " + formTemplate.getAgencyCode() + "): ");
            String agencyCode = scanner.nextLine().trim();
            if (!agencyCode.isEmpty()) {
                formTemplate.setAgencyCode(agencyCode);
            }
            
            System.out.println("\nForm filled successfully!");
            return formTemplate;
            
        } catch (Exception e) {
            System.err.println("Error filling form: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Asks the user for confirmation before proceeding with the transaction.
     * This method corresponds to Step 5 of the use case flow.
     * 
     * @param heritage The CulturalHeritage object to confirm
     * @return true if user confirms, false if user cancels
     */
    public boolean askForConfirmation(CulturalHeritage heritage) {
        if (heritage == null) {
            return false;
        }
        
        System.out.println("\n=== CONFIRMATION REQUIRED ===\n");
        System.out.println("Please review the cultural heritage details:");
        System.out.println("--------------------------------------------");
        System.out.println("Name: " + heritage.getName());
        System.out.println("Type: " + heritage.getType());
        System.out.println("Location: " + heritage.getLocation());
        System.out.println("Era: " + (heritage.getEra() != null ? heritage.getEra() : "Not specified"));
        System.out.println("Description: " + (heritage.getDescription() != null && !heritage.getDescription().isEmpty() 
                ? heritage.getDescription() : "Not specified"));
        System.out.println("Agency Code: " + heritage.getAgencyCode());
        System.out.println("--------------------------------------------\n");
        
        while (true) {
            System.out.print("Do you want to proceed with registration? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (response.equals("yes") || response.equals("y")) {
                System.out.println("Confirmation received. Proceeding with registration...");
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                System.out.println("Registration cancelled.");
                return false;
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }
    }
    
    /**
     * Handles error situations by displaying error messages to the user.
     * This method corresponds to the "Errored" use case mentioned in requirements.
     * 
     * @param message The error message to display
     */
    public void handleError(String message) {
        System.out.println("\n=== ERROR ===\n");
        System.out.println(message);
        System.out.println("\nThe operation cannot be completed.");
        System.out.println("Please check the data and try again.");
        System.out.println("If the problem persists, contact system administrator.");
    }
    
    /**
     * Displays success message after successful insertion.
     * This method corresponds to the exit condition of notifying proper inclusion.
     * 
     * @param heritage The successfully inserted CulturalHeritage object
     */
    public void displaySuccess(CulturalHeritage heritage) {
        if (heritage == null) {
            handleError("Insertion succeeded but no heritage data returned.");
            return;
        }
        
        System.out.println("\n=== SUCCESS ===\n");
        System.out.println("Cultural heritage has been successfully registered in the system!");
        System.out.println("\nRegistration Details:");
        System.out.println("--------------------");
        System.out.println("Registration ID: " + heritage.getId());
        System.out.println("Name: " + heritage.getName());
        System.out.println("Type: " + heritage.getType());
        System.out.println("Location: " + heritage.getLocation());
        System.out.println("Registration Date: " + heritage.getRegistrationDate());
        System.out.println("\nThe cultural heritage is now part of the national registry.");
        System.out.println("You may reference it using the Registration ID for future queries.");
        
        // Ask if user wants to insert another
        System.out.print("\nWould you like to insert another cultural heritage? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("yes") || response.equals("y")) {
            System.out.println("Returning to insertion feature...");
            activateInsertionFeature();
        }
    }
    
    /**
     * Gets the scanner instance (for testing purposes).
     * 
     * @return The Scanner instance
     */
    public Scanner getScanner() {
        return scanner;
    }
    
    /**
     * Gets the controller instance.
     * 
     * @return The CulturalHeritageController instance
     */
    public CulturalHeritageController getController() {
        return controller;
    }
    
    /**
     * Checks if the UI is still running.
     * 
     * @return true if the UI is running, false otherwise
     */
    public boolean isRunning() {
        return isRunning;
    }
    
    /**
     * Stops the UI loop.
     */
    public void stop() {
        isRunning = false;
    }
}