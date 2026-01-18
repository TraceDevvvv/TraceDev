package com.smos.enrollment;

import java.util.Scanner;

/**
 * Main class for the AcceptEnrollmentStudent use case.
 * This program implements the complete workflow described in the use case:
 * 1. Administrator views registration requests (precondition)
 * 2. Administrator accepts a registration request (main action)
 * 3. System activates the student and displays remaining requests
 * 4. Handles SMOS server connection interruptions (postcondition)
 * 
 * This is the entry point of the enrollment acceptance system.
 */
public class AcceptEnrollmentStudent {
    
    /**
     * Main method - Entry point of the program.
     * Demonstrates the complete "AcceptEnrollmentStudent" use case workflow.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("     STUDENT ENROLLMENT ACCEPTANCE SYSTEM     ");
        System.out.println("==============================================");
        System.out.println("Use Case: AcceptEnrollmentStudent");
        System.out.println("Actor: Administrator");
        System.out.println("Description: Accept student system enrollment");
        System.out.println("==============================================\n");
        
        // Create administrator (simulating logged-in user)
        Administrator admin = new Administrator("ADMIN001", "System Administrator", "admin@university.edu");
        
        // Create SMOS server connection
        SMOSServer smosServer = new SMOSServer();
        
        // Step 1: Precondition - Administrator has viewed registration requests
        System.out.println("=== PRECONDITION CHECK ===");
        System.out.println("Simulating 'View Registration Requests' use case...");
        admin.loadSampleRequests(); // Load sample requests
        
        // Verify SMOS server connection
        System.out.println("\n=== SERVER CONNECTION CHECK ===");
        if (smosServer.pingServer()) {
            System.out.println("✓ SMOS server connection verified.");
        } else {
            System.out.println("⚠ SMOS server connection issue detected.");
            System.out.println("Attempting to restore connection...");
            smosServer.restoreConnection();
        }
        
        // Display initial pending requests
        System.out.println("\n=== INITIAL SYSTEM STATE ===");
        System.out.println(admin.getRequestStatistics());
        System.out.println(smosServer.getServerStatus());
        
        // Demonstrate the main use case
        System.out.println("\n" + "=".repeat(60));
        System.out.println("MAIN USE CASE: AcceptEnrollmentStudent");
        System.out.println("=".repeat(60));
        
        // Menu for user interaction
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;
        
        while (!exitProgram) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. View Pending Registration Requests");
            System.out.println("2. Accept Enrollment Request");
            System.out.println("3. Reject Enrollment Request");
            System.out.println("4. Process Requests Interactively");
            System.out.println("5. Test SMOS Server Connection");
            System.out.println("6. Simulate SMOS Connection Interruption (Postcondition)");
            System.out.println("7. View System Statistics");
            System.out.println("8. Exit Program");
            System.out.print("\nEnter your choice (1-8): ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    // View pending requests (precondition simulation)
                    displayPendingRequests(admin);
                    break;
                    
                case "2":
                    // Accept enrollment request (main use case)
                    acceptEnrollmentRequestInteractive(admin, smosServer, scanner);
                    break;
                    
                case "3":
                    // Reject enrollment request
                    rejectEnrollmentRequestInteractive(admin, scanner);
                    break;
                    
                case "4":
                    // Process requests interactively (complete workflow)
                    processRequestsInteractively(admin, smosServer);
                    break;
                    
                case "5":
                    // Test SMOS server connection
                    testSMOSServerConnection(smosServer);
                    break;
                    
                case "6":
                    // Simulate SMOS connection interruption (postcondition)
                    simulateSMOSConnectionInterruption(admin, smosServer);
                    break;
                    
                case "7":
                    // View system statistics
                    displaySystemStatistics(admin, smosServer);
                    break;
                    
                case "8":
                    // Exit program
                    System.out.println("\nExiting Student Enrollment Acceptance System...");
                    exitProgram = true;
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PROGRAM TERMINATED SUCCESSFULLY");
        System.out.println("=".repeat(60));
        
        scanner.close();
    }
    
    /**
     * Displays all pending registration requests.
     * This simulates the precondition "View Registration Requests".
     * 
     * @param admin Administrator object
     */
    private static void displayPendingRequests(Administrator admin) {
        System.out.println("\n=== VIEWING PENDING REGISTRATION REQUESTS ===");
        System.out.println("(Precondition for AcceptEnrollmentStudent use case)");
        admin.displayPendingRequests();
        
        if (admin.getPendingRequests().isEmpty()) {
            System.out.println("\nNote: No pending requests available for acceptance.");
            System.out.println("The 'Accept Enrollment Request' option will be unavailable.");
        }
    }
    
