// Main.java
// Entry point for the SearchTourist program.
// This class contains the main method that starts the application.

/**
 * Main class for the SearchTourist program.
 * This is the entry point that initializes and executes the SearchTourist use case.
 * The program simulates an agency operator searching for tourist accounts in the ETOUR system.
 */
public class Main {
    
    /**
     * Main method - program entry point.
     * Creates an instance of SearchTouristUseCase and executes it.
     * Handles any unexpected exceptions and ensures proper cleanup.
     * 
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        System.out.println("=== Starting ETOUR Tourist Search System ===");
        System.out.println("Initializing system components...");
        
        // Create the use case instance
        SearchTouristUseCase useCase = new SearchTouristUseCase();
        
        try {
            // Execute the use case
            useCase.executeUseCase();
        } catch (Exception e) {
            // Handle any unexpected exceptions
            System.err.println("A critical error occurred in the application:");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.out.println("\nThe application will now exit.");
        } finally {
            // Ensure cleanup of resources
            useCase.cleanup();
            System.out.println("\nSystem shutdown complete.");
        }
    }
}