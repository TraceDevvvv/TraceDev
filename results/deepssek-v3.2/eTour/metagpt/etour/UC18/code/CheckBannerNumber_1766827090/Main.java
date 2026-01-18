import java.util.Scanner;

/**
 * Main class demonstrating the CheckBannerNumber use case.
 * This program provides a complete implementation of the banner checking use case,
 * including handling of edge cases, exceptions, and user interactions.
 */
public class Main {
    
    /**
     * Main method - entry point of the program.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        System.out.println("=== CheckBannerNumber Program ===");
        System.out.println("Use Case: Check if a refreshment point has reached the maximum number of allowed banners\n");
        
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Create refreshment points for testing
            System.out.println("Setting up test scenarios...\n");
            
            // Scenario 1: Refreshment point with available slots
            RefreshmentPoint point1 = new RefreshmentPoint("RP001", "Main Refreshment Area", 
                    "Convention Center - Hall A", 2, 5);
            
            // Scenario 2: Refreshment point at maximum capacity
            RefreshmentPoint point2 = new RefreshmentPoint("RP002", "Snack Bar", 
                    "Convention Center - Hall B", 5, 5);
            
            // Scenario 3: Refreshment point with default constructor
            RefreshmentPoint point3 = new RefreshmentPoint();
            point3.setId("RP003");
            point3.setName("Coffee Station");
            point3.setLocation("Convention Center - Lobby");
            point3.setMaxAllowedBanners(3);
            point3.setCurrentBanners(1);
            
            // Test Scenario 1: Available slots
            System.out.println("\n=== Test Scenario 1: Available Slots ===");
            testRefreshmentPoint(point1);
            
            // Test Scenario 2: Maximum capacity reached
            System.out.println("\n=== Test Scenario 2: Maximum Capacity ===");
            testRefreshmentPoint(point2);
            
            // Test Scenario 3: Default values
            System.out.println("\n=== Test Scenario 3: Default Settings ===");
            testRefreshmentPoint(point3);
            
            // Test Scenario 4: Server connection failure
            System.out.println("\n=== Test Scenario 4: Server Connection Failure ===");
            testServerConnectionFailure(point1);
            
            // Test Scenario 5: Invalid input handling
            System.out.println("\n=== Test Scenario 5: Invalid Input Handling ===");
            testInvalidInputHandling();
            
            // Interactive demonstration
            System.out.println("\n=== Interactive Demonstration ===");
            interactiveDemo(scanner);
            
            System.out.println("\n=== Program Complete ===");
            System.out.println("All use case requirements have been demonstrated:");
            System.out.println("1. Data loading and banner count verification");
            System.out.println("2. Notification display on failure");
            System.out.println("3. Notification confirmation");
            System.out.println("4. State recovery");
            System.out.println("5. Server connection exception handling");
            
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    /**
     * Tests a refreshment point using BannerChecker.
     * 
     * @param point The refreshment point to test
     */
    private static void testRefreshmentPoint(RefreshmentPoint point) {
        System.out.println("Testing Refreshment Point: " + point.getName());
        System.out.println("Details: " + point);
        
        BannerChecker checker = new BannerChecker(point);
        
        try {
            boolean canAdd = checker.checkBannerNumber();
            if (canAdd) {
                System.out.println("Result: Banner can be added successfully.\n");
            } else {
                System.out.println("Result: Cannot add banner - maximum limit reached.\n");
            }
        } catch (ServerConnectionException e) {
            System.out.println("Result: Operation failed due to server connection issue: " 
                + e.getMessage() + "\n");
        } finally {
            checker.close();
        }
    }
    
    /**
     * Tests server connection failure scenario.
     * 
     * @param point The refreshment point to test
     */
    private static void testServerConnectionFailure(RefreshmentPoint point) {
        System.out.println("Simulating server connection failure...");
        
        BannerChecker checker = new BannerChecker(point);
        checker.simulateServerDisconnection();
        
        try {
            boolean canAdd = checker.checkBannerNumber();
            System.out.println("Result (should not reach here): " + canAdd);
        } catch (ServerConnectionException e) {
            System.out.println("✓ Expected exception caught: " + e.getMessage());
            System.out.println("This demonstrates proper handling of ETOUR server connection interruption.\n");
        } finally {
            checker.simulateServerReconnection();
            checker.close();
        }
    }
    
    /**
     * Tests invalid input handling.
     */
    private static void testInvalidInputHandling() {
        System.out.println("Testing invalid input scenarios...");
        
        // Test 1: Null refreshment point
        try {
            new BannerChecker(null);
            System.out.println("✗ Should have thrown exception for null refreshment point");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Null check works: " + e.getMessage());
        }
        
        // Test 2: Negative banner count
        try {
            RefreshmentPoint point = new RefreshmentPoint();
            point.setCurrentBanners(-1);
            System.out.println("✗ Should have thrown exception for negative banner count");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Negative banner count check works: " + e.getMessage());
        }
        
        // Test 3: Banner count exceeding maximum
        try {
            RefreshmentPoint point = new RefreshmentPoint("TEST", "Test", "Location", 6, 5);
            System.out.println("✗ Should have thrown exception for exceeding max banners");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Maximum banner check works: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Interactive demonstration for user.
     * 
     * @param scanner Scanner for user input
     */
    private static void interactiveDemo(Scanner scanner) {
        System.out.println("Would you like to run an interactive demonstration? (yes/no):");
        String answer = scanner.nextLine();
        
        if (!answer.equalsIgnoreCase("yes")) {
            System.out.println("Skipping interactive demonstration.\n");
            return;
        }
        
        System.out.println("\n=== Interactive Demonstration ===");
        System.out.println("Create a custom refreshment point:");
        
        System.out.print("Enter refreshment point name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        
        System.out.print("Enter maximum allowed banners: ");
        int maxBanners = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter current number of banners: ");
        int currentBanners = Integer.parseInt(scanner.nextLine());
        
        try {
            RefreshmentPoint customPoint = new RefreshmentPoint(
                "CUSTOM", name, location, currentBanners, maxBanners);
            
            System.out.println("\nYour custom refreshment point: " + customPoint);
            
            BannerChecker checker = new BannerChecker(customPoint);
            
            System.out.println("\nPerforming banner check...");
            boolean canAdd = checker.checkBannerNumber();
            
            if (canAdd) {
                System.out.println("\n✓ Your refreshment point can accommodate a new banner!");
            } else {
                System.out.println("\n✗ Your refreshment point has reached the maximum banner limit.");
            }
            
            checker.close();
            
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (ServerConnectionException e) {
            System.out.println("Server error: " + e.getMessage());
        }
        
        System.out.println("\nInteractive demonstration complete.\n");
    }
}