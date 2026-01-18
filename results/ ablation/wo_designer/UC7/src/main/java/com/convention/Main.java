
package com.convention;

/**
 * Main class to demonstrate the convention activation use case.
 * This is the entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Convention Activation System ===");
        System.out.println("Use Case: Activate Convention for Refreshment Point\n");
        
        // Entry Condition: The Agency IS a designated point of rest.
        System.out.println("Entry Condition: The Agency is a designated point of rest - Verified ✓\n");
        
        // Create an Agency Operator
        AgencyOperator operator = new AgencyOperator("OP001", "John Doe");
        System.out.println("Actor: Agency Operator - " + operator.getOperatorName() + "\n");
        
        // Create Convention Service
        ConventionService conventionService = new ConventionService();
        
        // Activate a convention
        String conventionId = "CONV001";
        System.out.println("Starting activation process for Convention ID: " + conventionId + "\n");
        
        boolean success = conventionService.activateConvention(conventionId, operator);
        
        System.out.println("\n=== Activation Result ===");
        if (success) {
            System.out.println("SUCCESS: Convention activation completed successfully.");
        } else {
            System.out.println("FAILURE: Convention activation failed.");
        }
        
        // Test another convention
        System.out.println("\n\n=== Testing Second Convention ===");
        conventionId = "CONV002";
        System.out.println("Starting activation process for Convention ID: " + conventionId + "\n");
        
        success = conventionService.activateConvention(conventionId, operator);
        
        System.out.println("\n=== Activation Result ===");
        if (success) {
            System.out.println("SUCCESS: Convention activation completed successfully.");
        } else {
            System.out.println("FAILURE: Convention activation failed.");
        }
        
        // Test non-existent convention
        System.out.println("\n\n=== Testing Error Case ===");
        conventionId = "CONV999";
        System.out.println("Starting activation process for Convention ID: " + conventionId + "\n");
        
        success = conventionService.activateConvention(conventionId, operator);
        
        System.out.println("\n=== Activation Result ===");
        if (success) {
            System.out.println("SUCCESS: Convention activation completed successfully.");
        } else {
            System.out.println("FAILURE: Convention activation failed.");
        }
    }
}
