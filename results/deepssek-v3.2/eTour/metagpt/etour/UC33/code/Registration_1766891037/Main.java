import java.util.Scanner;

/**
 * Main class that demonstrates the complete Registration use case.
 * This class contains the main method to run the registration system
 * and shows all features including error handling, server interruption,
 * and the complete registration flow.
 */
public class Main {
    /**
     * Main method - entry point of the registration system.
     * Demonstrates the complete registration use case with multiple scenarios.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== REGISTRATION SYSTEM DEMONSTRATION ===\n");
        
        Scanner scanner = new Scanner(System.in);
        
        // Demonstrate the registration system
        RegistrationSystem registrationSystem = new RegistrationSystem();
        
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            System.out.print("Enter your choice (1-5): ");
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    // Scenario 1: Normal successful registration
                    System.out.println("\n--- SCENARIO 1: NORMAL SUCCESSFUL REGISTRATION ---");
                    System.out.println("This demonstrates the complete successful registration flow.\n");
                    
                    // Simulate successful registration by ensuring server is connected
                    registrationSystem.setServerConnected(true);
                    boolean success = registrationSystem.executeRegistrationFlow();
                    
                    if (success) {
                        System.out.println("SUCCESS: Registration completed successfully!");
                    } else {
                        System.out.println("ERROR: Registration failed unexpectedly.");
                    }
                    break;
                    
                case "2":
                    // Scenario 2: Invalid data - activate Errored use case
                    System.out.println("\n--- SCENARIO 2: INVALID DATA ENTRY ---");
                    System.out.println("This demonstrates what happens when invalid data is entered.\n");
                    System.out.println("Instructions: Enter invalid data (e.g., invalid email, short password)");
                    System.out.println("to see how the system activates the Errored use case.\n");
                    
                    registrationSystem.setServerConnected(true);
                    RegistrationSystem newSystem = new RegistrationSystem(); // Fresh system for this demo
                    newSystem.executeRegistrationFlow();
                    newSystem.cleanup();
                    break;
                    
                case "3":
                    // Scenario 3: Server interruption (ETOUR)
                    System.out.println("\n--- SCENARIO 3: SERVER INTERRUPTION (ETOUR) ---");
                    System.out.println("This demonstrates handling of server connection interruptions.\n");
                    
                    // Create a system with manual server control
                    RegistrationSystem serverTestSystem = new RegistrationSystem();
                    System.out.println("Simulating server interruption during registration...\n");
                    
                    // We'll manually trigger server interruption
                    serverTestSystem.setServerConnected(false);
                    System.out.println("[DEMO] Server connection lost mid-process...");
                    System.out.println("[DEMO] Activating Errored use case for server interruption...");
                    
                    // Show how system handles it
                    serverTestSystem.activateErroredUseCase("Server connection interrupted (ETOUR)");
                    
                    System.out.println("\n[DEMO] Attempting reconnection...");
                    // For demo purposes, simulate reconnection
                    serverTestSystem.setServerConnected(true);
                    System.out.println("[DEMO] Reconnection successful! System can continue.");
                    
                    serverTestSystem.cleanup();
                    break;
                    
                case "4":
                    // Scenario 4: User cancellation
                    System.out.println("\n--- SCENARIO 4: USER CANCELLATION ---");
                    System.out.println("This demonstrates user-initiated cancellation.\n");
                    System.out.println("Instructions: During form filling, enter 'cancel' for username");
                    System.out.println("to simulate user cancellation.\n");
                    
                    RegistrationSystem cancelSystem = new RegistrationSystem();
                    cancelSystem.executeRegistrationFlow();
                    cancelSystem.cleanup();
                    break;
                    
                case "5":
                    // Scenario 5: Registration with retry mechanism
                    System.out.println("\n--- SCENARIO 5: REGISTRATION WITH RETRY ---");
                    System.out.println("This demonstrates the retry mechanism after failures.\n");
                    
                    RegistrationSystem retrySystem = new RegistrationSystem();
                    System.out.println("Starting registration with up to 3 retry attempts...\n");
                    
                    boolean retrySuccess = retrySystem.executeRegistrationWithRetry(3);
                    
                    if (retrySuccess) {
                        System.out.println("SUCCESS: Registration succeeded with retry mechanism!");
                    } else {
                        System.out.println("FAILURE: Maximum retry attempts reached.");
                    }
                    
                    retrySystem.cleanup();
                    break;
                    
                case "6":
                    // Exit the program
                    exit = true;
                    System.out.println("\nExiting Registration System Demonstration...");
                    break;
                    
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 6.");
                    break;
            }
            
            if (!exit) {
                System.out.println("\n" + "=".repeat(50));
                System.out.print("Press Enter to continue to the main menu...");
                scanner.nextLine();
            }
        }
        
        // Clean up resources
        registrationSystem.cleanup();
        scanner.close();
        
        System.out.println("\n=== REGISTRATION SYSTEM DEMONSTRATION COMPLETED ===");
        System.out.println("Thank you for using the Registration System!");
    }
    
    /**
     * Display the main menu of the demonstration program.
     */
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("REGISTRATION SYSTEM DEMONSTRATION MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Normal Successful Registration");
        System.out.println("2. Invalid Data Entry (Activates Errored Use Case)");
        System.out.println("3. Server Interruption (ETOUR) Handling");
        System.out.println("4. User Cancellation");
        System.out.println("5. Registration with Retry Mechanism");
        System.out.println("6. Exit");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Helper method to demonstrate a complete registration example.
     * This shows how all the components work together.
     */
    private static void demonstrateCompleteExample() {
        System.out.println("\n" + "*".repeat(60));
        System.out.println("COMPLETE REGISTRATION USE CASE WALKTHROUGH");
        System.out.println("*".repeat(60));
        
        System.out.println("\n1. Use Case Name: Registration");
        System.out.println("2. Description: Requires the creation of a new system account");
        System.out.println("3. Participating Actor: Guest User");
        System.out.println("4. Entry Conditions: Guest User has access to the system");
        
        System.out.println("\nFlow of Events:");
        System.out.println("  1. Enable logging feature");
        System.out.println("  2. View registration form");
        System.out.println("  3. Fill out form and submit");
        System.out.println("  4. Verify data and ask for confirmation");
        System.out.println("  5. Confirm operation");
        System.out.println("  6. Create new account");
        
        System.out.println("\nExit Conditions:");
        System.out.println("  - System notifies successful operation");
        System.out.println("  - Guest User cancels operation");
        System.out.println("  - Interruption of connection to server (ETOUR)");
        
        System.out.println("\n" + "*".repeat(60));
    }
    
