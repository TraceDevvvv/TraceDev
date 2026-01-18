/**
 * Test class to demonstrate the Delete Menu application with different scenarios.
 * This class provides a main method that can be run to see the application in action.
 * It simulates user inputs programmatically to demonstrate:
 * 1. Successful deletion
 * 2. Operator cancellation
 * 3. Server connection interruption
 */
public class DeleteMenuApplicationTest {
    
    /**
     * Main method to demonstrate different scenarios of the Delete Menu application.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Delete Menu Application Test Suite ===\n");
        
        // Test Scenario 1: Successful deletion
        testSuccessfulDeletion();
        
        // Test Scenario 2: Operator cancels operation
        testOperatorCancellation();
        
        // Test Scenario 3: Server connection interruption
        testServerConnectionInterruption();
        
        // Test Scenario 4: Invalid day selection handling
        testInvalidDaySelectionHandling();
        
        System.out.println("\n=== All test scenarios completed ===");
    }
    
    /**
     * Tests the successful deletion scenario.
     * Simulates a user selecting a valid day and confirming deletion.
     * Demonstrates the normal flow of the application.
     */
    private static void testSuccessfulDeletion() {
        System.out.println("\n--- Test Scenario 1: Successful Deletion ---");
        System.out.println("Simulating: User selects Monday (day 1) and confirms deletion.");
        
        // Create a mock scanner with predefined inputs
        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream("1\nyes\n".getBytes());
        java.util.Scanner mockScanner = new java.util.Scanner(in);
        
        try {
            // Create application components
            ConsoleUI consoleUI = new ConsoleUI();
            MenuService menuService = new MenuService();
            
            // Simulate the flow
            System.out.println("\n[Simulated UI Flow]");
            consoleUI.displayDaysOfWeek();
            
            // Simulate day selection (input "1" for Monday)
            System.out.print("Enter your choice (1-7, or 0 to cancel): 1\n");
            int daySelection = 1; // Simulated user input
            System.out.println("Selected day: Monday");
            
            // Simulate confirmation (input "yes")
            System.out.print("Are you sure you want to delete this daily menu? (yes/no): yes\n");
            boolean confirmed = consoleUI.confirmDeletion(mockScanner, daySelection);
            
            if (confirmed) {
                // Try to delete the menu (with potential server failure)
                try {
                    menuService.deleteDailyMenu(daySelection);
                    consoleUI.showSuccessMessage(daySelection);
                    System.out.println("\n[Result: SUCCESS] Menu deleted successfully.");
                } catch (ServerConnectionException e) {
                    System.out.println("\n[Result: SERVER ERROR] " + e.getMessage());
                }
            }
        } catch (OperationCancelledException e) {
            System.out.println("\n[Result: CANCELLED] " + e.getMessage());
        } finally {
            mockScanner.close();
        }
    }
    
    /**
     * Tests the operator cancellation scenario.
     * Simulates a user selecting a day but then cancelling the operation.
     */
    private static void testOperatorCancellation() {
        System.out.println("\n--- Test Scenario 2: Operator Cancellation ---");
        System.out.println("Simulating: User selects Wednesday (day 3) but cancels at confirmation.");
        
        // Create a mock scanner with predefined inputs
        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream("3\nno\n".getBytes());
        java.util.Scanner mockScanner = new java.util.Scanner(in);
        
        try {
            // Create application components
            ConsoleUI consoleUI = new ConsoleUI();
            MenuService menuService = new MenuService();
            
            // Simulate the flow
            System.out.println("\n[Simulated UI Flow]");
            consoleUI.displayDaysOfWeek();
            
            // Simulate day selection (input "3" for Wednesday)
            System.out.print("Enter your choice (1-7, or 0 to cancel): 3\n");
            int daySelection = 3; // Simulated user input
            System.out.println("Selected day: Wednesday");
            
            // Simulate cancellation (input "no")
            System.out.print("Are you sure you want to delete this daily menu? (yes/no): no\n");
            boolean confirmed = consoleUI.confirmDeletion(mockScanner, daySelection);
            
            if (!confirmed) {
                System.out.println("\n[Result: CANCELLED] Operation cancelled by operator as expected.");
            } else {
                System.out.println("\n[Result: UNEXPECTED] Operation was not cancelled.");
            }
        } catch (Exception e) {
            System.out.println("\n[Result: ERROR] " + e.getMessage());
        } finally {
            mockScanner.close();
        }
    }
    
