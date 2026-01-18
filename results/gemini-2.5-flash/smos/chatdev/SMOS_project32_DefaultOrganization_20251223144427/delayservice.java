'''
This class simulates a service layer that interacts with a backend server
to fetch and save delay data. It uses an in-memory map for simulation
and includes artificial network delays.
'''
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class DelayService {
    // Simulates a database or server-side storage for delays per date
    private final Map<LocalDate, Integer> delayData;
    private final Random random;
    /**
     * Constructor for DelayService.
     * Initializes the in-memory data store and populates with some sample data.
     */
    public DelayService() {
        delayData = new HashMap<>();
        random = new Random();
        // Populate with some initial data for testing
        delayData.put(LocalDate.now(), 500); // Today's delay
        delayData.put(LocalDate.now().plusDays(1), 1000); // Tomorrow's delay
        delayData.put(LocalDate.parse("2023-10-26"), 250); // A specific date's delay
    }
    /**
     * Simulates fetching a delay value from the server for a given date.
     * Includes a simulated network delay and potential connection interruption.
     *
     * @param date The date for which to fetch the delay.
     * @return An Optional containing the delay value if found, or empty if not found or connection interrupted.
     * @throws DelayServiceException If there's a simulated connection interruption.
     */
    public Optional<Integer> fetchDelay(LocalDate date) throws DelayServiceException {
        // Simulate network delay
        simulateNetworkDelay();
        // Simulate connection interruption randomly
        if (random.nextInt(10) == 0) { // 10% chance of interruption
            throw new DelayServiceException("Connection to SMOS server interrupted during fetch.");
        }
        // Return the delay if present, otherwise an empty Optional
        return Optional.ofNullable(delayData.get(date));
    }
    /**
     * Simulates saving a delay value to the server for a given date.
     * Includes a simulated network delay and potential connection interruption.
     *
     * @param date The date for which to save the delay.
     * @param delay The delay value to save.
     * @return True if the save operation was successful, false otherwise (e.g., if null date/delay).
     * @throws DelayServiceException If there's a simulated connection interruption.
     */
    public boolean saveDelay(LocalDate date, int delay) throws DelayServiceException {
        if (date == null) {
            return false;
        }
        // Simulate network delay
        simulateNetworkDelay();
        // Simulate connection interruption randomly
        if (random.nextInt(8) == 0) { // Slightly lower chance of interruption for saving
            throw new DelayServiceException("Connection to SMOS server interrupted during save.");
        }
        delayData.put(date, delay); // Update the in-memory "database"
        return true;
    }
    /**
     * Helper method to simulate network latency.
     */
    private void simulateNetworkDelay() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(500) + 200); // 200-700 ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("Network delay simulation interrupted: " + e.getMessage());
        }
    }
}
'''
Custom exception for simulating server connection issues.
'''
class DelayServiceException extends Exception {
    public DelayServiceException(String message) {
        super(message);
    }
}