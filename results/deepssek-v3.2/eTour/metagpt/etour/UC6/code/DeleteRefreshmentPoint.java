import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Main application class for the DeleteRefreshmentPoint use case.
 * This program simulates a refreshment point management system where an agency operator
 * can delete refreshment points with proper confirmation and error handling.
 */
public class DeleteRefreshmentPoint {
    
    /**
     * Model class representing a refreshment point (point of rest).
     */
    static class RefreshmentPoint {
        private int id;
        private String name;
        private String location;
        private String description;
        
        public RefreshmentPoint(int id, String name, String location, String description) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.description = description;
        }
        
        public int getId() { return id; }
        public String getName() { return name; }
        public String getLocation() { return location; }
        public String getDescription() { return description; }
        
        @Override
        public String toString() {
            return String.format("ID: %d | Name: %s | Location: %s | Description: %s", 
                               id, name, location, description);
        }
    }
    
    /**
     * Service class for managing refreshment point operations including deletion.
     */
    static class RefreshmentPointService {
        private List<RefreshmentPoint> refreshmentPoints;
        private static final AtomicInteger idCounter = new AtomicInteger(1);
        
        public RefreshmentPointService() {
            this.refreshmentPoints = new ArrayList<>();
            // Initialize with some sample data for demonstration
            initializeSampleData();
        }
        
        /**
         * Initialize some sample refreshment points for demonstration purposes.
         */
        private void initializeSampleData() {
            refreshmentPoints.add(new RefreshmentPoint(idCounter.getAndIncrement(), 
                "Mountain Cafe", "Alpine Valley", "Coffee shop with mountain view"));
            refreshmentPoints.add(new RefreshmentPoint(idCounter.getAndIncrement(), 
                "River Rest", "Riverbank Trail", "Rest area by the river"));
            refreshmentPoints.add(new RefreshmentPoint(idCounter.getAndIncrement(), 
                "Forest Lodge", "Deep Woods", "Lodge in the forest with refreshments"));
            refreshmentPoints.add(new RefreshmentPoint(idCounter.getAndIncrement(), 
                "Heritage Inn", "Historic District", "Traditional inn with local cuisine"));
        }
        
        /**
         * Simulates searching cultural heritage to get a list of refreshment points.
         * In a real application, this would connect to a database or external service.
         * 
         * @return List of all refreshment points
         */
        public List<RefreshmentPoint> searchCulturalHeritage() {
            System.out.println("Searching cultural heritage refreshment points...");
            return new ArrayList<>(refreshmentPoints); // Return a copy
        }
        
        /**
         * Deletes a refreshment point by ID with proper validation.
         * 
         * @param id The ID of the refreshment point to delete
         * @return true if deletion was successful, false otherwise
         */
        public boolean deleteRefreshmentPoint(int id) {
            // Check server connection before performing deletion
            if (!checkServerConnection()) {
                System.out.println("Error: Cannot connect to server. Operation aborted.");
                return false;
            }
            
            // Find the point to delete
            RefreshmentPoint pointToDelete = null;
            for (RefreshmentPoint point : refreshmentPoints) {
                if (point.getId() == id) {
                    pointToDelete = point;
                    break;
                }
            }
            
            if (pointToDelete == null) {
                System.out.println("Error: Refreshment point with ID " + id + " not found.");
                return false;
            }
            
            // Perform deletion
            boolean removed = refreshmentPoints.removeIf(point -> point.getId() == id);
            if (removed) {
                System.out.println("Successfully deleted refreshment point: " + pointToDelete.getName());
                return true;
            } else {
                System.out.println("Error: Failed to delete refreshment point.");
                return false;
            }
        }
        
        /**
         * Checks if the server is available by simulating a connection check.
         * This handles the "ETOUR connection to the server" interruption requirement.
         * 
         * @return true if server is available, false otherwise
         */
        private boolean checkServerConnection() {
            // In a real application, this would check actual server connectivity
            // For simulation, we'll assume connection is available 90% of the time
            try {
                // Simulate network check with small delay
                Thread.sleep(100);
                // Simulate occasional connection failure (10% chance)
                return Math.random() > 0.1;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        
        /**
         * Displays all refreshment points with their details.
         */
        public void displayAllPoints() {
            if (refreshmentPoints.isEmpty()) {
                System.out.println("No refreshment points available.");
            } else {
                System.out.println("\n=== Available Refreshment Points ===");
                for (RefreshmentPoint point : refreshmentPoints) {
                    System.out.println(point);
                }
                System.out.println("====================================\n");
            }
        }
    }
    
    /**
     * Main method - entry point of the application.
     * Simulates the flow of the DeleteRefreshmentPoint use case.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RefreshmentPointService service = new RefreshmentPointService();
        
        System.out.println("=== Refreshment Point Management System ===");
        System.out.println("Agency Operator Logged In Successfully.");
        
        boolean continueProgram = true;
        
        while (continueProgram) {
            try {
                // Step 1: Display list of refreshment points from SearchCulturalHeritage
                System.out.println("\n--- Step 1: View Refreshment Points ---");
                List<RefreshmentPoint> points = service.searchCulturalHeritage();
                
                if (points.isEmpty()) {
                    System.out.println("No refreshment points found. Exiting program.");
                    break;
                }
                
                service.displayAllPoints();
                
                // Get user selection
                System.out.print("Enter the ID of the refreshment point to delete (or 0 to exit): ");
                int selectedId = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (selectedId == 0) {
                    System.out.println("Operation cancelled by agency operator.");
                    continueProgram = false;
                    continue;
                }
                
                // Validate selection
                boolean validId = false;
                for (RefreshmentPoint point : points) {
                    if (point.getId() == selectedId) {
                        validId = true;
                        System.out.println("Selected: " + point.getName() + " at " + point.getLocation());
                        break;
                    }
                }
                
                if (!validId) {
                    System.out.println("Invalid ID selected. Please try again.");
                    continue;
                }
                
                // Step 2: Ask for confirmation
                System.out.println("\n--- Step 2: Confirm Deletion ---");
                System.out.print("Are you sure you want to delete this refreshment point? (yes/no): ");
                String confirmation = scanner.nextLine().trim().toLowerCase();
                
                // Step 3: Handle confirmation
                if (confirmation.equals("yes") || confirmation.equals("y")) {
                    // Step 4: Perform deletion
                    System.out.println("\n--- Step 4: Deleting Refreshment Point ---");
                    boolean success = service.deleteRefreshmentPoint(selectedId);
                    
                    // Exit condition: Notify successful elimination
                    if (success) {
                        System.out.println("\n✅ SUCCESS: Refreshment point has been successfully eliminated from the system.");
                    } else {
                        System.out.println("\n❌ FAILURE: Could not delete refreshment point.");
                    }
                    
                    // Show updated list
                    System.out.println("\nUpdated list of refreshment points:");
                    service.displayAllPoints();
                    
                } else {
                    // Operator cancels the operation
                    System.out.println("Deletion cancelled by operator.");
                }
                
                // Ask if user wants to perform another operation
                System.out.print("\nDo you want to delete another refreshment point? (yes/no): ");
                String continueChoice = scanner.nextLine().trim().toLowerCase();
                if (!continueChoice.equals("yes") && !continueChoice.equals("y")) {
                    continueProgram = false;
                    System.out.println("Exiting program. Goodbye!");
                }
                
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for ID.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
        
        scanner.close();
    }
}