    /**
     * Demonstrate edge case handling in the registration system.
     */
    private static void demonstrateEdgeCases() {
        System.out.println("\n--- EDGE CASE DEMONSTRATIONS ---");
        
        // Edge case 1: Empty form submission
        System.out.println("\n1. Empty Form Submission:");
        System.out.println("   When user submits empty form, validation fails.");
        System.out.println("   System activates Errored use case.");
        
        // Edge case 2: SQL injection attempt
        System.out.println("\n2. Malicious Input (SQL Injection):");
        System.out.println("   Input: ' OR '1'='1");
        System.out.println("   System validates and rejects improper format.");
        
        // Edge case 3: Extremely long inputs
        System.out.println("\n3. Extremely Long Inputs:");
        System.out.println("   Username validation enforces length limits (3-20 chars).");
        
        // Edge case 4: Special characters in username
        System.out.println("\n4. Special Characters in Username:");
        System.out.println("   Only letters, numbers, underscore allowed.");
        System.out.println("   Special chars like @#$% are rejected.");
        
        // Edge case 5: Duplicate email/username (simulated)
        System.out.println("\n5. Duplicate Registration Attempt:");
        System.out.println("   In production, system would check database for duplicates.");
        System.out.println("   For demo, validation focuses on format, not uniqueness.");
        
        System.out.println("\nAll edge cases are handled by:");
        System.out.println("- Comprehensive validation in RegistrationForm");
        System.out.println("- Error handling in RegistrationSystem");
        System.out.println("- Activation of Errored use case when needed");
    }
}