    /**
     * Interactive method to accept an enrollment request.
     * Implements the main use case functionality.
     * 
     * @param admin Administrator object
     * @param smosServer SMOS server connection
     * @param scanner Scanner for user input
     */
    private static void acceptEnrollmentRequestInteractive(Administrator admin, 
                                                          SMOSServer smosServer, 
                                                          Scanner scanner) {
        System.out.println("\n=== ACCEPT ENROLLMENT REQUEST ===");
        System.out.println("(Main use case: AcceptEnrollmentStudent)");
        
        // Check if there are pending requests
        if (admin.getPendingRequests().isEmpty()) {
            System.out.println("No pending registration requests available.");
            System.out.println("Please use 'View Registration Requests' first.");
            return;
        }
        
        // Display pending requests with ind
        System.out.println("Available pending requests:");
        admin.displayPendingRequests();
        
        // Get request selection from user
        System.out.print("Enter the number of the request to accept (1-" + 
                        admin.getPendingRequests().size() + "): ");
        String input = scanner.nextLine().trim();
        
        try {
            int requestIndex = Integer.parseInt(input);
            
            // Validate index
            if (requestIndex < 1 || requestIndex > admin.getPendingRequests().size()) {
                System.out.println("Invalid request number. Please enter a valid number.");
                return;
            }
            
            // Get the request to be processed
            RegistrationRequest selectedRequest = admin.getPendingRequests().get(requestIndex - 1);
            
            System.out.println("\n=== PROCESSING REQUEST ===");
            System.out.println("Request ID: " + selectedRequest.getRequestId());
            System.out.println("Student: " + selectedRequest.getStudent().getFullName());
            
            // Check SMOS server connection before processing
            if (!smosServer.isConnected()) {
                System.out.println("\n⚠ WARNING: SMOS server connection is interrupted!");
                System.out.print("Attempt to restore connection before proceeding? (y/n): ");
                String restoreChoice = scanner.nextLine().trim().toLowerCase();
                
                if (restoreChoice.equals("y")) {
                    if (!smosServer.restoreConnection()) {
                        System.out.println("❌ Cannot proceed - SMOS server connection unavailable.");
                        return;
                    }
                } else {
                    System.out.println("❌ Operation cancelled due to SMOS server connection issue.");
                    return;
                }
            }
            
            // Simulate the use case event sequence
            System.out.println("\n--- Event Sequence ---");
            
            // Event 1: Activate the new user in the system
            System.out.println("1. Activating the new user in the system...");
            
            boolean success = admin.acceptEnrollmentRequest(requestIndex);
            
            if (success) {
                // Event 2: Display the list of registrations yet to be activated
                System.out.println("\n2. Displaying remaining pending requests...");
                
                // Synchronize with SMOS server
                System.out.println("\n--- SMOS Server Synchronization ---");
                try {
                    boolean syncSuccess = smosServer.synchronizeRegistrationRequest(selectedRequest);
                    if (syncSuccess) {
                        System.out.println("✓ Enrollment successfully synchronized with SMOS server.");
                    } else {
                        System.out.println("⚠ Enrollment processed locally but SMOS sync failed.");
                        System.out.println("   Data will be synchronized when connection is restored.");
                    }
                } catch (IllegalStateException e) {
                    System.out.println("⚠ SMOS server synchronization skipped: " + e.getMessage());
                }
                
                System.out.println("\n✓ USE CASE COMPLETED SUCCESSFULLY");
                System.out.println("  Postcondition: User has accepted a system registration request");
                
            } else {
                System.out.println("❌ Failed to accept enrollment request.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalStateException e) {
            System.out.println("Operation failed: " + e.getMessage());
            System.out.println("Postcondition: SMOS server connection interrupted");
        }
    }
    
    /**
     * Interactive method to reject an enrollment request.
     * 
     * @param admin Administrator object
     * @param scanner Scanner for user input
     */
    private static void rejectEnrollmentRequestInteractive(Administrator admin, Scanner scanner) {
        System.out.println("\n=== REJECT ENROLLMENT REQUEST ===");
        
        if (admin.getPendingRequests().isEmpty()) {
            System.out.println("No pending registration requests available.");
            return;
        }
        
        admin.displayPendingRequests();
        
        System.out.print("Enter the number of the request to reject (1-" + 
                        admin.getPendingRequests().size() + "): ");
        String input = scanner.nextLine().trim();
        
        try {
            int requestIndex = Integer.parseInt(input);
            
            if (requestIndex < 1 || requestIndex > admin.getPendingRequests().size()) {
                System.out.println("Invalid request number.");
                return;
            }
            
            boolean success = admin.rejectEnrollmentRequest(requestIndex);
            
            if (success) {
                System.out.println("✓ Enrollment request rejected successfully.");
            } else {
                System.out.println("❌ Failed to reject enrollment request.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
    
    /**
     * Processes requests interactively using the Administrator's built-in method.
     * Provides a complete workflow experience.
     * 
     * @param admin Administrator object
     * @param smosServer SMOS server connection
     */
    private static void processRequestsInteractively(Administrator admin, SMOSServer smosServer) {
        System.out.println("\n=== INTERACTIVE REQUEST PROCESSING ===");
        System.out.println("This mode provides a complete workflow for processing enrollment requests.");
        System.out.println("It includes all steps from the use case description.");
        
        // Ensure SMOS server is connected
        if (!smosServer.isConnected()) {
            System.out.println("\n⚠ SMOS server connection is required for interactive processing.");
            System.out.println("Attempting to restore connection...");
            
            if (!smosServer.restoreConnection()) {
                System.out.println("❌ Cannot proceed - SMOS server unavailable.");
                return;
            }
        }
        
        // Use the Administrator's interactive processing method
        admin.processRequestsInteractive();
    }
    
    /**
     * Tests the SMOS server connection and displays status.
     * 
     * @param smosServer SMOS server connection
     */
    private static void testSMOSServerConnection(SMOSServer smosServer) {
        System.out.println("\n=== TESTING SMOS SERVER CONNECTION ===");
        System.out.println(smosServer.getServerStatus());
        
        boolean isAlive = smosServer.pingServer();
        
        if (!isAlive && smosServer.isConnected()) {
            System.out.println("⚠ Server marked as connected but ping failed.");
            System.out.println("Updating connection status...");
            smosServer.disconnect();
        }
        
        System.out.println("\nUpdated Status:");
        System.out.println(smosServer.getServerStatus());
    }
    
    /**
     * Simulates SMOS connection interruption (postcondition handling).
     * 
     * @param admin Administrator object
     * @param smosServer SMOS server connection
     */
    private static void simulateSMOSConnectionInterruption(Administrator admin, SMOSServer smosServer) {
        System.out.println("\n=== SIMULATING SMOS CONNECTION INTERRUPTION ===");
        System.out.println("(Postcondition: SMOS server connection interrupted)");
        
        // Interrupt SMOS server connection
        smosServer.interruptConnection();
        
        // Also interrupt administrator's connection
        admin.interruptSMOSConnection();
        
        System.out.println("\n✓ SMOS server connection has been interrupted.");
        System.out.println("This simulates the postcondition from the use case.");
        System.out.println("\nNote: Enrollment operations will now fail until connection is restored.");
        
        // Demonstrate that enrollment acceptance fails when connection is interrupted
        if (!admin.getPendingRequests().isEmpty()) {
            System.out.println("\n--- Testing enrollment with interrupted connection ---");
            try {
                // This should fail due to connection interruption
                admin.acceptEnrollmentRequest(1);
            } catch (IllegalStateException e) {
                System.out.println("Expected error: " + e.getMessage());
                System.out.println("✓ This demonstrates the postcondition behavior.");
            }
        }
    }
    
    /**
     * Displays comprehensive system statistics.
     * 
     * @param admin Administrator object
     * @param smosServer SMOS server connection
     */
    private static void displaySystemStatistics(Administrator admin, SMOSServer smosServer) {
        System.out.println("\n=== SYSTEM STATISTICS ===");
        System.out.println(admin.getRequestStatistics());
        System.out.println();
        System.out.println(smosServer.getServerStatus());
        System.out.println("\nTotal students in system: " + countActiveStudents(admin));
    }
    
    /**
     * Counts active students in the system.
     * 
     * @param admin Administrator object
     * @return Number of active students
     */
    private static int countActiveStudents(Administrator admin) {
        int activeCount = 0;
        for (RegistrationRequest request : admin.getAllRequests()) {
            if (request.getStudent() != null && request.getStudent().isActive()) {
                activeCount++;
            }
        }
        return activeCount;
    }
    
    /**
     * Utility method to display program usage instructions.
     */
    private static void displayHelp() {
        System.out.println("\n=== PROGRAM USAGE ===");
        System.out.println("This program implements the 'AcceptEnrollmentStudent' use case.");
        System.out.println("\nWorkflow:");
        System.out.println("1. View Registration Requests (Precondition)");
        System.out.println("2. Accept Enrollment Request (Main Action)");
        System.out.println("3. System activates student and shows remaining requests");
        System.out.println("4. SMOS server synchronization");
        System.out.println("5. Handle connection interruptions (Postcondition)");
        System.out.println("\nUse the menu options to navigate through the workflow.");
    }
    
    /**
     * Static initializer block - executed when class is loaded.
     * Displays copyright and version information.
     */
    static {
        System.out.println("AcceptEnrollmentStudent System v1.0");
        System.out.println("Copyright © 2023 University Enrollment System");
        System.out.println("All rights reserved.\n");
    }
}