package com.example.localization.module;

import com.example.localization.model.Location;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * A mock implementation of IGpsModule for testing and demonstration purposes.
 * It simulates GPS sensor behavior, including delays and potential failures.
 */
public class MockGpsModule implements IGpsModule {
    private final Random random = new Random();
    private final int minDelayMillis;
    private final int maxDelayMillis;
    private final boolean alwaysSucceed;

    /**
     * Constructs a MockGpsModule with specified delay range and success probability.
     *
     * @param minDelayMillis The minimum simulated delay in milliseconds.
     * @param maxDelayMillis The maximum simulated delay in milliseconds.
     * @param alwaysSucceed If true, the GPS will always successfully return a location.
     *                      If false, there's a 30% chance of failure.
     */
    public MockGpsModule(int minDelayMillis, int maxDelayMillis, boolean alwaysSucceed) {
        this.minDelayMillis = minDelayMillis;
        this.maxDelayMillis = maxDelayMillis;
        this.alwaysSucceed = alwaysSucceed;
    }

    /**
     * Simulates querying a GPS sensor.
     * Introduces a random delay and may throw a GpsSensorException.
     *
     * @return A simulated Location object.
     * @throws GpsSensorException if the simulated GPS fails to provide data.
     */
    @Override
    public Location queryGpsSensor() throws GpsSensorException {
        System.out.println("[GPS Module] Querying GPS sensor...");
        // Simulate processing time (R9: GPS localization transaction must complete within 5 seconds)
        long delay = minDelayMillis + random.nextInt(maxDelayMillis - minDelayMillis + 1);
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new GpsSensorException("GPS sensor query interrupted.", e);
        }

        // Simulate success or failure
        if (!alwaysSucceed && random.nextDouble() < 0.3) { // 30% chance of failure if not always succeeding
            System.err.println("[GPS Module] Failed to detect position.");
            throw new GpsSensorException("Tourist's position is not detectable by GPS.");
        } else {
            // Generate some mock location data
            double latitude = 34.0522 + (random.nextDouble() - 0.5) * 0.1; // Around Los Angeles
            double longitude = -118.2437 + (random.nextDouble() - 0.5) * 0.1;
            long timestamp = System.currentTimeMillis();
            Location location = new Location(latitude, longitude, timestamp);
            System.out.println("[GPS Module] Successfully acquired: " + location);
            return location;
        }
    }
}