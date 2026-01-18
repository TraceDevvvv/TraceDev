package com.editteachingsystem;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class demonstrating the Edit Teaching use case.
 * This class provides a console-based UI that walks through the complete
 * use case sequence as specified in the requirements.
 * 
 * The use case sequence implemented:
 * 1. Administrator login (simulated)
 * 2. Display teaching details
 * 3. Edit teaching information
 * 4. Save with validation
 * 5. Handle errors and SMOS server connection
 * 6. Display updated teachings list
 */
public class Main {
    
    private static TeachingService teachingService;
    private static SMOSServerConnection smosConnection;
    private static Scanner scanner;
    private static boolean isAdministratorLoggedIn = false;
    
    public static void main(String[] args) {
        System.out.println("=== Edit Teaching Use Case Demonstration ===");
        System.out.println("Use Case: Edit Teaching");
        System.out.println("Actor: Administrator");
        System.out.println("==========================================");
        
        // Initialize serv
        initializeServ();
        scanner = new Scanner(System.in);
        
        try {
            // Step 1: Administrator login (simulated as per preconditions)
            simulateAdministratorLogin();
            
            if (!isAdministratorLoggedIn) {
                System.out.println("Error: Administrator login failed. Exiting.");
                return;
            }
            
            // Display initial teachings list
            displayTeachingsList("Initial Teachings in System:");
            
            // Main use case loop
            boolean continueRunning = true;
            while (continueRunning) {
                System.out.println("\n=== Edit Teaching Use Case Menu ===");
                System.out.println("1. Display teaching details");
                System.out.println("2. Edit teaching information");
                System.out.println("3. Simulate SMOS server interruption");
                System.out.println("4. Simulate administrator interruption");
                System.out.println("5. Display updated teachings list");
                System.out.println("6. Exit");
                System.out.print("Enter your choice (1-6): ");
                
                String choice = scanner.nextLine().trim();
                
                switch (choice) {
                    case "1":
                        displayTeachingDetails();
                        break;
                    case "2":
                        editTeachingProcess();
                        break;
                    case "3":
                        simulateSMOSInterruption();
                        break;
                    case "4":
                        simulateAdministratorInterruption();
                        break;
                    case "5":
                        displayTeachingsList("Current Teachings in System:");
                        break;
                    case "6":
                        continueRunning = false;
                        System.out.println("Exiting Edit Teaching System.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1-6.");
                }
            }
            
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            System.out.println("\n=== Use Case Demonstration Complete ===");
        }
    }
    
    /**
     * Initializes the SMOS connection and TeachingService
     */
    private static void initializeServ() {
        System.out.println("Initializing SMOS server connection...");
        smosConnection = new SMOSServerConnection();
        teachingService = new TeachingService(smosConnection);
        
        System.out.println("SMOS Connection Status: " + smosConnection.getConnectionStatus());
        System.out.println("Teaching service initialized with sample data.");
    }
    
    /**
     * Simulates administrator login (as per preconditions)
     */
    private static void simulateAdministratorLogin() {
        System.out.println("\n=== Precondition: Administrator Login ===");
        System.out.println("Simulating administrator login...");
        
        // Simple simulation - in real system this would validate credentials
        isAdministratorLoggedIn = true;
        System.out.println("Administrator logged in successfully.");
        System.out.println("Precondition 'displaydeddailsigning' assumed to be completed.");
    }
    
    /**
     * Displays details of a specific teaching
     */
    private static void displayTeachingDetails() {
        System.out.println("\n=== Display Teaching Details ===");
        displayTeachingsList("Available Teachings:");
        
        System.out.print("Enter Teaching ID to view details (e.g., TEA-100001): ");
        String teachingId = scanner.nextLine().trim();
        
        Teaching teaching = teachingService.getTeachingById(teachingId);
        if (teaching == null) {
            System.out.println("Teaching not found with ID: " + teachingId);
            return;
        }
        
        System.out.println("\n=== Teaching Details ===");
        System.out.println(teaching);
        System.out.println("========================");
    }
    
