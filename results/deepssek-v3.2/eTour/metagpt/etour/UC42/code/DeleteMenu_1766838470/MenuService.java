import java.util.Random;

/**
 * Service class for menu operations.
 * Contains business logic for deleting daily menus and handling server connections.
 * Simulates server interactions and includes validation.
 */
public class MenuService {
    private Random random;
    private static final double SERVER_FAILURE_PROBABILITY = 0.2; // 20% chance of server failure
    
    /**
     * Constructor initializes the service.
     */
    public MenuService() {
        this.random = new Random();
    }
    
    /**
     * Deletes the daily menu for the specified day.
     * Validates the day parameter and simulates server interaction.
     * Randomly simulates server connection failures to demonstrate exception handling.
     * 
     * @param dayNumber the day number (1-7 where 1=Monday, 7=Sunday)
     * @throws IllegalArgumentException if dayNumber is not between 1 and 7
     * @throws ServerConnectionException if server connection is interrupted
     */
    public void deleteDailyMenu(int dayNumber) throws ServerConnectionException {
        // Validate input
        if (dayNumber < 1 || dayNumber > 7) {
            throw new IllegalArgumentException("Invalid day number. Must be between 1 and 7.");
        }
        
        // Simulate server connection check
        checkServerConnection();
        
        // Convert day number to day name
        String dayName = getDayName(dayNumber);
        
        // Simulate deletion process
        System.out.println("Connecting to database to delete menu for " + dayName + "...");
        
        // Simulate processing delay
        try {
            Thread.sleep(1000); // 1 second delay to simulate processing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServerConnectionException("Deletion process interrupted", e);
        }
        
        // Simulate actual deletion
        System.out.println("Menu for " + dayName + " deleted successfully from database.");
        
        // Simulate additional cleanup or logging
        logDeletion(dayName);
    }
    
    /**
     * Checks server connection and randomly simulates connection failures.
     * This simulates the ETOUR server connection interruption mentioned in requirements.
     * 
     * @throws ServerConnectionException if server connection fails
     */
    private void checkServerConnection() throws ServerConnectionException {
        System.out.println("Checking connection to ETOUR server...");
        
        // Simulate connection check delay
        try {
            Thread.sleep(500); // 0.5 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServerConnectionException("Connection check interrupted", e);
        }
        
        // Randomly simulate server connection failure
        if (random.nextDouble() < SERVER_FAILURE_PROBABILITY) {
            throw new ServerConnectionException("ETOUR server connection interrupted. " +
                                              "Please check your network connection and try again.");
        }
        
        System.out.println("Server connection established successfully.");
    }
    
    /**
     * Logs the deletion operation for audit purposes.
     * 
     * @param dayName the name of the day whose menu was deleted
     */
    private void logDeletion(String dayName) {
        // In a real system, this would write to a log file or database
        System.out.println("Audit log: Menu deletion for " + dayName + " completed at " + 
                          java.time.LocalDateTime.now());
    }
    
    /**
     * Converts day number (1-7) to day name.
     * 
     * @param dayNumber the day number (1=Monday, 7=Sunday)
     * @return the name of the day
     */
    private String getDayName(int dayNumber) {
        switch (dayNumber) {
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            case 7: return "Sunday";
            default: return "Invalid Day";
        }
    }
    
    /**
     * Gets the current server status (for testing/diagnostic purposes).
     * 
     * @return true if server is likely to connect successfully (based on probability)
     */
    public boolean isServerLikelyAvailable() {
        return random.nextDouble() >= SERVER_FAILURE_PROBABILITY;
    }
}