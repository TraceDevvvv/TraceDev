/**
 * Main application class demonstrating the Localization use case.
 * This is a complete, runnable program as required.
 */
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
/**
 * GPSPosition represents geographic coordinates with latitude, longitude and accuracy.
 * This class encapsulates the position data returned by GPS.
 */
class GPSPosition {
    private double latitude;
    private double longitude;
    private double accuracy; // in meters
    public GPSPosition(double latitude, double longitude, double accuracy) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
    }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public double getAccuracy() { return accuracy; }
    @Override
    public String toString() {
        return String.format("Latitude: %.6f, Longitude: %.6f, Accuracy: %.2fm", 
                           latitude, longitude, accuracy);
    }
}
/**
 * GPSException is thrown when GPS cannot determine position or encounters an error.
 */
class GPSException extends Exception {
    public GPSException(String message) {
        super(message);
    }
}
/**
 * GPSProvider simulates GPS hardware that calculates tourist position.
 * It includes timeout handling as per quality requirement (5 seconds maximum).
 */
class GPSProvider {
    private Random random = new Random();
    /**
     * Calculates current position with simulated GPS delay.
     * Simulates real GPS behavior with random success/failure and delay.
     * 
     * @return GPSPosition object with coordinates
     * @throws GPSException if position cannot be determined or timeout occurs
     */
    public GPSPosition calculatePosition() throws GPSException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<GPSPosition> future = executor.submit(() -> {
            // Simulate GPS calculation delay (0-4 seconds to meet <5s requirement)
            int delay = random.nextInt(4000);
            Thread.sleep(delay);
            // Simulate GPS signal availability (80% success rate)
            if (random.nextDouble() < 0.8) {
                // Generate random position near a tourist area (e.g., Paris)
                double latitude = 48.8566 + (random.nextDouble() - 0.5) * 0.01;
                double longitude = 2.3522 + (random.nextDouble() - 0.5) * 0.01;
                double accuracy = 5.0 + random.nextDouble() * 15.0; // 5-20m accuracy
                return new GPSPosition(latitude, longitude, accuracy);
            } else {
                // Simulate GPS signal loss
                throw new GPSException("GPS signal not available");
            }
        });
        try {
            // Wait for position with 5-second timeout as per quality requirement
            GPSPosition position = future.get(5, TimeUnit.SECONDS);
            executor.shutdown();
            return position;
        } catch (TimeoutException e) {
            future.cancel(true);
            executor.shutdown();
            throw new GPSException("GPS calculation exceeded 5 second timeout");
        } catch (ExecutionException e) {
            executor.shutdown();
            if (e.getCause() instanceof GPSException) {
                throw (GPSException) e.getCause();
            }
            throw new GPSException("GPS system error: " + e.getCause().getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executor.shutdown();
            throw new GPSException("GPS operation interrupted");
        }
    }
}
/**
 * LocalizationSystem handles the complete GPS localization use case for tourists.
 * Manages the flow: request position → calculate → wait → return result.
 */
class LocalizationSystem {
    private GPSProvider gpsProvider;
    public LocalizationSystem() {
        this.gpsProvider = new GPSProvider();
    }
    /**
     * Main method to get tourist position according to use case flow.
     * Entry condition: Tourist began a search or advanced search.
     * Flow: 1. System requires position data
     *       2. GPS calculates position
     *       3. System waits for position data
     * Exit: Returns position or null if not detectable.
     * 
     * @return GPSPosition or null if position cannot be determined
     */
    public GPSPosition getTouristPosition() {
        // Step 1: System requires position data
        System.out.println("System: Requesting tourist position data...");
        try {
            // Step 2: GPS calculates position (with built-in waiting in calculatePosition)
            System.out.println("GPS: Calculating position...");
            GPSPosition position = gpsProvider.calculatePosition();
            // Step 3: System was on hold, now receives position
            System.out.println("System: Position received successfully");
            return position;
        } catch (GPSException e) {
            // Exit condition: Position not detectable by GPS
            System.err.println("System Error: " + e.getMessage());
            return null;
        }
    }
    /**
     * Optional: Method to simulate advanced search scenario
     */
    public GPSPosition getTouristPositionWithRetry(int maxRetries) {
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            System.out.println("\nAttempt " + attempt + " of " + maxRetries + ":");
            GPSPosition position = getTouristPosition();
            if (position != null) {
                return position;
            }
            if (attempt < maxRetries) {
                try {
                    Thread.sleep(1000); // Wait before retry
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        return null;
    }
}
/**
 * Main application entry point.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Tourist Localization System ===");
        System.out.println("Use Case: Localization");
        System.out.println("Entry Condition: Tourist began a search\n");
        // Create localization system
        LocalizationSystem system = new LocalizationSystem();
        // Get tourist position (simulating search initiation)
        GPSPosition position = system.getTouristPosition();
        // Display results
        System.out.println("\n=== Result ===");
        if (position != null) {
            System.out.println("Tourist Position: " + position);
            System.out.println("Exit Condition: System received position");
        } else {
            System.out.println("Exit Condition: Position not detectable by GPS");
        }
        // Demonstrate retry capability for advanced search
        System.out.println("\n=== Advanced Search (with retry) ===");
        GPSPosition retryPosition = system.getTouristPositionWithRetry(3);
        if (retryPosition != null) {
            System.out.println("Advanced Search Successful: " + retryPosition);
        } else {
            System.out.println("Advanced Search Failed after all retries");
        }
    }
}