    /**
     * Main method implementing the Edit Teaching use case sequence
     */
    private static void editTeachingProcess() {
        System.out.println("\n=== Edit Teaching Process ===");
        System.out.println("Following the use case sequence:");
        System.out.println("1. User displays detailed information of a teaching");
        System.out.println("2. User edits information in the form");
        System.out.println("3. User clicks 'Save' button");
        System.out.println("4. System validates data and updates archive");
        System.out.println("5. System displays updated teachings list");
        
        // Step 1: Display teaching details
        displayTeachingsList("Select a teaching to edit:");
        
        System.out.print("Enter Teaching ID to edit (e.g., TEA-100001): ");
        String teachingId = scanner.nextLine().trim();
        
        Teaching originalTeaching = teachingService.getTeachingById(teachingId);
        if (originalTeaching == null) {
            System.out.println("Teaching not found with ID: " + teachingId);
            return;
        }
        
        System.out.println("\n=== Current Teaching Details ===");
        System.out.println(originalTeaching);
        
        // Step 2: Create updated teaching with edited information
        System.out.println("\n=== Edit Teaching Information ===");
        System.out.println("Enter new values (press Enter to keep current value):");
        
        Teaching updatedTeaching = originalTeaching.copy();
        
        // Edit course name
        System.out.print("Course Name [" + originalTeaching.getCourseName() + "]: ");
        String courseName = scanner.nextLine().trim();
        if (!courseName.isEmpty()) {
            try {
                updatedTeaching.setCourseName(courseName);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }
        
        // Edit instructor name
        System.out.print("Instructor Name [" + originalTeaching.getInstructorName() + "]: ");
        String instructorName = scanner.nextLine().trim();
        if (!instructorName.isEmpty()) {
            try {
                updatedTeaching.setInstructorName(instructorName);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }
        
        // Edit schedule
        System.out.print("Schedule [" + originalTeaching.getSchedule() + "]: ");
        String schedule = scanner.nextLine().trim();
        if (!schedule.isEmpty()) {
            try {
                updatedTeaching.setSchedule(schedule);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }
        
        // Edit location
        System.out.print("Location [" + originalTeaching.getLocation() + "]: ");
        String location = scanner.nextLine().trim();
        if (!location.isEmpty()) {
            try {
                updatedTeaching.setLocation(location);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }
        
        // Edit max students
        System.out.print("Max Students [" + originalTeaching.getMaxStudents() + "]: ");
        String maxStudentsStr = scanner.nextLine().trim();
        if (!maxStudentsStr.isEmpty()) {
            try {
                int maxStudents = Integer.parseInt(maxStudentsStr);
                updatedTeaching.setMaxStudents(maxStudents);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format");
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
        }
        
        // Step 3: Simulate clicking 'Save' button
        System.out.println("\n=== Step 3: Clicking 'Save' Button ===");
        System.out.println("Simulating user clicking the 'Save' button...");
        
        // Step 4: System validates data and updates archive
        System.out.println("\n=== Step 4: System Validation and Archive Update ===");
        TeachingService.EditResult result = teachingService.editTeaching(teachingId, updatedTeaching);
        
        // Display result
        System.out.println("\n=== Edit Operation Result ===");
        System.out.println(result);
        
        // Handle different result types as per use case
        if (result.isSuccess()) {
            System.out.println("\n✓ Teaching updated successfully!");
            System.out.println("Postcondition: User has changed a teaching.");
            
            // Step 5: Display updated teachings list
            System.out.println("\n=== Step 5: Displaying Updated Teachings List ===");
            displayTeachingsList("Updated Teachings List:");
            
        } else if (!result.getValidationErrors().isEmpty()) {
            System.out.println("\n✗ Data validation errors occurred.");
            System.out.println("Activating 'Errodati' use case as specified.");
            System.out.println("Postcondition: User is notified of data error.");
            
        } else if (result.isInterrupted()) {
            System.out.println("\n✗ Operation was interrupted.");
            System.out.println("Postcondition: The administrator interrupts the operation.");
            
        } else {
            System.out.println("\n✗ Edit operation failed.");
            if (!teachingService.isSMOSConnected()) {
                System.out.println("Postcondition: Connection to SMOS server interrupted.");
            }
        }
    }
    
    /**
     * Displays all teachings in the system
     */
    private static void displayTeachingsList(String title) {
        List<Teaching> teachings = teachingService.getAllTeachings();
        
        System.out.println("\n=== " + title + " ===");
        if (teachings.isEmpty()) {
            System.out.println("No teachings found.");
        } else {
            for (int i = 0; i < teachings.size(); i++) {
                Teaching teaching = teachings.get(i);
                System.out.printf("%d. %s\n", i + 1, teaching.toSummaryString());
            }
        }
        System.out.println("Total: " + teachings.size() + " teachings");
    }
    
    /**
     * Simulates SMOS server interruption (postcondition scenario)
     */
    private static void simulateSMOSInterruption() {
        System.out.println("\n=== Simulating SMOS Server Interruption ===");
        smosConnection.interruptConnection();
        System.out.println("SMOS Connection Status: " + smosConnection.getConnectionStatus());
        System.out.println("Postcondition: Connection to SMOS server interrupted.");
    }
    
    /**
     * Simulates administrator interrupting the operation (postcondition scenario)
     */
    private static void simulateAdministratorInterruption() {
        System.out.println("\n=== Simulating Administrator Interruption ===");
        teachingService.interruptOperation();
        System.out.println("Postcondition: The administrator interrupts the operation.");
    }
}