/**
 * Simulates a GPS system responsible for calculating the tourist's position.
 * It includes simulated delays and success/failure conditions.
 */
import java.util.Random;
public class GPSModule {
    private final Random random;
    /**
     * Constructor for GPSModule.
     */
    public GPSModule() {
        this.random = new Random();
    }
    /**
     * Simulates the calculation of a tourist's position by the GPS.
     * This method incorporates a simulated delay (1s-7s) and random success/failure logic.
     * It also checks the quality requirement: a successful transaction should complete within 5 seconds.
     *
     * @return A Position object indicating the result of the localization attempt.
     */
    public Position calculatePosition() {
        // Simulate processing time between 1 and 7 seconds (exclusive for 0ms, exclusive for 8000ms, so 1-7s)
        // This covers cases for 'within 5s' and 'more than 5s'.
        int processingTimeMs = 1000 + random.nextInt(6001); // Random delay between 1000ms and 7000ms
        try {
            // Simulate the GPS working
            Thread.sleep(processingTimeMs);
        } catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
            return Position.createFailed("GPS calculation interrupted.");
        }
        // Apply quality requirement: Transaction should complete within 5 seconds.
        if (processingTimeMs > 5000) {
            return Position.createFailed("Localization timed out (> 5 seconds).");
        }
        // Simulate success or failure (e.g., 80% chance of success for detectable position)
        if (random.nextDouble() < 0.8) { // 80% chance of success (position detectable)
            // Generate random coordinates for demonstration purposes
            double latitude = -90.0 + (90.0 - -90.0) * random.nextDouble();  // -90 to +90
            double longitude = -180.0 + (180.0 - -180.0) * random.nextDouble(); // -180 to +180
            return Position.createSuccess(latitude, longitude);
        } else {
            // 20% chance of failure (position not detectable)
            return Position.createFailed("Position not detectable by GPS.");
        }
    }
}