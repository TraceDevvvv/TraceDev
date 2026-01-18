/*
MenuManager class handles the business logic related to menu operations.
In a real application, this would interact with a database, a remote API,
or other persistent storage mechanisms to manage menu data.
For this simulation, it simply logs the "deletion" operation.
*/
import java.util.Random; // Required for simulating random failures
public class MenuManager {
    // Initialize Random for simulating successes/failures. Using a secure random for general purpose,
    // though a simple Random would suffice for this simulation.
    private final Random random = new Random();
    /**
     * Simulates the deletion of a daily menu for a restaurant.
     * This method represents the backend operation that would remove
     * the menu data associated with a specific day.
     *
     * In a production system:
     * - This would involve database transactions (e.g., DELETE SQL query).
     * - It might communicate with a remote server (e.g., via REST API).
     * - Error handling for network issues, database connection, etc., would be crucial.
     *
     * For this simulation, it includes a small delay to mimic processing time
     * and prints a message to the console to indicate the "deletion".
     * It also simulates a random failure to represent server connection issues or other errors,
     * making the "Interruption of the connection to the server ETOUR" exit condition more demonstrable.
     *
     * @param day The specific day of the week for which the menu is to be deleted (e.g., "Monday").
     * @return true if the simulated deletion was successful, false if it failed
     *         (e.g., due to an interruption, simulating a server/connection issue).
     */
    public boolean deleteMenu(String day) {
        System.out.println("DEBUG: Attempting to delete menu for: " + day);
        // Simulate some processing time or a network call delay.
        // This also provides a point where an InterruptedException could theoretically occur.
        try {
            Thread.sleep(800); // Wait for 800 milliseconds
        } catch (InterruptedException e) {
            // If the thread is interrupted during sleep, it might indicate an issue
            // (e.g., server disconnection, forced shutdown).
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("ERROR: Menu deletion for " + day + " interrupted: " + e.getMessage());
            return false; // Indicate failure
        }
        // Simulate a general server failure or "ETOUR" interruption with a 20% chance.
        // This makes the "failure" path more testable without requiring an actual thread interruption.
        // This covers the scenario of "Interruption of the connection to the server ETOUR" more practically.
        if (random.nextInt(100) < 20) { // Approximately 20% chance of a simulated failure
            System.err.println("ERROR: Simulated server connection issue or other backend failure for: " + day);
            return false; // Indicate failure due to simulated problem
        }
        // In a real scenario, conditional logic would be here
        // to check if the database operation was successful, etc.
        // For this simple simulation, we always "succeed" unless interrupted or randomly failed.
        System.out.println("DEBUG: Successfully (simulated) deleted menu for: " + day);
        return true;
    }
}