package com.example.localization;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Simulates a GPS device that calculates the tourist's position.
 * This class includes a simulated delay and a possibility of not detecting the position.
 */
public class GPS implements Callable<Location> {
    private static final int MIN_CALCULATION_TIME_MS = 1000; // Minimum simulated time to calculate position
    private static final int MAX_CALCULATION_TIME_MS = 6000; // Maximum simulated time to calculate position (can exceed 5s)
    private static final double DETECTION_FAILURE_RATE = 0.1; // 10% chance of not detecting position

    private final Random random;

    /**
     * Constructs a new GPS simulator.
     */
    public GPS() {
        this.random = new Random();
    }

    /**
     * Simulates the GPS calculating the position of the tourist.
     * This method introduces a random delay and a chance of failure.
     *
     * @return A Location object if successful, or null if the position is not detectable.
     * @throws Exception if the simulation is interrupted.
     */
    @Override
    public Location call() throws Exception {
        System.out.println("GPS: Starting position calculation...");

        // Simulate calculation time
        long calculationTime = MIN_CALCULATION_TIME_MS + random.nextInt(MAX_CALCULATION_TIME_MS - MIN_CALCULATION_TIME_MS + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(calculationTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("GPS: Position calculation interrupted.");
            throw new InterruptedException("GPS calculation was interrupted.");
        }

        // Simulate detection failure
        if (random.nextDouble() < DETECTION_FAILURE_RATE) {
            System.out.println("GPS: Position not detectable.");
            return null; // Position not detectable
        }

        // Simulate a random location
        double latitude = -90.0 + (90.0 - (-90.0)) * random.nextDouble();
        double longitude = -180.0 + (180.0 - (-180.0)) * random.nextDouble();

        Location detectedLocation = new Location(latitude, longitude);
        System.out.printf("GPS: Position calculated in %d ms: %s%n", calculationTime, detectedLocation);
        return detectedLocation;
    }
}