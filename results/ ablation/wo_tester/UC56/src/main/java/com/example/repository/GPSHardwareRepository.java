package com.example.repository;

import com.example.model.GPSRawData;
import com.example.model.Position;
import com.example.utils.TimeoutManager;
import com.example.hardware.GPSSensor;

/**
 * Concrete implementation of GPSRepository using hardware sensor.
 */
public class GPSHardwareRepository implements GPSRepository {
    private long timeoutMs;
    private int retryCount;
    private TimeoutManager timeoutManager;
    private GPSSensor gpsSensor;

    public GPSHardwareRepository(long timeoutMs, int maxRetries) {
        this.timeoutMs = timeoutMs;
        this.retryCount = maxRetries;
        this.timeoutManager = new TimeoutManager(timeoutMs);
        this.gpsSensor = new GPSSensor();
    }

    @Override
    public Position getCurrentPosition() {
        // Start timeout timer
        timeoutManager.startTimer();
        // Attempt retries
        for (int attempt = 1; attempt <= retryCount; attempt++) {
            System.out.println("GPS retrieval attempt " + attempt + " of " + retryCount);
            if (timeoutManager.checkTimeout()) {
                handleTimeout();
                return null; // indicate timeout
            }
            if (!isAvailable()) {
                System.out.println("GPS not available");
                return null;
            }
            GPSRawData rawData = readFromGPSSensor();
            if (rawData == null) {
                continue;
            }
            Position position = validateAndConvert(rawData);
            if (position != null && position.isValid()) {
                System.out.println("Valid position obtained.");
                return position;
            } else {
                System.out.println("Invalid position, retrying...");
            }
        }
        // All retries exhausted
        System.out.println("All retries exhausted, position not detectable.");
        return null;
    }

    @Override
    public boolean isAvailable() {
        return gpsSensor.isActive();
    }

    @Override
    public void setTimeout(long durationMs) {
        this.timeoutMs = durationMs;
        timeoutManager.setTimeout(durationMs);
    }
    
    public void acknowledged() {
        System.out.println("GPS Repository acknowledged timeout.");
    }

    @Override
    public void setMaxRetries(int count) {
        this.retryCount = count;
    }

    /**
     * Reads raw data from the GPS sensor.
     */
    protected GPSRawData readFromGPSSensor() {
        return gpsSensor.readRawData();
    }

    /**
     * Validates raw data and converts to Position.
     */
    protected Position validateAndConvert(GPSRawData rawData) {
        if (rawData == null || !rawData.isValid()) {
            return null;
        }
        try {
            double lat = Double.parseDouble(rawData.getRawLatitude());
            double lon = Double.parseDouble(rawData.getRawLongitude());
            // Simulate accuracy based on signal strength
            double accuracy = 100.0 / rawData.getSignalStrength();
            return new Position(lat, lon, accuracy);
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse raw GPS data: " + e.getMessage());
            return null;
        }
    }

    /**
     * Handles timeout event.
     */
    protected void handleTimeout() {
        timeoutManager.triggerTimeout();
        System.out.println("GPS operation timed out.");
    }

    /**
     * Handles retry logic for a given attempt.
     */
    protected boolean handleRetry(int attempt) {
        System.out.println("Retrying GPS read, attempt " + attempt);
        // Simulate delay between retries
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        return true;
    }
}