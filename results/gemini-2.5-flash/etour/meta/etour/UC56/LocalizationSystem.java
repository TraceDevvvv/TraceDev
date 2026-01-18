package com.example.localization;

import java.util.concurrent.*;

/**
 * The main system class that orchestrates the localization process.
 * It interacts with the GPS to get the tourist's position and handles
 * quality requirements like the 5-second timeout.
 */
public class LocalizationSystem {

    // Quality requirement: GPS transaction should not take more than 5 seconds.
    private static final long GPS_TIMEOUT_SECONDS = 5;

    /**
     * Initiates the localization process to find the tourist's current position.
     * This method simulates the system requiring data, GPS calculating, and the system
     * being on hold until data is received or a timeout occurs.
     *
     * @param tourist The tourist for whom the location is being sought.
     * @return The Location of the tourist if successfully detected within the timeout,
     *         or null if the position is not detectable or a timeout occurs.
     */
    public Location getCurrentTouristPosition(Tourist tourist) {
        System.out.println("System: " + tourist.getName() + " has initiated a localization request.");
        System.out.println("System: Requiring data for the position from GPS.");

        // Create a GPS task
        GPS gps = new GPS();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Location> future = executor.submit(gps);

        Location touristLocation = null;
        try {
            // The system is on hold until the data of the position or timeout
            System.out.println("System: On hold, waiting for GPS data (max " + GPS_TIMEOUT_SECONDS + " seconds)...");
            touristLocation = future.get(GPS_TIMEOUT_SECONDS, TimeUnit.SECONDS);

            if (touristLocation != null) {
                System.out.println("System: Received position data for " + tourist.getName() + ": " + touristLocation);
            } else {
                System.out.println("System: Position of " + tourist.getName() + " is not detectable by GPS.");
            }
        } catch (TimeoutException e) {
            // Quality requirement: GPS requirements into the transaction in more than 5 seconds.
            System.err.println("System: GPS transaction timed out after " + GPS_TIMEOUT_SECONDS + " seconds. Position could not be determined.");
            future.cancel(true); // Interrupt the GPS calculation if it's still running
        } catch (InterruptedException e) {
            System.err.println("System: Localization process was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore the interrupted status
        } catch (ExecutionException e) {
            System.err.println("System: Error during GPS calculation: " + e.getCause().getMessage());
        } finally {
            executor.shutdownNow(); // Ensure the executor service is shut down
        }
        return touristLocation;
    }

    /**
     * Main method to demonstrate the Localization System.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        LocalizationSystem system = new LocalizationSystem();
        Tourist tourist1 = new Tourist("Alice");
        Tourist tourist2 = new Tourist("Bob");
        Tourist tourist3 = new Tourist("Charlie");

        System.out.println("--- Scenario 1: Successful Localization ---");
        tourist1.startSearch();
        Location loc1 = system.getCurrentTouristPosition(tourist1);
        if (loc1 != null) {
            System.out.println("Final result for " + tourist1.getName() + ": " + loc1);
        } else {
            System.out.println("Final result for " + tourist1.getName() + ": Location not found.");
        }
        System.out.println("\n");

        System.out.println("--- Scenario 2: Localization Timeout ---");
        // To reliably test timeout, we might need to adjust GPS.MAX_CALCULATION_TIME_MS
        // or GPS_TIMEOUT_SECONDS for this specific run, but for a general case,
        // the random nature of GPS calculation time will eventually hit a timeout.
        tourist2.startAdvancedSearch();
        Location loc2 = system.getCurrentTouristPosition(tourist2);
        if (loc2 != null) {
            System.out.println("Final result for " + tourist2.getName() + ": " + loc2);
        } else {
            System.out.println("Final result for " + tourist2.getName() + ": Location not found due to timeout or no detection.");
        }
        System.out.println("\n");

        System.out.println("--- Scenario 3: Position Not Detectable ---");
        // To reliably test non-detectable, we might need to adjust GPS.DETECTION_FAILURE_RATE
        // for this specific run, but for a general case, the random nature will eventually hit it.
        tourist3.startSearch();
        Location loc3 = system.getCurrentTouristPosition(tourist3);
        if (loc3 != null) {
            System.out.println("Final result for " + tourist3.getName() + ": " + loc3);
        } else {
            System.out.println("Final result for " + tourist3.getName() + ": Location not found due to timeout or no detection.");
        }
        System.out.println("\n");
    }
}