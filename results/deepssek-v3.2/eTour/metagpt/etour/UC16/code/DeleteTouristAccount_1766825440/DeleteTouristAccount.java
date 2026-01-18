package com.etour.main;

import com.etour.operator.AgencyOperator;
import java.util.Scanner;

/**
 * DeleteTouristAccount - Main class for the DeleteTouristAccount use case.
 * 
 * This class demonstrates the complete workflow for deleting a tourist account
 * as specified in the use case documentation.
 * 
 * The program:
 * 1. Creates an AgencyOperator (simulating the logged-in agency operator)
 * 2. Logs in the operator (simulating authentication)
 * 3. Initiates the tourist account deletion workflow
 * 4. Handles edge cases such as server disconnection
 * 
 * This is a fully runnable Java program that demonstrates the use case
 * with a console-based interface.
 */
public class DeleteTouristAccount {
    
    /**
     * Main method - entry point of the program.
     * 
     * @param args command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        System.out.println("=======================================================");
        System.out.println("        DELETE TOURIST ACCOUNT - SYSTEM DEMO");
        System.out.println("=======================================================\n");
        
        // Create an agency operator (simulating the actor in the use case)
        System.out.println("Step 1: Creating Agency Operator...");
        AgencyOperator operator = new AgencyOperator("OP001", "Jane Doe");
        System.out.println("Agency Operator Created:");
        System.out.println("  - ID: " + operator.getOperatorId());
        System.out.println("  - Name: " + operator.getOperatorName());
        System.out.println("  - Initial Login Status: " + 
                          (operator.isLoggedIn() ? "LOGGED IN" : "LOGGED OUT"));
        
        // Check login status before proceeding (entry condition)
        System.out.println("\nStep 2: Checking Entry Conditions...");
        if (!operator.isLoggedIn()) {
            System.out.println("Entry condition: Agency operator is not logged in.");
            System.out.println("Proceeding with login simulation...\n");
        }
        
        // Simulate login (entry condition: "The agency has logged")
        System.out.println("Step 3: Logging in Agency Operator...");
        boolean loginSuccess = operator.login("jdoe", "password123");
        
        if (!loginSuccess) {
            System.out.println("Login failed. Cannot proceed with account deletion.");
            System.out.println("Exiting program.");
            return;
        }
        
        // Display operator status after login
        operator.displayStatus();
        
        // Demonstrate the main workflow: Delete Tourist Account
        System.out.println("Step 4: Initiating Delete Tourist Account Workflow...");
        System.out.println("--------------------------------------------------------");
        
        // First, demonstrate a successful deletion
        System.out.println("\nDEMONSTRATION 1: Successful Account Deletion");
        System.out.println("==============================================");
        operator.deleteTouristAccount();
        
        // Display status after deletion
        operator.displayStatus();
        
        // Demonstrate edge case: Server connection interruption
        System.out.println("\nDEMONSTRATION 2: Server Connection Interruption");
        System.out.println("==================================================");
        System.out.println("Simulating server disconnection to demonstrate edge case handling...\n");
        
        operator.simulateServerConnectionInterruption();
        operator.displayStatus();
        
        // Try deletion with server disconnected
        System.out.println("Attempting to delete account with server disconnected...");
        operator.deleteTouristAccount();
        
        // Reconnect server and try again
        System.out.println("\nReconnecting server and attempting deletion again...");
        operator.simulateServerReconnection();
        operator.displayStatus();
        
        // Demonstrate another deletion
        System.out.println("\nDEMONSTRATION 3: Another Account Deletion");
        System.out.println("===========================================");
        operator.deleteTouristAccount();
        
        // Demonstrate cancellation scenario
        System.out.println("\nDEMONSTRATION 4: Cancellation Scenario");
        System.out.println("========================================");
        System.out.println("This demonstrates what happens when operator cancels during confirmation.");
        
        // We need a way to simulate cancellation. Let's create a simple interactive demo
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n-- Interactive Demo: Cancellation --");
        System.out.println("When prompted for confirmation, enter 'no' to cancel.");
        System.out.print("Press Enter to start this demo...");
        scanner.nextLine();
        
        // Start another deletion workflow
        System.out.println("\nStarting deletion workflow...");
        operator.deleteTouristAccount();
        
        // Demonstrate logout
        System.out.println("\nStep 5: Logging Out...");
        operator.logout();
        operator.displayStatus();
        
        // Try to delete account after logout (should fail due to entry condition)
        System.out.println("\nDEMONSTRATION 5: Attempting Deletion After Logout");
        System.out.println("===================================================");
        System.out.println("This demonstrates the entry condition enforcement.");
        operator.deleteTouristAccount();
        
        // Summary and program conclusion
        System.out.println("\n=======================================================");
        System.out.println("               PROGRAM EXECUTION SUMMARY");
        System.out.println("=======================================================");
        System.out.println("\nUse Case: DeleteTouristAccount");
        System.out.println("Participating Actor: Agency Operator");
        System.out.println("\nWorkflow Steps Demonstrated:");
        System.out.println("1. Operator login (entry condition satisfied)");
        System.out.println("2. Search for tourists (activates SearchTourist use case)");
        System.out.println("3. Select tourist from search results");
        System.out.println("4. Confirm deletion transaction");
        System.out.println("5. Delete selected tourist account");
        System.out.println("\nEdge Cases Handled:");
        System.out.println("• Server connection interruption (ETOUR server)");
        System.out.println("• Operator not logged in (entry condition check)");
        System.out.println("• Invalid tourist selection");
        System.out.println("• Operation cancellation by operator");
        System.out.println("• Already deleted accounts");
        System.out.println("\nExit Conditions:");
        System.out.println("✓ Notification system has eliminated selected tourist account");
        System.out.println("✓ Handled interruption of connection to server ETOUR");
        System.out.println("\n=======================================================");
        System.out.println("           PROGRAM EXECUTION COMPLETED");
        System.out.println("=======================================================\n");
        
        System.out.println("Note: In a production environment, this would include:");
        System.out.println("• Database persistence");
        System.out.println("• Proper authentication and authorization");
        System.out.println("• Audit logging");
        System.out.println("• Real server connectivity checks");
        System.out.println("• User interface (GUI/Web) instead of console");
        System.out.println("• Error handling and recovery mechanisms");
    }
    
    /**
     * Helper method to pause execution and wait for user input.
     * Used in the demonstration to make the output more readable.
     */
    private static void waitForUserInput(Scanner scanner, String message) {
        System.out.print("\n" + message + " Press Enter to continue...");
        scanner.nextLine();
    }
}