    /**
     * Tests the server connection interruption scenario.
     * Uses a MenuService with high failure probability to simulate server issues.
     */
    private static void testServerConnectionInterruption() {
        System.out.println("\n--- Test Scenario 3: Server Connection Interruption ---");
        System.out.println("Simulating: Server connection fails during menu deletion.");
        
        // Create a mock scanner with predefined inputs
        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream("5\nyes\n".getBytes());
        java.util.Scanner mockScanner = new java.util.Scanner(in);
        
        try {
            // Create application components
            ConsoleUI consoleUI = new ConsoleUI();
            MenuService menuService = new MenuService();
            
            // Simulate the flow
            System.out.println("\n[Simulated UI Flow]");
            consoleUI.displayDaysOfWeek();
            
            // Simulate day selection (input "5" for Friday)
            System.out.print("Enter your choice (1-7, or 0 to cancel): 5\n");
            int daySelection = 5; // Simulated user input
            System.out.println("Selected day: Friday");
            
            // Simulate confirmation (input "yes")
            System.out.print("Are you sure you want to delete this daily menu? (yes/no): yes\n");
            boolean confirmed = consoleUI.confirmDeletion(mockScanner, daySelection);
            
            if (confirmed) {
                // Try to delete the menu multiple times to demonstrate server failure
                System.out.println("\n[Testing with potential server failures...]");
                
                int attempts = 0;
                int maxAttempts = 5;
                boolean success = false;
                
                while (attempts < maxAttempts && !success) {
                    attempts++;
                    System.out.println("\nAttempt " + attempts + ":");
                    
                    try {
                        menuService.deleteDailyMenu(daySelection);
                        consoleUI.showSuccessMessage(daySelection);
                        success = true;
                        System.out.println("[Result: SUCCESS on attempt " + attempts + "]");
                    } catch (ServerConnectionException e) {
                        System.out.println("[Server Error: " + e.getMessage() + "]");
                        
                        if (attempts < maxAttempts) {
                            System.out.println("Retrying...");
                        }
                    }
                }
                
                if (!success) {
                    System.out.println("\n[Result: FAILED] Could not delete menu after " + maxAttempts + 
                                      " attempts due to server connection issues.");
                }
            }
        } catch (OperationCancelledException e) {
            System.out.println("\n[Result: CANCELLED] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[Result: ERROR] " + e.getMessage());
        } finally {
            mockScanner.close();
        }
    }
    
    /**
     * Tests invalid day selection handling.
     * Demonstrates how the application handles invalid inputs.
     */
    private static void testInvalidDaySelectionHandling() {
        System.out.println("\n--- Test Scenario 4: Invalid Day Selection Handling ---");
        System.out.println("Simulating: User enters invalid day numbers and then cancels.");
        
        // Create a mock scanner with predefined inputs: invalid number, then cancel
        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream("9\n0\n".getBytes());
        java.util.Scanner mockScanner = new java.util.Scanner(in);
        
        try {
            // Create application components
            ConsoleUI consoleUI = new ConsoleUI();
            
            // Simulate the flow
            System.out.println("\n[Simulated UI Flow]");
            consoleUI.displayDaysOfWeek();
            
            // The ConsoleUI.getDaySelection() method will handle invalid input
            // For this test, we'll simulate what happens with invalid input
            System.out.print("Enter your choice (1-7, or 0 to cancel): 9\n");
            System.out.println("[Expected: 'Invalid selection. Please enter a number between 1 and 7 (or 0 to cancel):']");
            
            System.out.print("Enter your choice (1-7, or 0 to cancel): 0\n");
            System.out.println("[Expected: Operation cancelled by operator.]");
            
            // Actually try to get selection to trigger exception
            int daySelection = consoleUI.getDaySelection(mockScanner);
            System.out.println("\n[Result: UNEXPECTED] Got selection: " + daySelection);
            
        } catch (OperationCancelledException e) {
            System.out.println("\n[Result: CANCELLED] " + e.getMessage() + " (as expected)");
        } catch (Exception e) {
            System.out.println("\n[Result: ERROR] " + e.getMessage());
        } finally {
            mockScanner.close();
        }
    }
    
    /**
     * Helper method to demonstrate the application's robustness.
     * Shows how the application handles edge cases gracefully.
     */
    public static void demonstrateEdgeCases() {
        System.out.println("\n=== Demonstrating Edge Cases ===");
        
        // Test with invalid day number (should throw IllegalArgumentException)
        try {
            MenuService service = new MenuService();
            service.deleteDailyMenu(0); // Invalid day number
            System.out.println("[UNEXPECTED] No exception thrown for invalid day number");
        } catch (IllegalArgumentException e) {
            System.out.println("[EXPECTED] IllegalArgumentException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[OTHER EXCEPTION] " + e.getMessage());
        }
        
        // Test with day number out of range (should throw IllegalArgumentException)
        try {
            MenuService service = new MenuService();
            service.deleteDailyMenu(10); // Invalid day number
            System.out.println("[UNEXPECTED] No exception thrown for out-of-range day number");
        } catch (IllegalArgumentException e) {
            System.out.println("[EXPECTED] IllegalArgumentException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[OTHER EXCEPTION] " + e.getMessage());
        }
        
        System.out.println("\nEdge case demonstration completed.");
    }
}