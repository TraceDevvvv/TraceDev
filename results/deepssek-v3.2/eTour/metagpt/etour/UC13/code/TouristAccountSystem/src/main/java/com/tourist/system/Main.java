package com.tourist.system;

/**
 * Main class - Entry point for the Tourist Account Management System
 * 
 * This program demonstrates the complete use case for activating/deactivating
 * tourist accounts as specified in the requirements:
 * 1. Agency Operator activates the feature
 * 2. System asks for confirmation
 * 3. Operator confirms the operation
 * 4. System enables/disables the selected tourist account
 * 
 * The program handles edge cases including server connection interruptions (ETOUR)
 * and provides proper notifications of operation outcomes.
 */
public class Main {
    /**
     * Main method - Entry point of the application
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   TOURIST ACCOUNT MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("Developer: Tourist System Team");
        System.out.println("Version: 1.0.0");
        System.out.println("Use Case: Active/Inactive Tourist Account");
        System.out.println("==========================================");
        System.out.println("\nStarting system initialization...");
        
        // Create an instance of AgencyOperator which will handle the user interaction
        AgencyOperator operator = new AgencyOperator();
        
        // Start the interactive feature for activation/deactivation
        operator.runActivationFeature();
        
        System.out.println("\n==========================================");
        System.out.println("System shutdown completed.");
        System.out.println("==========================================");
    }
    
    /**
     * Alternative demonstration method that runs the use case programmatically
     * without user interaction. This can be used for testing or demonstration.
     */
    public static void demonstrateUseCase() {
        System.out.println("\n==========================================");
        System.out.println("DEMONSTRATION: Programmatic Use Case Test");
        System.out.println("==========================================");
        
        AgencyOperator operator = new AgencyOperator();
        
        // Test 1: Activate an inactive account (T002 is inactive in sample data)
        System.out.println("\nTest 1: Activating inactive tourist account (T002)");
        String result1 = operator.processTouristAccountChange("T002", true);
        System.out.println("Result: " + result1);
        
        // Test 2: Deactivate an active account (T001 is active in sample data)
        System.out.println("\nTest 2: Deactivating active tourist account (T001)");
        String result2 = operator.processTouristAccountChange("T001", true);
        System.out.println("Result: " + result2);
        
        // Test 3: Operation cancelled (confirmation denied)
        System.out.println("\nTest 3: Operation with confirmation denied (T003)");
        String result3 = operator.processTouristAccountChange("T003", false);
        System.out.println("Result: " + result3);
        
        // Test 4: Non-existent tourist
        System.out.println("\nTest 4: Non-existent tourist (T999)");
        String result4 = operator.processTouristAccountChange("T999", true);
        System.out.println("Result: " + result4);
        
        // Test 5: Server connection simulation (will show success or failure randomly)
        System.out.println("\nTest 5: Server connection simulation");
        try {
            TouristAccountService service = new TouristAccountService();
            boolean connected = service.checkServerConnection();
            System.out.println("Server Connection: " + (connected ? "✓ Available" : "✗ Interrupted"));
        } catch (Exception e) {
            System.out.println("Server Connection Test Error: " + e.getMessage());
        }
        
        System.out.println("\n==========================================");
        System.out.println("Demonstration completed.");
        System.out.println("==========================================");
    }
}