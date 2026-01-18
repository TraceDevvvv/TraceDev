/**
 * Simulates the RicercaSito use case, providing data related to locations and their feedback.
 * This class acts as a data service layer, potentially interacting with a backend system.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
public class RicercaSitoService {
    private final List<Location> mockLocations;
    private final Random random;
    private boolean simulateConnectionError = false; // Flag to manually trigger a connection error
    /**
     * Constructs a new RicercaSitoService and initializes mock data.
     */
    public RicercaSitoService() {
        random = new Random();
        // Initialize with some mock locations
        mockLocations = Arrays.asList(
            new Location(101, "Colosseum, Rome"),
            new Location(102, "Eiffel Tower, Paris"),
            new Location(103, "Central Park, New York"),
            new Location(104, "Sagrada Familia, Barcelona"),
            new Location(105, "Tokyo Tower, Tokyo")
        );
    }
    /**
     * Retrieves a list of all available locations.
     * Simulates network latency and potential connection interruptions.
     * @return A list of Location objects.
     * @throws IOException If a simulated connection interruption occurs.
     */
    public List<Location> getAllLocations() throws IOException {
        simulateNetworkDelay();
        // If simulateConnectionError is true, force an error. Otherwise, use random chance.
        if (simulateConnectionError || random.nextDouble() < 0.1) { // 10% chance of random error
            throw new IOException("Failed to connect to the server to retrieve locations. Please check your network.");
        }
        return new ArrayList<>(mockLocations); // Return a copy to prevent external modification
    }
    /**
     * Retrieves statistical feedback data for a given location ID.
     * This method simulates the outcome of the 'RicercaSito' use case for detailed data.
     * Simulates network latency and potential connection interruptions.
     * @param locationId The ID of the location for which to fetch data.
     * @return A StatisticalReport object containing the aggregated data.
     * @throws IOException If a simulated connection interruption occurs.
     * @throws IllegalArgumentException If the location ID is not found.
     */
    public StatisticalReport getStatisticalDataForLocation(int locationId) throws IOException, IllegalArgumentException {
        simulateNetworkDelay();
        // If simulateConnectionError is true, force an error. Otherwise, use random chance.
        if (simulateConnectionError || random.nextDouble() < 0.15) { // 15% chance of random error
            throw new IOException("Failed to connect to the server to retrieve report data. Please check your network.");
        }
        // Find the location by ID
        Location selectedLocation = mockLocations.stream()
            .filter(loc -> loc.getId() == locationId)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Location with ID " + locationId + " not found."));
        // Simulate statistical data for the selected location
        double averageRating = 2.0 + (random.nextDouble() * 3.0); // Rating between 2.0 and 5.0
        int totalFeedbackCount = 50 + random.nextInt(500); // 50 to 549 feedbacks
        String[] feedbackTypes = {"Positive", "Neutral", "Negative", "Mixed"};
        String mostCommonType = feedbackTypes[random.nextInt(feedbackTypes.length)];
        return new StatisticalReport(
            selectedLocation.getId(),
            selectedLocation.getName(),
            averageRating,
            totalFeedbackCount,
            mostCommonType
        );
    }
    /**
     * Simulates a network delay to mimic real-world network operations.
     */
    private void simulateNetworkDelay() {
        try {
            Thread.sleep(500 + random.nextInt(1500)); // Delay between 0.5 to 2 seconds
        } catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
            System.err.println("Network delay simulation interrupted: " + e.getMessage());
        }
    }
    /**
     * Sets a flag to simulate a connection error on the next network call.
     * @param simulate true to force a connection error, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulate) {
        this.simulateConnectionError = simulate;
    }
}