/**
 * Simulates an external service (ETOUR) for managing tourist data.
 * It provides methods to retrieve lists of tourists and their detailed information,
 * including a mechanism to simulate connection errors.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit; // For simulating network delays
public class ETOURService {
    private Map<String, Tourist> mockTouristData; // Stores tourists by ID for quick lookup
    private boolean simulateConnectionError = false; // Flag to simulate server connection issues
    /**
     * Initializes the ETOURService with mock tourist data.
     */
    public ETOURService() {
        mockTouristData = new HashMap<>();
        // Populate with some sample tourist data
        addMockTourist(new Tourist("T001", "Mario", "Rossi", "mario.rossi@example.com", "3331234567", "Via Roma 1, Milano"));
        addMockTourist(new Tourist("T002", "Laura", "Bianchi", "laura.bianchi@example.com", "3479876543", "Piazza Duomo 10, Firenze"));
        addMockTourist(new Tourist("T003", "Giuseppe", "Verdi", "giuseppe.verdi@example.com", "3401122334", "Corso Italia 50, Napoli"));
        addMockTourist(new Tourist("T004", "Anna", "Neri", "anna.neri@example.com", "3395566778", "Viale Venezia 23, Roma"));
        addMockTourist(new Tourist("T005", "Marco", "Gialli", "marco.gialli@example.com", "3351122334", "Via Dante 7, Torino"));
        addMockTourist(new Tourist("T006", "Sofia", "Bruno", "sofia.bruno@example.com", "3489988776", "Corso Vittorio Emanuele 15, Palermo"));
    }
    /**
     * Adds a tourist to the mock data.
     * @param tourist The Tourist object to add.
     */
    private void addMockTourist(Tourist tourist) {
        mockTouristData.put(tourist.getId(), tourist);
    }
    /**
     * Retrieves a list of all tourists.
     * @return A List of Tourist objects.
     * @throws ETOURConnectionException If a simulated connection error occurs.
     */
    public List<Tourist> getTouristList() throws ETOURConnectionException {
        simulateNetworkDelay();
        if (simulateConnectionError) {
            throw new ETOURConnectionException("Connection to ETOUR server interrupted while fetching tourist list.");
        }
        return new ArrayList<>(mockTouristData.values());
    }
    /**
     * Retrieves the detailed information for a specific tourist by their ID.
     * @param touristId The ID of the tourist to retrieve.
     * @return The Tourist object if found, otherwise null.
     * @throws ETOURConnectionException If a simulated connection error occurs.
     */
    public Tourist getTouristDetails(String touristId) throws ETOURConnectionException {
        simulateNetworkDelay();
        if (simulateConnectionError) {
            throw new ETOURConnectionException("Connection to ETOUR server interrupted while fetching tourist details for ID: " + touristId);
        }
        return mockTouristData.get(touristId);
    }
    /**
     * Simulates a network delay to mimic real-world service calls.
     */
    private void simulateNetworkDelay() {
        try {
            TimeUnit.MILLISECONDS.sleep(500); // Simulate 0.5-second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
    /**
     * Sets whether to simulate a connection error for subsequent calls.
     * @param error True to simulate an error, false otherwise.
     */
    public void setSimulateConnectionError(boolean error) {
        this.simulateConnectionError = error;
    }
}