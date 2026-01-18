import java.util.concurrent.*;
import java.util.Random;

/**
 * Position class representing a geographic coordinate with latitude and longitude
 */
class Position {
    private double latitude;
    private double longitude;
    
    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public double getLatitude() {
        return latitude;
    }
    
    public double getLongitude() {
        return longitude;
    }
    
    @Override
    public String toString() {
        return String.format("Position{latitude=%.6f, longitude=%.6f}", latitude, longitude);
    }
}

/**
 * GPS Service interface that defines the contract for GPS operations
 */
interface GPSService {
    /**
     * Calculates the current position of the tourist
     * @return Position object containing latitude and longitude
     * @throws GPSException if position cannot be determined
     */
    Position calculatePosition() throws GPSException;
}

/**
 * Custom exception for GPS-related errors
 */
class GPSException extends Exception {
    public GPSException(String message) {
        super(message);
    }
    
    public GPSException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * Simulated GPS Service implementation
 * This class simulates GPS calculations with random success/failure and delays
 */
class SimulatedGPSService implements GPSService {
    private Random random;
    private static final double BASE_LATITUDE = 40.7128;  // New York City latitude
    private static final double BASE_LONGITUDE = -74.0060; // New York City longitude
    private static final double OFFSET_RANGE = 0.01;       // Small offset range for variation
    
    public SimulatedGPSService() {
        this.random = new Random();
    }
    
    @Override
    public Position calculatePosition() throws GPSException {
        // Simulate GPS calculation delay (0-8 seconds)
        int delaySeconds = random.nextInt(8);
        
        try {
            // Simulate processing time
            Thread.sleep(delaySeconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new GPSException("GPS calculation interrupted", e);
        }
        
        // Simulate GPS signal failure (20% chance)
        if (random.nextInt(100) < 20) {
            throw new GPSException("GPS signal not available. Position cannot be determined.");
        }
        
        // Generate a position with small random offsets from base coordinates
        double latitude = BASE_LATITUDE + (random.nextDouble() * OFFSET_RANGE * 2 - OFFSET_RANGE);
        double longitude = BASE_LONGITUDE + (random.nextDouble() * OFFSET_RANGE * 2 - OFFSET_RANGE);
        
        return new Position(latitude, longitude);
    }
}

/**
 * Localization System that manages GPS positioning with timeout handling
 * This follows the use case requirements:
 * 1. System requires position data
 * 2. GPS calculates position
 * 3. System waits for position data
 * 4. Handles timeout (>5 seconds) and GPS detection failures
 */
class LocalizationSystem {
    private GPSService gpsService;
    private final long timeoutSeconds;
    
    /**
     * Constructor for LocalizationSystem
     * @param gpsService the GPS service implementation to use
     * @param timeoutSeconds timeout in seconds for GPS operations
     */
    public LocalizationSystem(GPSService gpsService, long timeoutSeconds) {
        this.gpsService = gpsService;
        this.timeoutSeconds = timeoutSeconds;
    }
    
    /**
     * Gets the current position of the tourist with timeout handling
     * This method implements the main flow of events from the use case:
     * 1. System requires position data
     * 2. GPS calculates position
     * 3. System waits for position data
     * 
     * @return Position object if successful, null if timeout or GPS failure
     */
    public Position getCurrentPosition() {
        System.out.println("System: Requesting position data...");
        
        // Create a thread pool for executing GPS calculation
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Position> future = null;
        
        try {
            // Submit GPS calculation task with timeout
            future = executor.submit(() -> {
                try {
                    System.out.println("GPS: Calculating tourist position...");
                    return gpsService.calculatePosition();
                } catch (GPSException e) {
                    System.err.println("GPS Error: " + e.getMessage());
                    return null;
                }
            });
            
            // Wait for the result with timeout (use case quality requirement: >5 seconds)
            System.out.println("System: Waiting for position data (timeout: " + timeoutSeconds + "s)...");
            Position position = future.get(timeoutSeconds, TimeUnit.SECONDS);
            
            if (position != null) {
                System.out.println("System: Successfully received position: " + position);
                return position;
            } else {
                System.out.println("System: GPS could not determine position (signal unavailable).");
                return null;
            }
            
        } catch (TimeoutException e) {
            // Handle timeout (quality requirement: transaction takes more than 5 seconds)
            System.out.println("System: GPS calculation timeout after " + timeoutSeconds + " seconds.");
            
            // Cancel the task if it's still running
            if (future != null && !future.isDone()) {
                future.cancel(true);
            }
            
            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("System: Position request interrupted.");
            return null;
        } catch (ExecutionException e) {
            System.err.println("System: Error during GPS calculation: " + e.getCause().getMessage());
            return null;
        } finally {
            // Shutdown executor to prevent thread leaks
            executor.shutdown();
            try {
                // Wait for tasks to complete, but don't wait too long
                if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * Main entry point that simulates the system initialization
     * This simulates the entry condition: "He began a search or an advanced search"
     */
    public void initializeSystem() {
        System.out.println("=== Localization System Initialized ===");
        System.out.println("Entry condition: Tourist began a search/advanced search");
        System.out.println("Quality requirement: GPS timeout > 5 seconds");
        System.out.println("========================================");
    }
}

/**
 * Main class to demonstrate the Localization use case
 * This provides a runnable program that demonstrates all aspects of the use case
 */
public class LocalizationSystemDemo {
    public static void main(String[] args) {
        System.out.println("=== Starting Localization Use Case Demo ===");
        
        // Create GPS service and localization system with 5-second timeout
        GPSService gpsService = new SimulatedGPSService();
        LocalizationSystem localizationSystem = new LocalizationSystem(gpsService, 5);
        
        // Initialize system (simulates entry condition)
        localizationSystem.initializeSystem();
        
        // Attempt to get position (simulates the main flow of events)
        Position touristPosition = localizationSystem.getCurrentPosition();
        
        // Handle exit conditions
        if (touristPosition != null) {
            System.out.println("Exit condition: System received position of tourist");
            System.out.println("Tourist is at: " + touristPosition);
        } else {
            System.out.println("Exit condition: Position of tourist is not detectable by GPS");
            System.out.println("Alternative actions could be triggered here...");
        }
        
        System.out.println("\n=== Additional Test Cases ===");
        
        // Additional test: Simulate multiple attempts to show different scenarios
        System.out.println("\nTesting multiple position requests to demonstrate different scenarios:");
        for (int i = 1; i <= 3; i++) {
            System.out.println("\n--- Attempt " + i + " ---");
            Position testPosition = localizationSystem.getCurrentPosition();
            if (testPosition != null) {
                System.out.println("Attempt " + i + " successful: " + testPosition);
            } else {
                System.out.println("Attempt " + i + " failed: Could not determine position");
            }
            
            // Small delay between attempts
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        
        System.out.println("\n=== Demo Complete ===");
    }
}